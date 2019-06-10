package nba.dao.repos;

import org.springframework.data.repository.CrudRepository;

import nba.dao.model.PlayerSalaryEntity;

public interface PlayerSalaryDAO extends CrudRepository<PlayerSalaryEntity, Long> {

}
