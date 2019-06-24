package nba.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

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
import nba.model.GameLight;
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
        List<Game> games = gameService.getGames();
        try {
            LOGGER.info("Started to save games.");
            int numOfGames = games.size();
            LOGGER.info("{} games", numOfGames);
            List<String> gameIds = new ArrayList<>();
            int i = 1;
            for (Game game1 : games) {
                if (!gameIds.contains(game1.getGameId())) {
                    LOGGER.info("Saving game:{}/{}({} on date:{})[{}%]", i, numOfGames, game1.getDate(), game1.getGameId(),
                            (i / (float) games.size()) * 100);
                    Game game = getGame(game1.getDate(), game1.getGameId(), model);
                    if (game != null) {
                        games.add(game);
                        gameIds.add(game.getGameId());
                    }
                } else {
                    LOGGER.info("Game ID:{} already is in database.", game1.getGameId());
                }
                i++;
            }
        } catch (Exception e) {
            LOGGER.info("SHIT: {}", e.getMessage());
        }
        return games;
    }

    @GetMapping(value = "fullgame", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Game getFullGame(@RequestParam Long gameId, Model model) {
        return gameService.getFullGame(gameId);
    }

    @GetMapping(value = "games/light", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<GameLight> getGamesLight(@RequestParam String year) {
        return gameService.getGamesLight(year);
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
            LOGGER.info("URL:{}", url);
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
            LOGGER.error("Failed: {}", e.getMessage());
        }
        return null;
    }
}
