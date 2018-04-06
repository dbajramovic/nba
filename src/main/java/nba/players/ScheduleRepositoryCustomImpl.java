package nba.players;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class ScheduleRepositoryCustomImpl implements ScheduleRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ScheduleEntity findByTeamAndYear(String team, String year) {
        String query = "select schedule from ScheduleEntity schedule where schedule.team = :team and schedule.year = :year";
        TypedQuery<ScheduleEntity> query1 = entityManager.createQuery(query, ScheduleEntity.class).setParameter("team", team)
                .setParameter("year", year).setMaxResults(1);
        try {
            return query1.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
