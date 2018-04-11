package nba.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import nba.model.Boxscore;
import nba.service.BoxscoreService;
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

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "boxscore", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Boxscore getBoxscore(@RequestParam String date, @RequestParam String gameId, Model model) {
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
            Boxscore box = new Boxscore();
            ArrayList<LinkedHashMap<String, Object>> players = new ArrayList<>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getKey().equalsIgnoreCase("stats")) {
                    box = boxscoreService.saveBoxscore((HashMap<String, Object>) entry.getValue());
                }
            }
            return box;
        } catch (Exception e) {
            LOGGER.info("{}", e.getMessage());
        }
        return null;
    }

}
