package nba.dao.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import nba.dao.model.GameEntity;

@Service
public interface GameDAO extends CrudRepository<GameEntity, Long>, GameRepositoryCustom {

}
