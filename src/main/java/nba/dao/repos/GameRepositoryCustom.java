package nba.dao.repos;

import java.util.List;
import java.util.Map;
import java.util.Set;

import nba.dao.model.GameEntity;

public interface GameRepositoryCustom {

    GameEntity findGameForGameIdAndDate(String gameId, String date);

    Map<String, String> findNewGames();

    GameEntity findByGameId(String gameId);

    List<GameEntity> findBySchedule(Long id);

    List<GameEntity> findByGameIds(Set<String> gameIds);
}
