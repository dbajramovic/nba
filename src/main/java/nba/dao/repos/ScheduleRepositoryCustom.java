package nba.dao.repos;

import nba.dao.model.ScheduleEntity;

public interface ScheduleRepositoryCustom {

    public ScheduleEntity findByTeamAndYear(String team, String year);

}
