package nba.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nba.dao.model.GameEntity;
import nba.model.Game;

@Service
public class GameMapper {

    @Autowired
    PlayMapper playMapper;

    public Game entitytoDto(GameEntity game, Boolean includePlays) {
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
        if (includePlays) {
            gameDto.setPlays(playMapper.entitesToDtos(game.getPlays()));
        }
        return gameDto;
    }

    public List<Game> entitesToDtos(List<GameEntity> ents, Boolean includePlays) {
        List<Game> games = new ArrayList<>();
        for (GameEntity ent : ents) {
            games.add(entitytoDto(ent, includePlays));
        }
        return games;
    }

}
