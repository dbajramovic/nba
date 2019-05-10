package nba.dao.repos;

import java.util.List;

import nba.dao.model.TeamEntity;

public interface TeamRepositoryCustom {
    public TeamEntity findByTeamId(String teamId, String year);

    public List<TeamEntity> findTeamsOfPlayer(String player);

    public List<String> getNicknames(Boolean onlyNbaFranchises);

    TeamEntity findByTeamNickname(String nickname, String year);

}
