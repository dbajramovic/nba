package nba.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import nba.dao.model.GameEntity;
import nba.dao.model.PlayEntity;
import nba.dao.model.PlayInfo;
import nba.dao.repos.GameDAO;
import nba.dao.repos.PlayDAO;
import nba.helpers.PlayDescriptionParser;
import nba.mapper.GameMapper;
import nba.model.Game;

@Component
public class GameService {

    @Autowired
    GameDAO gameDAO;

    @Autowired
    PlayDAO playDAO;

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
                playInfos.add(PlayDescriptionParser.determineTypeOfPlay(play.getDescription()));
            }
        }
        return playInfos;
    }

    public List<Game> getGames() {
        return gameMapper.entitesToDtos(Lists.newArrayList(gameDAO.findAll()), false);
    }

}
