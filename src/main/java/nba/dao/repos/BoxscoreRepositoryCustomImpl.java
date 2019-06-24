package nba.dao.repos;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import nba.dao.model.BoxscoreEntity;

public class BoxscoreRepositoryCustomImpl implements BoxscoreRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<BoxscoreEntity> findByIds(Set<Long> ids) {
        String query = "select box from BoxscoreEntity box where box.id in :ids";
        TypedQuery<BoxscoreEntity> query1 = entityManager.createQuery(query, BoxscoreEntity.class).setParameter("ids", ids);
        return query1.getResultList();
    }

    @Override
    public List<String> findTeamIdsForGameId(String gameId) {
        String query = "select t.teamId from TeamBoxscoreStatEntity t, BoxscoreEntity box where box.gameId in :gameId and t.boxscore.id = box.id";
        TypedQuery<String> query1 = entityManager.createQuery(query, String.class).setParameter("gameId", gameId);
        return query1.getResultList();
    }

}
