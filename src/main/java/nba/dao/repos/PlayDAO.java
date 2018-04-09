package nba.dao.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import nba.dao.model.PlayEntity;

@Service
public interface PlayDAO extends CrudRepository<PlayEntity, Long>, PlayRepositoryCustom {

}
