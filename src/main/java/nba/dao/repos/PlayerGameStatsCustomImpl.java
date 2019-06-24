package nba.dao.repos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import nba.dao.model.PlayerGameStatsEntity;

public class PlayerGameStatsCustomImpl implements PlayerGameStatsCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<PlayerGameStatsEntity> findByPersonIds(List<String> playerIds) {
        String query = "select pgs from PlayerGameStatsEntity pgs where pgs.personId in :playerIds";
        TypedQuery<PlayerGameStatsEntity> query1 = entityManager.createQuery(query, PlayerGameStatsEntity.class).setParameter("playerIds",
                playerIds);
        return query1.getResultList();
    }

    @Override
    public List<PlayerGameStatsEntity> getPGSForTimeAndPlayer(List<String> playerIds, Long start, Long end) {
        String query = "select pgs from PlayerGameStatsEntity pgs where pgs.personId in :playerIds and pgs.boxscore in (select id from BoxscoreEntity box where box.gameId in (select distinct gameId from GameEntity g where cast(g.date as long) > :start and cast(g.date as long) < :end))";
        TypedQuery<PlayerGameStatsEntity> query1 = entityManager.createQuery(query, PlayerGameStatsEntity.class)
                .setParameter("playerIds", playerIds).setParameter("start", start).setParameter("end", end);
        return query1.getResultList();
    }

}
