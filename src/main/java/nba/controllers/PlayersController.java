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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import nba.model.Player;
import nba.model.Team;
import nba.model.Years;
import nba.service.PlayerService;

@Controller
public class PlayersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayersController.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PlayerService playersService;

    @RequestMapping(value = "saveAllPlayers", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Player> saveAllPlayers(Model model) {
        List<Player> players = new ArrayList<>();
        for (int i = Integer.parseInt(Years.STARTYEAR.getValue()); i < Integer.parseInt(Years.ENDYEAR.getValue()); i++) {
            players.addAll(getRoster(i + "", model));
            LOGGER.info("Year {} saved.", i);
        }
        return players;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "roster", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Player> getRoster(@RequestParam String year, Model model) {
        final String url = "http://data.nba.net/prod/v1/" + year + "/players.json";
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
                if (entry.getKey().equalsIgnoreCase("league")) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> playerMap = (HashMap<String, Object>) entry.getValue();
                    for (Object o : playerMap.entrySet()) {
                        HashMap.Entry<String, Object> oMap = (HashMap.Entry<String, Object>) o;
                        players = (ArrayList<LinkedHashMap<String, Object>>) oMap.getValue();
                    }
                    model.addAttribute("players", players);
                }
            }
            return playersService.savePlayers(players, year);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Collections.emptyList();
    }

    @RequestMapping(value = "teams/player", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Team> teamsOfPlayer(@RequestParam final String name, @RequestParam final String surname) {
        return playersService.teamsOfPlayer(name, surname);
    }

}
