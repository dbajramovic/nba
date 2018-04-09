package nba.dao.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import nba.dao.model.TeamEntity;

@Service
public interface TeamDAO extends CrudRepository<TeamEntity, Long>, TeamRepositoryCustom {

}
