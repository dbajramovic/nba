package nba.dao.repos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import nba.dao.model.TeamEntity;

public class TeamRepositoryCustomImpl implements TeamRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public TeamEntity findByTeamId(String teamId, String year) {
        String query = "select team from TeamEntity team where team.teamId = :teamId and team.year = :year";
        TypedQuery<TeamEntity> query1 = entityManager.createQuery(query, TeamEntity.class).setParameter("teamId", teamId)
                .setParameter("year", year).setMaxResults(1);
        return query1.getSingleResult();
    }

    @Override
    public TeamEntity findByTeamNickname(String nickname, String year) {
        String query = "select team from TeamEntity team where team.nickname = :nickname and team.year = :year";
        TypedQuery<TeamEntity> query1 = entityManager.createQuery(query, TeamEntity.class).setParameter("nickname", nickname)
                .setParameter("year", year).setMaxResults(1);
        return query1.getSingleResult();
    }

    @Override
    public List<TeamEntity> findTeamsOfPlayer(String player) {
        String query = "select team from TeamEntity team, PlayerTeamEntity plTeam where team.teamId = plTeam.teamRefId and plTeam.playerRefId = :player";
        TypedQuery<TeamEntity> query1 = entityManager.createQuery(query, TeamEntity.class).setParameter("player", player);
        return query1.getResultList();
    }

    @Override
    public List<String> getNicknames(Boolean onlyNbaFranchises) {
        String query = "select distinct team.nickname from TeamEntity team where team.isNBAFranchise = :onlyNbaFranchises";
        TypedQuery<String> query1 = entityManager.createQuery(query, String.class).setParameter("onlyNbaFranchises", onlyNbaFranchises);
        return query1.getResultList();
    }

}
