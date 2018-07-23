package nba.dao.repos;

import java.util.List;

import nba.dao.model.PlayerGameStatsEntity;

public interface PlayerGameStatsCustom {
    List<PlayerGameStatsEntity> findByPersonIds(List<String> playerIds);

}
