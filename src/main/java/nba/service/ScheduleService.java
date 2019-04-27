package nba.service;

import org.springframework.stereotype.Component;

import nba.dao.model.ScheduleEntity;

@Component
public class ScheduleService {

    public ScheduleEntity createSchedule(String nickname, String year) {
        ScheduleEntity schEntity = new ScheduleEntity();
        schEntity.setTeam(nickname);
        schEntity.setYear(year);
        return schEntity;
    }

}
