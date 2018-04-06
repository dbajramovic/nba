package nba.players;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TeamMapper {

    public Team entityToDto(TeamEntity ent) {
        Team team = new Team();
        team.setAltCityName(ent.getAltCityName());
        team.setCity(ent.getCity());
        team.setConfName(ent.getConfName());
        team.setDivName(ent.getDivName());
        team.setFullName(ent.getFullName());
        team.setIsAllStar(ent.getIsAllStar());
        team.setIsNBAFranchise(ent.getIsNBAFranchise());
        team.setNickname(ent.getNickname());
        team.setTeamId(ent.getTeamId());
        team.setTricode(ent.getTricode());
        team.setUrlName(ent.getUrlName());
        team.setYear(ent.getYear());
        return team;
    }

    public List<Team> entitiesToDtos(List<TeamEntity> ents) {
        List<Team> teams = new ArrayList<>();
        for (TeamEntity ent : ents) {
            teams.add(entityToDto(ent));
        }
        return teams;
    }
}
