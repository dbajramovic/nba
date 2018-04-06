package nba.players;

public interface TeamRepositoryCustom {
    public TeamEntity findByTeamId(String teamId, String year);

}
