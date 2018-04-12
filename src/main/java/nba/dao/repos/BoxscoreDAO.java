package nba.dao.repos;

import org.springframework.data.repository.CrudRepository;

import nba.dao.model.BoxscoreEntity;

public interface BoxscoreDAO extends CrudRepository<BoxscoreEntity, Long> {

}
