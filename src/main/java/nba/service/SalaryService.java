package nba.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nba.dao.model.GameEntity;
import nba.dao.model.PlayerEntity;
import nba.dao.model.PlayerSalaryEntity;
import nba.dao.model.TeamEntity;
import nba.dao.repos.BoxscoreDAO;
import nba.dao.repos.GameDAO;
import nba.dao.repos.PlayerDAO;
import nba.dao.repos.PlayerSalaryDAO;
import nba.dao.repos.TeamDAO;
import nba.mapper.SalaryMapper;
import nba.model.GamePriceStructure;
import nba.model.PlayerSalary;
import nba.model.PlayerSalaryPair;

@Service
public class SalaryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SalaryService.class);

    private static final String COMMA_DELIMITER = ",";

    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private GameDAO gameDAO;

    @Autowired
    private BoxscoreDAO boxscoreDAO;

    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private SalaryMapper playerSalaryMapper;

    @Autowired
    private PlayerSalaryDAO playerSalaryDAO;

    public List<PlayerSalary> saveAllSalaries() throws IOException {
        List<List<String>> records = new ArrayList<>();
        List<PlayerSalaryEntity> salaries = new ArrayList<>();
        List<PlayerEntity> playerEnts = (List<PlayerEntity>) playerDAO.findAll();
        Map<String, String> players = playerEnts.parallelStream()
                .collect(Collectors.toMap(PlayerEntity::getFullName, PlayerEntity::getPersonId, (p1, p2) -> {
                    return p1;
                }));
        Map<String, PlayerEntity> playerPersonIdMap = playerEnts.parallelStream()
                .collect(Collectors.toMap(PlayerEntity::getPersonId, PlayerEntity::getItself, (p1, p2) -> {
                    return p1;
                }));
        try (BufferedReader br = new BufferedReader(new FileReader("salary.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        }
        for (int i = 1; i < records.size(); i++) {
            Long year = 2018L;
            for (int j = 3; j < 9; j++) {
                PlayerSalaryEntity salary = new PlayerSalaryEntity();
                salary.setYear(year);
                if (records.get(i).size() > j && !"".equalsIgnoreCase(records.get(i).get(j))) {
                    salary.setSalary(Long.parseLong(records.get(i).get(j).substring(1)));
                    if (records.get(i).size() < 10) {
                        salary.setGaranteedSalary(Long.parseLong(records.get(i).get(j).substring(1)));
                    } else {
                        salary.setGaranteedSalary(Long.parseLong(records.get(i).get(10).substring(1)));
                    }
                    salary.setPersonId(players.get(records.get(i).get(1).split("/")[0]));
                    year++;
                    salary.setPlayer(playerPersonIdMap.get(salary.getPersonId()));
                    salaries.add(salary);
                }
            }
        }
        playerSalaryDAO.saveAll(salaries);
        return playerSalaryMapper.entitesToDtos(salaries);
    }

    public GamePriceStructure getPriceOfGame(final String gameId) {
        GamePriceStructure struct = new GamePriceStructure();
        List<String> teamIds = boxscoreDAO.findTeamIdsForGameId(gameId);
        if (!teamIds.isEmpty()) {
            GameEntity game = gameDAO.findByGameId(gameId);
            struct.setYear(game.getHomeStartDate().substring(0, 4));
            struct.setHomeTeamPrice(createTeamPrices(teamIds.get(0), game));
            struct.setVisitorTeamPrice(createTeamPrices(teamIds.get(1), game));
            struct.setTeamPriceMap(createTeamPriceMap(teamIds, struct));
        }
        return struct;
    }

    private List<PlayerSalaryPair> createTeamPriceMap(List<String> teamIds, GamePriceStructure struct) {
        Map<String, Long> map = new HashMap<>();
        Long price = 0L;
        for (PlayerSalaryPair salary : struct.getHomeTeamPrice()) {
            price += salary.getValue();
        }
        map.put(teamIds.get(0), price);
        price = 0L;
        for (PlayerSalaryPair salary : struct.getVisitorTeamPrice()) {
            price += salary.getValue();
        }
        map.put(teamIds.get(1), price);
        return mapTeamNames(map, struct.getYear());
    }

    private List<PlayerSalaryPair> mapTeamNames(Map<String, Long> map, final String year) {
        Map<String, String> teamTricodeMap = teamDAO.findByTeamIds(map.keySet(), year).stream()
                .collect(Collectors.toMap(TeamEntity::getTeamId, TeamEntity::getTricode, (p1, p2) -> {
                    return p1;
                }));
        List<PlayerSalaryPair> cleanedMap = new ArrayList<>();
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            PlayerSalaryPair pair = new PlayerSalaryPair();
            pair.setName(teamTricodeMap.get(entry.getKey()));
            pair.setValue(entry.getValue());
            cleanedMap.add(pair);
        }
        return cleanedMap;
    }

    private List<PlayerSalaryPair> createTeamPrices(String teamId, GameEntity game) {
        Long endOfSeason = Long.parseLong(game.getHomeStartDate().substring(0, 4)) - 1L;
        return mapPlayerNames(playerSalaryDAO.createPrices(teamId, game.getHomeStartDate().substring(0, 4), endOfSeason.toString()));
    }

    private List<PlayerSalaryPair> mapPlayerNames(Map<String, Long> playerSalaries) {
        Map<String, String> playerPersonIdMap = playerDAO.findByPersonIds(playerSalaries.keySet()).stream()
                .collect(Collectors.toMap(PlayerEntity::getPersonId, PlayerEntity::getFullName, (p1, p2) -> {
                    return p1;
                }));
        List<PlayerSalaryPair> cleanedMap = new ArrayList<>();
        for (Map.Entry<String, Long> entry : playerSalaries.entrySet()) {
            PlayerSalaryPair pair = new PlayerSalaryPair();
            pair.setName(playerPersonIdMap.get(entry.getKey()));
            pair.setValue(entry.getValue());
            cleanedMap.add(pair);
        }
        return cleanedMap;
    }

}
