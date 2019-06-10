package nba.dao.repos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import nba.dao.model.GameEntity;

public class GameRepositoryCustomImpl implements GameRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public GameEntity findGameForGameIdAndDate(String gameId, String date) {
        String query = "select game from GameEntity game where game.gameId = :gameId and game.date = :date";
        TypedQuery<GameEntity> query1 = entityManager.createQuery(query, GameEntity.class).setParameter("gameId", gameId)
                .setParameter("date", date).setMaxResults(1);
        try {
            return query1.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Map<String, String> findNewGames() {
        Map<String, String> map = new HashMap<>();
        String query = "select game from GameEntity game where game.gameId not in (select distinct p.game.gameId from PlayEntity p)";
        TypedQuery<GameEntity> query1 = entityManager.createQuery(query, GameEntity.class);
        List<GameEntity> list = query1.getResultList();
        for (GameEntity ent : list) {
            map.put(ent.getGameId(), ent.getDate());
        }
        return map;
    }

    @Override
    public GameEntity findByGameId(String gameId) {
        String query = "select game from GameEntity game where game.gameId = :gameId";
        TypedQuery<GameEntity> query1 = entityManager.createQuery(query, GameEntity.class).setParameter("gameId", gameId).setMaxResults(1);
        try {
            return query1.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<GameEntity> findBySchedule(Long id) {
        String query = "select game from GameEntity game where game.schedule.id = :id order by date + 0 asc";
        TypedQuery<GameEntity> query1 = entityManager.createQuery(query, GameEntity.class).setParameter("id", id);
        return query1.getResultList();
    }

    @Override
    public List<GameEntity> findByGameIds(Set<String> gameIds) {
        String query = "select game from GameEntity game where game.gameId in :gameIds";
        TypedQuery<GameEntity> query1 = entityManager.createQuery(query, GameEntity.class).setParameter("gameIds", gameIds);
        return query1.getResultList();
    }

}
