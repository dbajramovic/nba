package nba.dao.repos;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nba.dao.model.PlayerSalaryEntity;

public class PlayerSalaryRepositoryCustomImpl implements PlayerSalaryRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Map<String, Long> createPrices(String teamId, String year, String seasonEnd) {
        String sql = "select p from PlayerSalaryEntity p where p.year in (:year) and p.personId in (select plt.playerRefId from PlayerTeamEntity plt where plt.teamRefId = :teamId and plt.team.confName != 'summer' and plt.seasonEnd = :year)";
        List<PlayerSalaryEntity> playerSalaries = entityManager.createQuery(sql, PlayerSalaryEntity.class).setParameter("teamId", teamId)
                .setParameter("year", Long.parseLong(year)).getResultList();
        return playerSalaries.stream()
                .collect(Collectors.toMap(PlayerSalaryEntity::getPersonId, PlayerSalaryEntity::getSalary, (p1, p2) -> {
                    return p1;
                }));
    }

    @Override
    public Long getPlayerSalary(String personId, String year) {
        String sql = "select p.salary from PlayerSalaryEntity p where p.year in (:year) and p.personId in :personId";
        Long salary = 0L;
        try {
            return entityManager.createQuery(sql, Long.class).setParameter("year", Long.parseLong(year)).setParameter("personId", personId)
                    .getSingleResult();
        } catch (Exception e) {
            return salary;
        }
    }

}
