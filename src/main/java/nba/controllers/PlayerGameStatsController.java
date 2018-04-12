package nba.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

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
import nba.model.Game;
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

    @RequestMapping(value = "boxscore/all", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    @Transactional(rollbackOn = Exception.class)
    public List<Boxscore> saveAllBoxscores(Model model) {
        List<Boxscore> boxs = new ArrayList<>();
        List<Game> games = gameService.getGames();
        List<Game> releventGames = new ArrayList<>();
        releventGames.addAll(games.parallelStream().filter(isInYear("2015")).collect(Collectors.toList()));
        releventGames.addAll(games.parallelStream().filter(isInYear("2016")).collect(Collectors.toList()));
        releventGames.addAll(games.parallelStream().filter(isInYear("2017")).collect(Collectors.toList()));
        releventGames.addAll(games.parallelStream().filter(isInYear("2018")).collect(Collectors.toList()));
        int i = 1;
        for (Game game : releventGames) {
            LOGGER.info("Parsing of boxscores at: {}%", ((float) i / releventGames.size()) * 100);
            boxs.add(getBoxscore(game.getDate(), game.getGameId(), model));
            i++;
        }
        return boxs;
    }

    private Predicate<Game> isInYear(String year) {
        return g -> g.getDate().contains(year);
    }

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
            HashMap<String, Object> boxScoreMap = new HashMap<>();
            HashMap<String, Object> hTeam = new HashMap<>();
            HashMap<String, Object> vTeam = new HashMap<>();
            Boxscore box;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getKey().equalsIgnoreCase("basicGameData")) {
                    HashMap<String, Object> basicGameData = (HashMap<String, Object>) entry.getValue();
                    hTeam = (HashMap<String, Object>) basicGameData.get("hTeam");
                    vTeam = (HashMap<String, Object>) basicGameData.get("vTeam");
                }
                if (entry.getKey().equalsIgnoreCase("stats")) {
                    boxScoreMap = (HashMap<String, Object>) entry.getValue();
                }
            }
            boxScoreMap.put("hTeamId", hTeam.get("teamId"));
            boxScoreMap.put("vTeamId", vTeam.get("teamId"));
            box = boxscoreService.saveBoxscore(boxScoreMap);
            return box;
        } catch (Exception e) {
            LOGGER.info("{}", e.getMessage());
        }
        return null;
    }

}
