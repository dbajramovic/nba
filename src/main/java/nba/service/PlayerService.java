package nba.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nba.dao.model.PlayerEntity;
import nba.dao.model.PlayerTeamEntity;
import nba.dao.model.TeamEntity;
import nba.dao.repos.PlayerDAO;
import nba.dao.repos.PlayerTeamDAO;
import nba.dao.repos.TeamDAO;
import nba.helpers.MappingChecker;
import nba.mapper.PlayerMapper;
import nba.mapper.TeamMapper;
import nba.model.Player;
import nba.model.Team;

@Component
public class PlayerService {

    @Autowired
    PlayerDAO playerDAO;

    @Autowired
    TeamDAO teamDAO;

    @Autowired
    PlayerTeamDAO playerTeamDAO;

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    PlayerMapper playerMapper;

    public List<Team> teamsOfPlayer(String name, String surname) {
        Map<String, TeamEntity> map = new HashMap<>();
        List<TeamEntity> teams = new ArrayList<>();
        List<PlayerEntity> players = playerDAO.findByNameAndSurname(name, surname);
        List<String> refIds = players.parallelStream().map(PlayerEntity::getPersonId).distinct().collect(Collectors.toList());
        for (String refId : refIds) {
            teams.addAll(teamDAO.findTeamsOfPlayer(refId));
        }
        for (TeamEntity team : teams) {
            map.put(team.getTeamId(), team);
        }
        teams.clear();
        for (Map.Entry<String, TeamEntity> entry : map.entrySet()) {
            teams.add(entry.getValue());
        }
        return teamMapper.entitiesToDtos(teams);
    }

    @Transactional(rollbackOn = Exception.class)
    public List<Player> savePlayers(ArrayList<LinkedHashMap<String, Object>> players, String year) {
        List<Player> playerList = new ArrayList<>();
        for (LinkedHashMap<String, Object> t : players) {
            PlayerEntity ent = new PlayerEntity();

            if (t.get("firstName") != null) {
                ent.setFirstName((String) t.get("firstName"));
            }
            if (t.get("lastName") != null) {
                ent.setLastName((String) t.get("lastName"));
            }
            if (t.get("personId") != null) {
                ent.setPersonId((String) t.get("personId"));
            }
            if (t.get("jersey") != null) {
                ent.setJersey((String) t.get("jersey"));
            }
            if (t.get("pos") != null) {
                ent.setPos((String) t.get("pos"));
            }
            if (t.get("isActive") != null) {
                ent.setIsActive((Boolean) t.get("isActive"));
            }
            if (MappingChecker.canBeParsedIntoLong((String) t.get("heightFeet"))) {
                ent.setHeightFeet(Long.parseLong((String) t.get("heightFeet")));
            }
            if (MappingChecker.canBeParsedIntoLong((String) t.get("heightInches"))) {
                ent.setHeightInches(Long.parseLong((String) t.get("heightInches")));
            }
            if (MappingChecker.canBeParsedIntoBigDecimal((String) t.get("heightMeters"))) {
                ent.setHeightMeters(new BigDecimal((String) t.get("heightMeters")));
            }
            if (MappingChecker.canBeParsedIntoLong((String) t.get("weightPounds"))) {
                ent.setWeightPounds(Long.parseLong((String) t.get("weightPounds")));
            }
            if (MappingChecker.canBeParsedIntoBigDecimal((String) t.get("weightKilograms"))) {
                ent.setWeightKilograms(new BigDecimal((String) t.get("weightKilograms")));
            }
            if (t.get("dateOfBirthUTC") != null && !((String) t.get("dateOfBirthUTC")).equalsIgnoreCase("")) {
                String dob = (String) t.get("dateOfBirthUTC");
                String yearDob = dob.split("-")[0];
                String monthDob = dob.split("-")[1];
                String dayDob = dob.split("-")[2];
                LocalDateTime dateTime = LocalDateTime.now().withYear(Integer.parseInt(yearDob)).withMonth(Integer.parseInt(monthDob))
                        .withDayOfMonth(Integer.parseInt(dayDob));
                ent.setDateOfBirthUTC(dateTime);
            }
            if (t.get("nbaDebutYear") != null) {
                ent.setNbaDebutYear((String) t.get("nbaDebutYear"));
            }
            if (MappingChecker.canBeParsedIntoLong((String) t.get("yearsPro"))) {
                ent.setYearsPro(Long.parseLong((String) t.get("yearsPro")));
            }
            if (t.get("collegeName") != null) {
                ent.setCollegeName((String) t.get("collegeName"));
            }
            if (t.get("lastAffliation") != null) {
                ent.setLastAffliation((String) t.get("lastAffliation"));
            }
            if (t.get("country") != null) {
                ent.setCountry((String) t.get("country"));
            }
            ent.setYear(year);
            PlayerEntity player = playerDAO.save(ent);
            playerList.add(playerMapper.entityToDto(player));
            if (t.get("teams") != null) {
                @SuppressWarnings("unchecked")
                ArrayList<LinkedHashMap<String, Object>> teams = (ArrayList<LinkedHashMap<String, Object>>) t.get("teams");
                for (LinkedHashMap<String, Object> t1 : teams) {
                    TeamEntity team = teamDAO.findByTeamId((String) t1.get("teamId"), year);
                    PlayerTeamEntity playerTeam = new PlayerTeamEntity();
                    playerTeam.setPlayer(player);
                    playerTeam.setSeasonStart((String) t1.get("seasonStart"));
                    playerTeam.setSeasonEnd((String) t1.get("seasonEnd"));
                    playerTeam.setTeam(team);
                    playerTeam.setPlayerRefId(player.getPersonId());
                    playerTeam.setTeamRefId(team.getTeamId());
                    playerTeamDAO.save(playerTeam);
                }
            }
        }
        return playerList;
    }

}
