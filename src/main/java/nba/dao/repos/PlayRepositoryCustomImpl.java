package nba.dao.repos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import nba.dao.model.PlayEntity;

public class PlayRepositoryCustomImpl implements PlayRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<PlayEntity> findByGameId(Long gameId) {
        String query = "select play from PlayEntity play where play.game.id = :gameId order by play.event + 0 asc";
        TypedQuery<PlayEntity> query1 = entityManager.createQuery(query, PlayEntity.class).setParameter("gameId", gameId);
        return query1.getResultList();
    }

}
