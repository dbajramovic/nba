package nba.controllers;

import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import nba.dao.repos.TeamDAO;
import nba.mapper.TeamMapper;
import nba.model.Schedule;
import nba.model.Team;
import nba.model.TeamRoster;
import nba.model.Years;
import nba.service.TeamService;

@Controller
public class TeamsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayersController.class);

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    TeamService teamService;

    @Autowired
    TeamDAO teamDAO;

    @GetMapping(value = "saveAllTeams", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Team> saveAllTeams(Model model) {
        List<Team> teams = new ArrayList<>();
        for (int i = Integer.parseInt(Years.STARTYEAR.getValue()); i < Integer.parseInt(Years.ENDYEAR.getValue()); i++) {
            teams.addAll(saveTeams(i + "", model));
            LOGGER.info("Year {} saved.", i);
        }
        return teams;
    }

    @GetMapping(value = "teamSchedule", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Schedule teamSchedule(@RequestParam final String team, @RequestParam final String year, Model model) {
        return teamService.teamSchedule(team, year);
    }

    @GetMapping(value = "teams", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Team> getAllTeams(Model model) {
        return teamService.getAllTeams();
    }

    @GetMapping(value = "team/roster", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public TeamRoster getTeamRoster(@RequestParam final String team, @RequestParam final String year) {
        return teamService.getTeamRoster(team, year);
    }

    @SuppressWarnings("unchecked")
    @GetMapping(value = "saveTeams", produces = "application/json; charset=UTF-8")
    public List<Team> saveTeams(@RequestParam String year, Model model) {
        final String url = "http://data.nba.net/prod/v1/" + year + "/teams.json";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>("", headers);
            Map<String, String> params = new HashMap<>();
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<Map<String, Object>>() {
                    }, params);
            Map<String, Object> map = response.getBody();
            List<LinkedHashMap<String, Object>> teams = new ArrayList<>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getKey().equalsIgnoreCase("league")) {
                    Map<String, Object> playerMap = (HashMap<String, Object>) entry.getValue();
                    for (Object o : playerMap.entrySet()) {
                        HashMap.Entry<String, Object> oMap = (HashMap.Entry<String, Object>) o;
                        teams.addAll((ArrayList<LinkedHashMap<String, Object>>) oMap.getValue());
                    }
                    model.addAttribute("teams", teams);
                }
            }
            return teamService.saveTeams(teams, year);
        } catch (Exception e) {
            LOGGER.info("SHIT:{}", e.getMessage());
        }
        return Collections.emptyList();
    }

}
