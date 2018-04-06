package nba.players;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class TeamRepositoryCustomImpl implements TeamRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public TeamEntity findByTeamId(String teamId, String year) {
        String query = "select team from TeamEntity team where team.teamId = :teamId and team.year = :year";
        TypedQuery<TeamEntity> query1 = entityManager.createQuery(query.toString(), TeamEntity.class).setParameter("teamId", teamId)
                .setParameter("year", year).setMaxResults(1);
        return query1.getSingleResult();
    }

    @Override
    public List<TeamEntity> findTeamsOfPlayer(String player) {
        String query = "select team from TeamEntity team, PlayerTeamEntity plTeam where team.teamId = plTeam.teamRefId and plTeam.playerRefId = :player";
        TypedQuery<TeamEntity> query1 = entityManager.createQuery(query.toString(), TeamEntity.class).setParameter("player", player);
        return query1.getResultList();
    }

}
