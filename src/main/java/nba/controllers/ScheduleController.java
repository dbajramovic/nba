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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import nba.dao.model.GameEntity;
import nba.dao.model.ScheduleEntity;
import nba.dao.repos.GameDAO;
import nba.dao.repos.ScheduleDAO;
import nba.model.Schedule;
import nba.model.Years;
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
    GameDAO gameDAO;

    @RequestMapping(value = "saveAllSchedules", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Schedule> saveAllTeams(Model model) {
        List<Schedule> schedules = new ArrayList<>();
        List<String> nicknames = teamService.getNicknamesOfTeams(true);
        for (int i = Integer.parseInt(Years.STARTYEAR.getValue()); i < Integer.parseInt(Years.ENDYEAR.getValue()); i++) {
            for (String nickname : nicknames) {
                LOGGER.info("{} schedule for {} is being saved.", nickname, i);
                ScheduleEntity ent = getSchedule(i + "", nickname, model);
                if (ent != null) {
                    Schedule schedule = new Schedule();
                    schedule.setTeam(ent.getTeam());
                    schedule.setYear(ent.getYear());
                    schedules.add(schedule);
                }
            }
            LOGGER.info("Year {} saved.", i);
        }
        return schedules;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "saveSchedule", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public ScheduleEntity getSchedule(@RequestParam String year, @RequestParam String team, Model model) {
        if ("76ers".equalsIgnoreCase(team.toLowerCase())) {
            team = "sixers";
        } else if ("trail blazers".equalsIgnoreCase(team.toLowerCase())) {
            team = "blazers";
        }
        final String url = "http://data.nba.net/json/cms/" + year + "/team/" + team.toLowerCase() + "/schedule.json";
        try {
            Boolean newNeeded = false;
            ScheduleEntity schEntity = scheduleDAO.findByTeamAndYear(team, year);
            if (schEntity == null) {
                newNeeded = true;
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>("", headers);
            Map<String, String> params = new HashMap<>();
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<Map<String, Object>>() {
                    }, params);
            Map<String, Object> map = response.getBody();
            @SuppressWarnings("unchecked")
            Map<String, Object> playerMap = (HashMap<String, Object>) map.get("sports_content");
            ArrayList<LinkedHashMap<String, Object>> oMap = (ArrayList<LinkedHashMap<String, Object>>) playerMap.get("game");
            for (LinkedHashMap<String, Object> t : oMap) {
                GameEntity ent = new GameEntity();
                if (t.get("game_url") != null) {
                    ent.setGameUrl((String) t.get("game_url"));
                }
                if (t.get("season_id") != null) {
                    ent.setSeasonId((String) t.get("season_id"));
                }
                if (t.get("id") != null) {
                    ent.setGameId((String) t.get("id"));
                }
                if (t.get("date") != null) {
                    ent.setDate((String) t.get("date"));
                }
                if (t.get("time") != null) {
                    ent.setTime((String) t.get("time"));
                }
                if (t.get("arena") != null) {
                    ent.setArena((String) t.get("arena"));
                }
                if (t.get("city") != null) {
                    ent.setCity((String) t.get("city"));
                }
                if (t.get("state") != null) {
                    ent.setState((String) t.get("state"));
                }
                if (t.get("country") != null) {
                    ent.setCountry((String) t.get("country"));
                }
                if (t.get("home_start_time") != null) {
                    ent.setHomeStartTime((String) t.get("home_start_time"));
                }
                if (t.get("home_start_date") != null) {
                    ent.setHomeStartDate((String) t.get("home_start_date"));
                }
                if (t.get("visitor_start_date") != null) {
                    ent.setVisitorStartDate((String) t.get("visitor_start_date"));
                }
                if (t.get("visitor_start_time") != null) {
                    ent.setVisitorStartTime((String) t.get("visitor_start_time"));
                }
                if (t.get("tnt_ot") != null) {
                    ent.setTntOt((String) t.get("tnt_ot"));
                }

                if (schEntity != null) {
                    ent.setSchedule(schEntity);
                    gameDAO.save(ent);
                } else if (newNeeded) {
                    schEntity = new ScheduleEntity();
                    schEntity.setTeam(team);
                    schEntity.setYear(year);
                    ent.setSchedule(schEntity);
                    schEntity = scheduleDAO.save(schEntity);
                    gameDAO.save(ent);
                    newNeeded = false;
                }
            }
            return schEntity;
        } catch (Exception e) {
            LOGGER.error("SHIT:{} YEAR:{} TEAM:{}", e.getMessage(), year, team);
        }
        return null;
    }
}
