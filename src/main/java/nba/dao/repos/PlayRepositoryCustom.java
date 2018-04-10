package nba.dao.repos;

import java.util.List;

import nba.dao.model.PlayEntity;

public interface PlayRepositoryCustom {
    List<PlayEntity> findByGameId(Long gameId);
}
