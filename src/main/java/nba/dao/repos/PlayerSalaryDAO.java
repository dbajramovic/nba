package nba.dao.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import nba.dao.model.PlayerSalaryEntity;

@Service
public interface PlayerSalaryDAO extends CrudRepository<PlayerSalaryEntity, Long>, PlayerSalaryRepositoryCustom {

}
