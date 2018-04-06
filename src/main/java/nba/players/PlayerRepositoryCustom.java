package nba.players;

import java.util.List;

public interface PlayerRepositoryCustom {
    public List<PlayerEntity> findByNameAndSurname(String name, String surname);

}
