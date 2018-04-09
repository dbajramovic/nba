package nba.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nba.dao.repos.GameDAO;

@Component
public class GameService {

    @Autowired
    GameDAO gameDAO;

    public Map<String, String> getNewGames() {
        return gameDAO.findNewGames();
    }

}
