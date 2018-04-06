package nba.players;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PlayerMapper {

    public Player entityToDto(PlayerEntity ent) {
        Player player = new Player();
        player.setFirstName(ent.getFirstName());
        player.setLastAffliation(ent.getLastAffliation());
        player.setCollegeName(ent.getCollegeName());
        ;
        player.setLastName(ent.getLastName());
        player.setCountry(ent.getCountry());
        player.setDateOfBirthUTC(ent.getDateOfBirthUTC());
        player.setHeightFeet(ent.getHeightFeet());
        player.setHeightInches(ent.getHeightInches());
        player.setHeightMeters(ent.getHeightMeters());
        player.setWeightKilograms(ent.getWeightKilograms());
        player.setWeightPounds(ent.getWeightPounds());
        player.setIsActive(ent.getIsActive());
        player.setJersey(ent.getJersey());
        player.setNbaDebutYear(ent.getNbaDebutYear());
        player.setPos(ent.getPos());
        player.setYear(ent.getYear());
        return player;
    }

    public List<Player> entitiesToDtos(List<PlayerEntity> ents) {
        List<Player> players = new ArrayList<>();
        for (PlayerEntity ent : ents) {
            players.add(entityToDto(ent));
        }
        return players;
    }
}
