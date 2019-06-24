package nba.dao.repos;

import java.util.List;
import java.util.Set;

import nba.dao.model.TeamEntity;

public interface TeamRepositoryCustom {
    public TeamEntity findByTeamId(String teamId, String year);

    public List<TeamEntity> findTeamsOfPlayer(String player);

    public List<String> getNicknames(Boolean onlyNbaFranchises);

    TeamEntity findByTeamNickname(String nickname, String year);

    List<TeamEntity> findByTeamIds(Set<String> set, String year);

}
