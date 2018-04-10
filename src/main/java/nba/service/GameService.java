package nba.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nba.dao.model.GameEntity;
import nba.dao.repos.GameDAO;
import nba.dao.repos.PlayDAO;
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

}
