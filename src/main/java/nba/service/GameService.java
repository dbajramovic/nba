package nba.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import nba.dao.model.GameEntity;
import nba.dao.model.PlayEntity;
import nba.dao.model.PlayInfo;
import nba.dao.repos.GameDAO;
import nba.dao.repos.PlayDAO;
import nba.dao.repos.ScheduleDAO;
import nba.helpers.PlayDescriptionParser;
import nba.mapper.GameMapper;
import nba.model.Game;
import nba.model.GameLight;
import nba.model.GameScore;
import nba.model.ScoreCooridinate;

@Component
public class GameService {

    @Autowired
    GameDAO gameDAO;

    @Autowired
    PlayDAO playDAO;

    @Autowired
    ScheduleDAO scheduleDAO;

    @Autowired
    GameMapper gameMapper;

    public Map<String, String> getNewGames() {
        return gameDAO.findNewGames();
    }

    public Game getFullGame(Long gameId) {
        Optional<GameEntity> game = gameDAO.findById(gameId);
        if (game.isPresent()) {
            game.get().setPlays(playDAO.findByGameId(gameId));
            return gameMapper.entitytoDto(game.get(), true);
        }
        return null;
    }

    public List<PlayInfo> digestGame(Long gameId) {
        List<PlayInfo> playInfos = new ArrayList<>();
        Optional<GameEntity> game = gameDAO.findById(gameId);
        if (game.isPresent()) {
            game.get().setPlays(playDAO.findByGameId(gameId));
            for (PlayEntity play : game.get().getPlays()) {
                PlayInfo info = PlayDescriptionParser.determineTypeOfPlay(play.getDescription());
                if (info != null) {
                    playInfos.add(info);
                }
            }
        }
        return playInfos;
    }

    public List<Game> getGames() {
        return gameMapper.entitesToDtos(Lists.newArrayList(gameDAO.findAll()), false);
    }

    public GameEntity createGameEntity(Map<String, Object> t, String team, String year) {
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
        return ent;
    }

    public List<GameLight> getGamesLight(String year) {
        return gameDAO.getAll(year);
    }

    public GameScore getGameScore(Long gameId) {
        GameScore gs = new GameScore();
        gs.setPeriodPointsAway(new ArrayList<>());
        gs.setPeriodPointsHome(new ArrayList<>());

        Optional<GameEntity> game = gameDAO.findById(gameId);
        if (game.isPresent()) {
            game.get().setPlays(playDAO.findByGameId(gameId));
            handleScore(gs, game);
            handleCoordinates(gs, game);
        }
        return gs;
    }

    private void handleCoordinates(GameScore gs, Optional<GameEntity> game) {
        if (game.get().getPlays() != null && !game.get().getPlays().isEmpty()) {
            gs.setCoordinates(new ArrayList<>());
            Long step = 1L;
            Long homeScore = 0L;
            Long visitorScore = 0L;
            for (PlayEntity play : game.get().getPlays()) {
                if (homeScore < Long.parseLong(play.getHomeScore()) || visitorScore < Long.parseLong(play.getVisitorScore())) {
                    ScoreCooridinate scoreCor = new ScoreCooridinate();
                    scoreCor.setStep(step);
                    scoreCor.setHomeScore(Long.parseLong(play.getHomeScore()));
                    scoreCor.setVisitorScore(Long.parseLong(play.getVisitorScore()));
                    homeScore = scoreCor.getHomeScore();
                    visitorScore = scoreCor.getVisitorScore();
                    gs.getCoordinates().add(scoreCor);
                    step++;
                }
            }
        }
    }

    private void handleScore(GameScore gs, Optional<GameEntity> game) {
        if (game.get().getPlays() != null && !game.get().getPlays().isEmpty()) {
            List<PlayEntity> lastPeriodPlays = game.get().getPlays().stream().filter(isDesc("End Period")).sorted(byEvent().reversed())
                    .collect(Collectors.toList());
            Long homeLastScore = 0L;
            Long visitorLastScore = 0L;
            for (PlayEntity play : lastPeriodPlays) {
                gs.getPeriodPointsHome().add(Long.parseLong(play.getHomeScore()) - homeLastScore);
                gs.getPeriodPointsAway().add(Long.parseLong(play.getVisitorScore()) - visitorLastScore);
                homeLastScore = Long.parseLong(play.getHomeScore());
                visitorLastScore = Long.parseLong(play.getVisitorScore());
            }
            PlayEntity lastPlay = game.get().getPlays().stream().sorted(byEvent()).collect(Collectors.toList()).get(0);
            gs.setHomePoints(Long.parseLong(lastPlay.getHomeScore()));
            gs.setVisitorPoints(Long.parseLong(lastPlay.getVisitorScore()));
        }
    }

    private Predicate<? super PlayEntity> isDesc(String string) {
        return r -> r.getDescription().contains(string);
    }

    private Comparator<? super PlayEntity> byEvent() {
        return (p1, p2) -> {
            if (Long.parseLong(p1.getEvent()) > Long.parseLong(p2.getEvent())) {
                return -1;
            }
            return 1;
        };
    }

}
