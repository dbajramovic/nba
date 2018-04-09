package nba.dao.repos;

import java.util.List;

import nba.dao.model.PlayerEntity;

public interface PlayerRepositoryCustom {
    public List<PlayerEntity> findByNameAndSurname(String name, String surname);

}
