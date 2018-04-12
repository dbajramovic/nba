package nba.dao.repos;

import org.springframework.data.repository.CrudRepository;

import nba.dao.model.PlayerGameStatsEntity;

public interface PlayerGameStatsDAO extends CrudRepository<PlayerGameStatsEntity, Long> {

}
