package nba.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(BoxscoreService.class);

    private static final String FASTBREAKPOINTS = "fastBreakPoints";
    private static final String LONGESTRUN = "longestRun";
    private static final String POINTSINTPAINT = "pointsInPaint";
    private static final String BIGGESTLEAD = "biggestLead";
    private static final String SECONDCHANCEPOINTS = "secondChancePoints";

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
        if (boxScoreMap != null) {
            if (boxScoreMap.get("timesTied") != null) {
                box.setTimesTied((String) boxScoreMap.get("timesTied"));
            }
            box.setLeadChanges((String) boxScoreMap.get("leadChanges"));
            box.setYear((String) boxScoreMap.get("year"));
            box.setGameId((String) boxScoreMap.get("gameId"));
            TeamBoxscoreStat hTeam = new TeamBoxscoreStat();
            TeamBoxscoreStat vTeam = new TeamBoxscoreStat();
            Map<String, Object> hMap = (HashMap<String, Object>) boxScoreMap.get("hTeam");
            Map<String, Object> vMap = (HashMap<String, Object>) boxScoreMap.get("vTeam");
            if (hMap != null && vMap != null) {
                mapTeamBoxscoreStat(boxScoreMap, hTeam, hMap);
                mapTeamBoxscoreStat(boxScoreMap, vTeam, vMap);
                box.sethTeam(hTeam);
                box.setvTeam(vTeam);
                box.setActivePlayers(new ArrayList<>());
                ArrayList<LinkedHashMap<String, Object>> players = (ArrayList<LinkedHashMap<String, Object>>) boxScoreMap
                        .get("activePlayers");
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
            }
        }
        return box;
    }

    @SuppressWarnings("unchecked")
    private void mapTeamBoxscoreStat(Map<String, Object> boxScoreMap, TeamBoxscoreStat hTeam, Map<String, Object> hMap) {
        if (MappingChecker.canBeParsedIntoLong((String) hMap.get(FASTBREAKPOINTS))) {
            hTeam.setFastBreakPoints(Long.parseLong((String) hMap.get(FASTBREAKPOINTS)));
        }
        if (MappingChecker.canBeParsedIntoLong((String) hMap.get(POINTSINTPAINT))) {
            hTeam.setPointsInPaint(Long.parseLong((String) hMap.get(POINTSINTPAINT)));
        }
        if (MappingChecker.canBeParsedIntoLong((String) hMap.get(BIGGESTLEAD))) {
            hTeam.setBiggestLead(Long.parseLong((String) hMap.get(BIGGESTLEAD)));
        }
        if (MappingChecker.canBeParsedIntoLong((String) hMap.get(SECONDCHANCEPOINTS))) {
            hTeam.setSecondChancePoints(Long.parseLong((String) hMap.get(SECONDCHANCEPOINTS)));
        }
        if (MappingChecker.canBeParsedIntoLong((String) hMap.get(LONGESTRUN))) {
            hTeam.setLongestRun(Long.parseLong((String) hMap.get(LONGESTRUN)));
        }
        hTeam.setTeamId((String) boxScoreMap.get("hTeamId"));
        hTeam.setTotals(pgsService.mapPGS((HashMap<String, Object>) hMap.get("totals")));
    }

}
