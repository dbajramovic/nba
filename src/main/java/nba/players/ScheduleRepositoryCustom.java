package nba.players;

public interface ScheduleRepositoryCustom {

    public ScheduleEntity findByTeamAndYear(String team, String year);

}
