package nba.dao.repos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import nba.dao.model.PlayerEntity;

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

}
