package nba.players;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface TeamDAO extends CrudRepository<TeamEntity, Long> {

}
