package nba.dao.repos;

import java.util.List;
import java.util.Set;

import nba.dao.model.PlayerEntity;
import nba.dao.model.PlayerTeamEntity;

public interface PlayerRepositoryCustom {
    public List<PlayerEntity> findByNameAndSurname(String name, String surname);

    List<PlayerTeamEntity> findByPlayerId(List<String> playerIds);

    List<PlayerEntity> findByPersonIds(Set<String> personIds);

    List<PlayerEntity> findByTeamId(String team, String year);

}
