package nba.dao.repos;

import org.springframework.data.repository.CrudRepository;

import nba.dao.model.TeamBoxscoreStatEntity;

public interface TeamBoxscoreStatDAO extends CrudRepository<TeamBoxscoreStatEntity, Long> {

}
