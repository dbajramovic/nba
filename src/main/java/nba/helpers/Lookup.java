package nba.helpers;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nba.dao.model.PlayerTeamEntity;
import nba.dao.repos.PlayerDAO;
import nba.model.Player;

@Service
public class Lookup {

    @Autowired
    PlayerDAO playerDAO;

    public void lookupPlayersTeams(List<Player> players) {
        List<PlayerTeamEntity> links = playerDAO.findByPlayerId(players.stream().map(Player::getPersonId).collect(Collectors.toList()));
        for (Player player : players) {
            List<PlayerTeamEntity> relevenetLinks = links.stream().filter(isPlayer(player.getPersonId())).collect(Collectors.toList());
            StringBuilder teams = new StringBuilder();
            for (PlayerTeamEntity pt : relevenetLinks) {
                teams.append(pt.getTeam().getNickname());
                teams.append(" (");
                teams.append(pt.getSeasonStart());
                teams.append("-");
                teams.append(pt.getSeasonEnd());
                teams.append(")");
            }
            player.setTeamName(teams.toString());
        }
    }

    private Predicate<? super PlayerTeamEntity> isPlayer(String personId) {
        return z -> z.getPlayer().getPersonId().equals(personId);
    }

}
