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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nba.model.Boxscore;
import nba.model.Game;
import nba.service.BoxscoreService;
import nba.service.GameService;

@Controller
public class BoxscoreController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoxscoreController.class);

    @Autowired
    GameService gameService;

    @Autowired
    PlayerGameStatsController playerGameStatsController;

    @Autowired
    BoxscoreService boxscoreService;

    @GetMapping(value = "boxscore/all", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @Transactional(rollbackOn = Exception.class)
    public List<Boxscore> saveAllBoxscores(Model model) {
        List<Boxscore> boxs = new ArrayList<>();
        List<Game> games = gameService.getGames();
        List<Game> releventGames = new ArrayList<>();
        releventGames.addAll(games.parallelStream().filter(isInYear("2014")).collect(Collectors.toList()));
        releventGames.addAll(games.parallelStream().filter(isInYear("2015")).collect(Collectors.toList()));
        releventGames.addAll(games.parallelStream().filter(isInYear("2016")).collect(Collectors.toList()));
        releventGames.addAll(games.parallelStream().filter(isInYear("2017")).collect(Collectors.toList()));
        releventGames.addAll(games.parallelStream().filter(isInYear("2018")).collect(Collectors.toList()));
        releventGames.addAll(games.parallelStream().filter(isInYear("2019")).collect(Collectors.toList()));
        int i = 1;
        Map<String, Game> uniqueGameIds = new HashMap<>();
        for (Game game : releventGames) {
            uniqueGameIds.put(game.getGameId(), game);
        }
        LOGGER.info("Parsing of {} games", uniqueGameIds.entrySet().size());
        for (Game game : releventGames) {
            LOGGER.info("Parsing of boxscores at: {}%", ((float) i / uniqueGameIds.entrySet().size()) * 100);
            boxs.add(boxscoreService.saveBoxscore(playerGameStatsController.getBoxscore(game.getDate(), game.getGameId(), model)));
            i++;
        }
        return boxs;
    }

    private Predicate<Game> isInYear(String year) {
        return g -> g.getDate().contains(year);
    }
}
