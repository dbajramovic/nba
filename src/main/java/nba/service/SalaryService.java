package nba.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nba.dao.model.PlayerEntity;
import nba.dao.model.PlayerSalaryEntity;
import nba.dao.repos.PlayerDAO;
import nba.dao.repos.PlayerSalaryDAO;
import nba.mapper.SalaryMapper;
import nba.model.PlayerSalary;

@Service
public class SalaryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SalaryService.class);

    private static final String COMMA_DELIMITER = ",";

    @Autowired
    private PlayerDAO playerDAO;

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
            Long year = 2016L;
            for (int j = 3; j < 9; j++) {
                PlayerSalaryEntity salary = new PlayerSalaryEntity();
                salary.setYear(year);
                LOGGER.info("{}", records.get(i));
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

}
