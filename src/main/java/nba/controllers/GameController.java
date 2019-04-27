package nba.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

import nba.dao.model.PlayInfo;
import nba.model.Game;
import nba.service.GameService;
import nba.service.PlayService;
import nba.service.PlayerService;

@Controller
public class GameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PlayerService playersService;

    @Autowired
    PlayService playService;

    @Autowired
    GameService gameService;

    @GetMapping(value = "saveAllGames", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Game> saveAllGames(Model model) {
        List<Game> games = new ArrayList<>();
        Map<String, String> gamesToGet = gameService.getNewGames();
        int numOfGames = gamesToGet.entrySet().parallelStream().filter(yearIsOK()).collect(Collectors.toList()).size();
        for (Map.Entry<String, String> entry : gamesToGet.entrySet()) {
            if (entry.getValue().startsWith("2016") || entry.getValue().startsWith("2017") || entry.getValue().startsWith("2018")
                    || entry.getValue().startsWith("2019")) {
                LOGGER.info("Saving game:{}/{}({} on date:{})[{}%]", games.size(), numOfGames, entry.getKey(), entry.getValue(),
                        (games.size() / (float) numOfGames) * 100);
                games.add(getGame(entry.getValue(), entry.getKey(), model));
            }
        }
        return games;
    }

    private Predicate<? super Entry<String, String>> yearIsOK() {
        return p -> p.getValue().startsWith("2016") || p.getValue().startsWith("2017") || p.getValue().startsWith("2018")
                || p.getValue().startsWith("2019");
    }

    @GetMapping(value = "fullgame", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Game getFullGame(@RequestParam Long gameId, Model model) {
        return gameService.getFullGame(gameId);
    }

    @GetMapping(value = "digestGame", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<PlayInfo> digestGame(@RequestParam Long gameId, Model model) {
        return gameService.digestGame(gameId);
    }

    @SuppressWarnings("unchecked")
    @GetMapping(value = "game", produces = "application/json; charset=UTF-8")
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
            ArrayList<LinkedHashMap<String, Object>> plays = new ArrayList<>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getKey().equalsIgnoreCase("sports_content")) {
                    Map<String, Object> playerMap = (HashMap<String, Object>) entry.getValue();
                    LinkedHashMap<String, Object> gameMap = (LinkedHashMap<String, Object>) playerMap.get("game");
                    plays = (ArrayList<LinkedHashMap<String, Object>>) gameMap.get("play");
                }
            }
            return playService.savePlays(plays, gameId, date);
        } catch (Exception e) {
            LOGGER.error("Failed", e);
        }
        return null;
    }
}
