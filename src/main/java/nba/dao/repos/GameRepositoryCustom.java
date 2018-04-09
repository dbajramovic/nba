package nba.dao.repos;

import java.util.Map;

import nba.dao.model.GameEntity;

public interface GameRepositoryCustom {

    GameEntity findGameForGameIdAndDate(String gameId, String date);

    Map<String, String> findNewGames();
}
