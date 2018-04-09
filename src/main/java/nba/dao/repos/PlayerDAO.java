package nba.dao.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import nba.dao.model.PlayerEntity;

@Service
public interface PlayerDAO extends CrudRepository<PlayerEntity, Long>, PlayerRepositoryCustom {

}
