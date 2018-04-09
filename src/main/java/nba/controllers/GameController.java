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

import nba.model.Game;
import nba.service.PlayerService;

@Controller
public class GameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PlayerService playersService;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "game", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Game getGame(@RequestParam String date, @RequestParam String gameId, Model model) {
        final String url = "http://data.nba.net/json/cms/noseason/game/" + date + "/" + gameId + "/pbp_all.json";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>("", headers);
            Map<String, String> params = new HashMap<>();
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<Map<String, Object>>() {
                    }, params);
            Map<String, Object> map = response.getBody();
            ArrayList<LinkedHashMap<String, Object>> players = new ArrayList<>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getKey().equalsIgnoreCase("sports_content")) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> playerMap = (HashMap<String, Object>) entry.getValue();
                    LinkedHashMap<String, Object> gameMap = (LinkedHashMap<String, Object>) playerMap.get("game");
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
