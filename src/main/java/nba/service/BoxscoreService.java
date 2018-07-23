package nba.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nba.dao.model.BoxscoreEntity;
import nba.dao.model.PlayerGameStatsEntity;
import nba.dao.model.TeamBoxscoreStatEntity;
import nba.dao.repos.BoxscoreDAO;
import nba.dao.repos.PlayerGameStatsDAO;
import nba.dao.repos.TeamBoxscoreStatDAO;
import nba.helpers.MappingChecker;
import nba.mapper.BoxscoreMapper;
import nba.mapper.PlayerGameStatsMapper;
import nba.mapper.TeamBoxscoreStatMapper;
import nba.model.Boxscore;
import nba.model.PlayerGameStats;
import nba.model.TeamBoxscoreStat;

@Component
public class BoxscoreService {

    @Autowired
    PlayerGameStatsService pgsService;

    @Autowired
    BoxscoreDAO boxDAO;

    @Autowired
    PlayerGameStatsDAO pgsDAO;

    @Autowired
    TeamBoxscoreStatDAO teamBoxscoreStatDAO;

    @Autowired
    BoxscoreMapper boxMapper;

    @Autowired
    TeamBoxscoreStatMapper teamBoxMapper;

    @Autowired
    PlayerGameStatsMapper pgsMapper;

    @Transactional(rollbackOn = Exception.class)
    @SuppressWarnings("unchecked")
    public Boxscore saveBoxscore(Map<String, Object> boxScoreMap) {
        Boxscore box = new Boxscore();
        box.setTimesTied((String) boxScoreMap.get("timesTied"));
        box.setLeadChanges((String) boxScoreMap.get("leadChanges"));
        box.setYear((String) boxScoreMap.get("year"));
        box.setGameId((String) boxScoreMap.get("gameId"));
        TeamBoxscoreStat hTeam = new TeamBoxscoreStat();
        TeamBoxscoreStat vTeam = new TeamBoxscoreStat();
        Map<String, Object> hMap = (HashMap<String, Object>) boxScoreMap.get("hTeam");
        if (MappingChecker.canBeParsedIntoLong((String) hMap.get("fastBreakPoints"))) {
            hTeam.setFastBreakPoints(Long.parseLong((String) hMap.get("fastBreakPoints")));
        }
        if (MappingChecker.canBeParsedIntoLong((String) hMap.get("pointsInPaint"))) {
            hTeam.setPointsInPaint(Long.parseLong((String) hMap.get("pointsInPaint")));
        }
        if (MappingChecker.canBeParsedIntoLong((String) hMap.get("biggestLead"))) {
            hTeam.setBiggestLead(Long.parseLong((String) hMap.get("biggestLead")));
        }
        if (MappingChecker.canBeParsedIntoLong((String) hMap.get("secondChancePoints"))) {
            hTeam.setSecondChancePoints(Long.parseLong((String) hMap.get("secondChancePoints")));
        }
        if (MappingChecker.canBeParsedIntoLong((String) hMap.get("longestRun"))) {
            hTeam.setLongestRun(Long.parseLong((String) hMap.get("longestRun")));
        }
        Map<String, Object> totalsHomeMap = (HashMap<String, Object>) hMap.get("totals");
        hTeam.setTotals(pgsService.mapPGS(totalsHomeMap));

        Map<String, Object> vMap = (HashMap<String, Object>) boxScoreMap.get("vTeam");
        if (MappingChecker.canBeParsedIntoLong((String) vMap.get("fastBreakPoints"))) {
            vTeam.setFastBreakPoints(Long.parseLong((String) vMap.get("fastBreakPoints")));
        }
        if (MappingChecker.canBeParsedIntoLong((String) vMap.get("pointsInPaint"))) {
            vTeam.setPointsInPaint(Long.parseLong((String) vMap.get("pointsInPaint")));
        }
        if (MappingChecker.canBeParsedIntoLong((String) vMap.get("biggestLead"))) {
            vTeam.setBiggestLead(Long.parseLong((String) vMap.get("biggestLead")));
        }
        if (MappingChecker.canBeParsedIntoLong((String) vMap.get("secondChancePoints"))) {
            vTeam.setSecondChancePoints(Long.parseLong((String) vMap.get("secondChancePoints")));
        }
        if (MappingChecker.canBeParsedIntoLong((String) vMap.get("longestRun"))) {
            vTeam.setLongestRun(Long.parseLong((String) vMap.get("longestRun")));
        }
        Map<String, Object> visitorsHomeMap = (HashMap<String, Object>) vMap.get("totals");
        vTeam.setTotals(pgsService.mapPGS(visitorsHomeMap));
        box.sethTeam(hTeam);
        box.setvTeam(vTeam);
        box.setActivePlayers(new ArrayList<>());
        ArrayList<LinkedHashMap<String, Object>> players = (ArrayList<LinkedHashMap<String, Object>>) boxScoreMap.get("activePlayers");
        for (LinkedHashMap<String, Object> stat : players) {
            box.getActivePlayers().add(pgsService.mapPGS(stat));
        }

        BoxscoreEntity boxEnt = boxDAO.save(boxMapper.dtoToEntity(box));
        for (PlayerGameStats pgs : box.getActivePlayers()) {
            PlayerGameStatsEntity ent = pgsMapper.dtoToEntity(pgs);
            ent.setBoxscore(boxEnt);
            pgsDAO.save(ent);
        }
        TeamBoxscoreStatEntity hTeamEnt = teamBoxMapper.dtoToEntity(hTeam);
        TeamBoxscoreStatEntity vTeamEnt = teamBoxMapper.dtoToEntity(vTeam);
        hTeamEnt.setBoxscore(boxEnt);
        vTeamEnt.setBoxscore(boxEnt);
        hTeamEnt.setTeamId((String) boxScoreMap.get("hTeamId"));
        vTeamEnt.setTeamId((String) boxScoreMap.get("vTeamId"));
        teamBoxscoreStatDAO.save(hTeamEnt);
        teamBoxscoreStatDAO.save(vTeamEnt);
        return box;
    }

}
