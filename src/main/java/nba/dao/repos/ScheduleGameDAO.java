package nba.dao.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import nba.dao.model.ScheduleGameEntity;

@Service
public interface ScheduleGameDAO extends CrudRepository<ScheduleGameEntity, Long> {

}
