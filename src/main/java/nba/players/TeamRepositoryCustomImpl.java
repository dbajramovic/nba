package nba.players;

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
                .setParameter("year", year);
        return query1.getSingleResult();
    }

}
