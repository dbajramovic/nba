package nba.players;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class PlayerRepositoryCustomImpl implements PlayerRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<PlayerEntity> findByNameAndSurname(String name, String surname) {
        String query = "select player from PlayerEntity player where player.firstName = :name and player.lastName = :surname";
        TypedQuery<PlayerEntity> query1 = entityManager.createQuery(query.toString(), PlayerEntity.class).setParameter("name", name)
                .setParameter("surname", surname);
        return query1.getResultList();
    }

}
