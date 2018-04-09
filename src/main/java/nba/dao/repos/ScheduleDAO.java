package nba.dao.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import nba.dao.model.ScheduleEntity;

@Service
public interface ScheduleDAO extends CrudRepository<ScheduleEntity, Long>, ScheduleRepositoryCustom {

}
