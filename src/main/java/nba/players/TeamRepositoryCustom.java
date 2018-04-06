package nba.players;

import java.util.List;

public interface TeamRepositoryCustom {
    public TeamEntity findByTeamId(String teamId, String year);

    public List<TeamEntity> findTeamsOfPlayer(String player);

}
