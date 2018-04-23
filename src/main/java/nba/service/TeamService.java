package nba.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nba.dao.model.GameEntity;
import nba.dao.model.ScheduleEntity;
import nba.dao.model.TeamEntity;
import nba.dao.repos.GameDAO;
import nba.dao.repos.ScheduleDAO;
import nba.dao.repos.TeamDAO;
import nba.mapper.GameMapper;
import nba.mapper.ScheduleMapper;
import nba.mapper.TeamMapper;
import nba.model.Schedule;
import nba.model.Team;

@Component
public class TeamService {

    @Autowired
    TeamDAO teamDAO;

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

    public List<String> getNicknamesOfTeams(Boolean onlyNbaFranchises) {
        return teamDAO.getNicknames(onlyNbaFranchises);
    }

    public List<Team> saveTeams(List<LinkedHashMap<String, Object>> teams, String year) {
        List<Team> teamList = new ArrayList<>();
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
            teamDAO.save(ent);
            teamList.add(teamMapper.entityToDto(ent));
        }
        return teamList;

    }

    public Schedule teamSchedule(String team, String year) {
        ScheduleEntity scheduleEnt = scheduleDAO.findByTeamAndYear(team, year);
        Schedule sch = scheduleMapper.entitytoDto(scheduleEnt);
        List<GameEntity> games = gameDAO.findBySchedule(scheduleEnt.getId());
        sch.setGames(gameMapper.entitesToDtos(games, false));
        return sch;
    }
}