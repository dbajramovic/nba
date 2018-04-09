package nba.dao.repos;

import org.springframework.data.repository.CrudRepository;

import nba.dao.model.PlayerTeamEntity;

public interface PlayerTeamDAO extends CrudRepository<PlayerTeamEntity, Long> {

}
