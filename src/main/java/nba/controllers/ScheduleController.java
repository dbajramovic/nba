package nba.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import nba.dao.model.GameEntity;
import nba.dao.model.ScheduleEntity;
import nba.dao.model.ScheduleGameEntity;
import nba.dao.repos.GameDAO;
import nba.dao.repos.ScheduleDAO;
import nba.dao.repos.ScheduleGameDAO;
import nba.model.Schedule;
import nba.model.Years;
import nba.service.GameService;
import nba.service.ScheduleService;
import nba.service.TeamService;

@Controller
public class ScheduleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayersController.class);

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ScheduleDAO scheduleDAO;

    @Autowired
    TeamService teamService;

    @Autowired
    GameService gameService;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    GameDAO gameDAO;
    @Autowired
    ScheduleGameDAO schGameDAO;

    @GetMapping(value = "saveAllSchedules", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public List<Schedule> saveAllTeams(Model model) {
        List<Schedule> schedules = new ArrayList<>();
        List<String> nicknames = teamService.getNicknamesOfTeams(true);
        for (int i = Integer.parseInt(Years.STARTYEAR.getValue()); i < Integer.parseInt(Years.ENDYEAR.getValue()); i++) {
            for (String nickname : nicknames) {
                LOGGER.info("{} schedule for {} is being saved.", nickname, i);
                saveSchedule(i + "", nickname, model);
            }
            LOGGER.info("Year {} saved.", i);
        }
        return schedules;
    }

    @SuppressWarnings("unchecked")
    public void saveSchedule(@RequestParam String year, @RequestParam String team, Model model) {
        if ("76ers".equalsIgnoreCase(team.toLowerCase())) {
            team = "sixers";
        } else if ("trail blazers".equalsIgnoreCase(team.toLowerCase())) {
            team = "blazers";
        }
        final String url = "http://data.nba.net/json/cms/" + year + "/team/" + team.toLowerCase() + "/schedule.json";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>("", headers);
            Map<String, String> params = new HashMap<>();
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<Map<String, Object>>() {
                    }, params);
            Map<String, Object> map = response.getBody();
            Map<String, Object> playerMap = (HashMap<String, Object>) map.get("sports_content");
            ArrayList<LinkedHashMap<String, Object>> oMap = (ArrayList<LinkedHashMap<String, Object>>) playerMap.get("game");
            List<GameEntity> games = new ArrayList<>();
            List<ScheduleGameEntity> maps = new ArrayList<>();
            ScheduleEntity schEntity = scheduleDAO.findByTeamAndYear(team, year);
            Map<String, Long> gameIdIdMap = gameDAO.findGameIds();
            for (LinkedHashMap<String, Object> t : oMap) {
                String gameId = "";
                GameEntity newGame = null;
                if (t.get("id") != null) {
                    gameId = (String) t.get("id");
                    LOGGER.info("Game ID is :{}", gameId);
                }
                if ("".equalsIgnoreCase(gameId) || gameIdIdMap.get(gameId) == null) {
                    LOGGER.info("Game is new and will be added!");
                    newGame = gameDAO.save(gameService.createGameEntity(t, team, year));
                    gameIdIdMap.put(newGame.getGameId(), newGame.getId());
                    games.add(newGame);
                } else {
                    newGame = gameDAO.findByGameId(gameId);
                    LOGGER.info("Game already exists in  the system, making link for {}-> {}", newGame.getGameId(), schEntity.getTeam());
                }
                maps.add(createGameScheduleEntity(newGame, schEntity));
            }
            gameDAO.saveAll(games);
            schGameDAO.saveAll(maps);
            LOGGER.info("Saved {} games and {} schedules for ({},{})", games.size(), maps.size(), team, year);
        } catch (Exception e) {
            LOGGER.error("SHIT:{} YEAR:{} TEAM:{}", e.getMessage(), year, team);
        }
    }

    private ScheduleGameEntity createGameScheduleEntity(GameEntity newGame, ScheduleEntity schEntity) {
        ScheduleGameEntity schGame = new ScheduleGameEntity();
        schGame.setGame(newGame);
        schGame.setSchedule(schEntity);
        schGame.setDate(newGame.getDate());
        schGame.setHomeAbrv(newGame.getGameUrl().split("/")[1].substring(3, 6));
        schGame.setVisitAbrv(newGame.getGameUrl().split("/")[1].substring(0, 3));
        return schGame;
    }

    public void createAllSchedules(Model model) {
        List<String> nicknames = teamService.getNicknamesOfTeams(true);
        List<ScheduleEntity> schedules = new ArrayList<>();
        for (String nick : nicknames) {
            for (int i = Integer.parseInt(Years.STARTYEAR.getValue()); i < Integer.parseInt(Years.ENDYEAR.getValue()); i++) {
                schedules.add(scheduleService.createSchedule(nick, i + ""));
            }
        }
        scheduleDAO.saveAll(schedules);
        LOGGER.info("Saved {} schedules for {} teams for {} years.", schedules.size(), nicknames.size(),
                Integer.parseInt(Years.ENDYEAR.getValue()) - Integer.parseInt(Years.STARTYEAR.getValue()));
    }
}
