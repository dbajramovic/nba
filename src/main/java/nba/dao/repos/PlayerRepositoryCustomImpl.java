package nba.dao.repos;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import nba.dao.model.PlayerEntity;
import nba.dao.model.PlayerTeamEntity;

public class PlayerRepositoryCustomImpl implements PlayerRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<PlayerEntity> findByNameAndSurname(String name, String surname) {
        String query = "select player from PlayerEntity player where player.firstName = :name and player.lastName = :surname";
        TypedQuery<PlayerEntity> query1 = entityManager.createQuery(query, PlayerEntity.class).setParameter("name", name)
                .setParameter("surname", surname);
        return query1.getResultList();
    }

    @Override
    public List<PlayerEntity> findByTeamId(String team, String year) {
        String query = "select player from PlayerEntity player where player.personId in (select plt.playerRefId from PlayerTeamEntity plt where plt.teamRefId = :team and plt.seasonEnd = :year)";
        TypedQuery<PlayerEntity> query1 = entityManager.createQuery(query, PlayerEntity.class).setParameter("team", team)
                .setParameter("year", year);
        return query1.getResultList();
    }

    @Override
    public List<PlayerTeamEntity> findByPlayerId(List<String> playerIds) {
        String query = "select playerTeam from PlayerTeamEntity playerTeam where playerTeam.playerRefId in :playerIds";
        TypedQuery<PlayerTeamEntity> query1 = entityManager.createQuery(query, PlayerTeamEntity.class).setParameter("playerIds", playerIds);
        return query1.getResultList();
    }

    @Override
    public List<PlayerEntity> findByPersonIds(Set<String> personIds) {
        String query = "select player from PlayerEntity player where player.personId in :personIds";
        TypedQuery<PlayerEntity> query1 = entityManager.createQuery(query, PlayerEntity.class).setParameter("personIds", personIds);
        return query1.getResultList();
    }

}
