package nba.controllers;

import java.util.HashMap;
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

import nba.service.BoxscoreService;
import nba.service.GameService;
import nba.service.PlayerGameStatsService;

@Controller
public class PlayerGameStatsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerGameStatsController.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PlayerGameStatsService pgsService;

    @Autowired
    BoxscoreService boxscoreService;

    @Autowired
    GameService gameService;

    @SuppressWarnings("unchecked")
    @GetMapping(value = "boxscore", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> getBoxscore(@RequestParam String date, @RequestParam String gameId, Model model) {
        final String url = "http://data.nba.net/prod/v1/" + date + "/" + gameId + "_boxscore.json";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>("", headers);
            Map<String, String> params = new HashMap<>();
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<Map<String, Object>>() {
                    }, params);
            Map<String, Object> map = response.getBody();
            HashMap<String, Object> boxScoreMap = new HashMap<>();
            HashMap<String, Object> hTeam = new HashMap<>();
            HashMap<String, Object> vTeam = new HashMap<>();
            LOGGER.info("Getting boxscore for game: {}", gameId);
            String year = "";
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getKey().equalsIgnoreCase("basicGameData")) {
                    HashMap<String, Object> basicGameData = (HashMap<String, Object>) entry.getValue();
                    hTeam = (HashMap<String, Object>) basicGameData.get("hTeam");
                    vTeam = (HashMap<String, Object>) basicGameData.get("vTeam");
                    year = (String) basicGameData.get("seasonYear");
                }
                if (entry.getKey().equalsIgnoreCase("stats")) {
                    boxScoreMap = (HashMap<String, Object>) entry.getValue();
                }
            }
            boxScoreMap.put("hTeamId", hTeam.get("teamId"));
            boxScoreMap.put("vTeamId", vTeam.get("teamId"));
            boxScoreMap.put("year", year);
            boxScoreMap.put("gameId", gameId);
            boxscoreService.saveBoxscore(boxScoreMap);
            return boxScoreMap;
        } catch (Exception e) {
            LOGGER.info("{}", e.getMessage());
            throw e;
        }
    }

}
