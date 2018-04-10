package nba.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import nba.dao.model.ScheduleEntity;
import nba.model.Schedule;

@Service
public class ScheduleMapper {
    public Schedule entitytoDto(ScheduleEntity schEnt) {

        Schedule sch = new Schedule();
        sch.setTeam(schEnt.getTeam());
        sch.setYear(schEnt.getYear());
        return sch;
    }

    public List<Schedule> entitesToDtos(List<ScheduleEntity> ents) {
        List<Schedule> schedules = new ArrayList<>();
        for (ScheduleEntity ent : ents) {
            schedules.add(entitytoDto(ent));
        }
        return schedules;
    }
}
