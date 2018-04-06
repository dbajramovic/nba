package nba.players;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface GameDAO extends CrudRepository<GameEntity, Long>, GameRepositoryCustom {

}
