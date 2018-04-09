package nba.mapper;

import org.springframework.stereotype.Service;

import nba.dao.model.GameEntity;
import nba.model.Game;

@Service
public class GameMapper {

    public Game entitytoDto(GameEntity game) {
        Game gameDto = new Game();
        gameDto.setGameId(game.getGameId());
        gameDto.setArena(game.getArena());
        gameDto.setCity(game.getCity());
        gameDto.setCountry(game.getCountry());
        gameDto.setDate(game.getDate());
        gameDto.setGameUrl(game.getGameUrl());
        gameDto.setHomeStartDate(game.getHomeStartDate());
        gameDto.setHomeStartTime(game.getHomeStartTime());
        gameDto.setSeasonId(game.getSeasonId());
        gameDto.setState(game.getState());
        gameDto.setTime(game.getTime());
        gameDto.setVisitorStartDate(game.getVisitorStartDate());
        gameDto.setVisitorStartTime(game.getVisitorStartTime());
        return gameDto;
    }

}
