package nba.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nba.dao.model.GameEntity;
import nba.dao.model.PlayerEntity;
import nba.dao.model.ScheduleEntity;
import nba.dao.model.TeamEntity;
import nba.dao.repos.GameDAO;
import nba.dao.repos.PlayerDAO;
import nba.dao.repos.PlayerSalaryDAO;
import nba.dao.repos.ScheduleDAO;
import nba.dao.repos.TeamDAO;
import nba.mapper.GameMapper;
import nba.mapper.PlayerMapper;
import nba.mapper.ScheduleMapper;
import nba.mapper.TeamMapper;
import nba.model.Player;
import nba.model.Schedule;
import nba.model.Team;
import nba.model.TeamRoster;

@Component
public class TeamService {

    @Autowired
    TeamDAO teamDAO;

    @Autowired
    PlayerDAO playerDAO;

    @Autowired
    PlayerSalaryDAO playerSalaryDAO;

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    ScheduleDAO scheduleDAO;

    @Autowired
    ScheduleMapper scheduleMapper;

    @Autowired
    GameMapper gameMapper;

    @Autowired
    GameDAO gameDAO;

    @Autowired
    PlayerMapper playerMapper;

    public List<String> getNicknamesOfTeams(Boolean onlyNbaFranchises) {
        return teamDAO.getNicknames(onlyNbaFranchises);
    }

    public List<Team> saveTeams(List<LinkedHashMap<String, Object>> teams, String year) {
        List<Team> teamList = new ArrayList<>();
        List<TeamEntity> teamEnts = new ArrayList<>();
        for (LinkedHashMap<String, Object> t : teams) {
            TeamEntity ent = new TeamEntity();
            if (t.get("altCityName") != null) {
                ent.setAltCityName((String) t.get("altCityName"));
            }
            if (t.get("city") != null) {
                ent.setCity((String) t.get("city"));
            }
            if (t.get("IsNBAFranchise") != null) {
                ent.setConfName((String) t.get("IsNBAFranchise"));
            }
            if (t.get("divName") != null) {
                ent.setDivName((String) t.get("divName"));
            }
            if (t.get("fullName") != null) {
                ent.setFullName((String) t.get("fullName"));
            }
            if (t.get("isAllStar") != null) {
                ent.setIsAllStar((Boolean) t.get("isAllStar"));
            }
            if (t.get("isNBAFranchise") != null) {
                ent.setIsNBAFranchise((Boolean) t.get("isNBAFranchise"));
            }
            if (t.get("nickname") != null) {
                ent.setNickname((String) t.get("nickname"));
            }
            if (t.get("teamId") != null) {
                ent.setTeamId((String) t.get("teamId"));
            }
            if (t.get("tricode") != null) {
                ent.setTricode((String) t.get("tricode"));
            }
            if (t.get("urlName") != null) {
                ent.setUrlName((String) t.get("urlName"));
            }
            if (t.get("confName") != null) {
                ent.setConfName((String) t.get("confName"));
            }
            ent.setYear(year);
            teamEnts.add(ent);
            teamList.add(teamMapper.entityToDto(ent));
        }
        teamDAO.saveAll(teamEnts);
        return teamList;

    }

    public Schedule teamSchedule(String team, String year) {
        ScheduleEntity scheduleEnt = scheduleDAO.findByTeamAndYear(team, year);
        Schedule sch = scheduleMapper.entitytoDto(scheduleEnt);
        List<GameEntity> games = gameDAO.findBySchedule(scheduleEnt.getId());
        sch.setGames(gameMapper.entitesToDtos(games, false));
        return sch;
    }

    public List<Team> getAllTeams() {
        Iterable<TeamEntity> teamEnts = teamDAO.findAll();
        List<Team> teams = new ArrayList<>();
        List<String> teamIds = new ArrayList<>();
        for (TeamEntity e : teamEnts) {
            if (!teamIds.contains(e.getTeamId())) {
                teams.add(teamMapper.entityToDto(e));
                teamIds.add(e.getTeamId());
            }
        }
        return teams;
    }

    public TeamRoster getTeamRoster(String team, String year) {
        TeamRoster tRoster = new TeamRoster();
        tRoster.setYear(year);
        TeamEntity teamEnt = teamDAO.findByTeamNickname(team, year);
        List<PlayerEntity> playerEnts = playerDAO.findByTeamId(teamEnt.getTeamId(), year);
        tRoster.setName(teamEnt.getFullName());
        List<Player> players = new ArrayList<>();
        List<String> personIds = new ArrayList<>();
        for (PlayerEntity p : playerEnts) {
            if (!personIds.contains(p.getPersonId()) && year.equalsIgnoreCase(p.getYear())) {
                Player pEnt = playerMapper.entityToDto(p);
                setPlayerSalary(pEnt, year);
                players.add(pEnt);
                personIds.add(pEnt.getPersonId());
            }
        }
        tRoster.setPlayers(players);
        tRoster.setLogo(teamEnt.getLogoUrl());
        tRoster.setTotalSalary(createTotalSalary(tRoster));
        return tRoster;
    }

    private String createTotalSalary(TeamRoster tRoster) {
        Long salary = 0L;
        for (Player p : tRoster.getPlayers()) {
            salary += Long.parseLong(p.getSalary());
        }
        StringBuilder sb = new StringBuilder(salary.toString());
        for (int i = sb.toString().length(); i > 1; i--) {
            if (i % 3 == 0) {
                sb.insert(i, " ");
            }
        }
        sb.append("$");
        return sb.toString();
    }

    private void setPlayerSalary(Player p, String year) {
        Long salary = playerSalaryDAO.getPlayerSalary(p.getPersonId(), year);
        p.setSalary(salary.toString());
    }
}
