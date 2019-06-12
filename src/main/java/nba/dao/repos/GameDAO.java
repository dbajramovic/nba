package nba.dao.repos;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import nba.dao.model.GameEntity;

@Service
public interface GameDAO extends CrudRepository<GameEntity, Long>, GameRepositoryCustom {

}
