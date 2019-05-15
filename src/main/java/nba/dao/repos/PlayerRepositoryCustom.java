package nba.dao.repos;

import java.util.List;

import nba.dao.model.PlayerEntity;
import nba.dao.model.PlayerTeamEntity;

public interface PlayerRepositoryCustom {
    public List<PlayerEntity> findByNameAndSurname(String name, String surname);

    List<PlayerEntity> findByTeamId(String team);

    List<PlayerTeamEntity> findByPlayerId(List<String> playerIds);

}
