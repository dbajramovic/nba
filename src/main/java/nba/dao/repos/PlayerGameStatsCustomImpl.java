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

}
