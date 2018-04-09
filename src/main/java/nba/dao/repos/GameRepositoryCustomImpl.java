package nba.dao.repos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        TypedQuery<GameEntity> query1 = entityManager.createQuery(query.toString(), GameEntity.class).setParameter("gameId", gameId)
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
        TypedQuery<GameEntity> query1 = entityManager.createQuery(query.toString(), GameEntity.class);
        List<GameEntity> list = query1.getResultList();
        for (GameEntity ent : list) {
            map.put(ent.getGameId(), ent.getDate());
        }
        return map;
    }

}
