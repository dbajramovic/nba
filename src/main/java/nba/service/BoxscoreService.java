package nba.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import nba.model.Boxscore;
import nba.model.TeamBoxscoreStat;

public class BoxscoreService {

    @Autowired
    PlayerGameStatsService pgsService;

    @SuppressWarnings("unchecked")
    public Boxscore saveBoxscore(Map<String, Object> boxScoreMap) {
        Boxscore box = new Boxscore();
        box.setTimesTied((String) boxScoreMap.get("timesTied"));
        box.setLeadChanges((String) boxScoreMap.get("leadChanges"));
        TeamBoxscoreStat hTeam = new TeamBoxscoreStat();
        TeamBoxscoreStat vTeam = new TeamBoxscoreStat();
        Map<String, Object> hMap = (HashMap<String, Object>) boxScoreMap.get("hTeam");
        hTeam.setFastBreakPoints(Long.parseLong((String) hMap.get("fastBreakPoints")));
        hTeam.setPointsInPaint(Long.parseLong((String) hMap.get("pointsInPaint")));
        hTeam.setBiggestLead(Long.parseLong((String) hMap.get("biggestLead")));
        hTeam.setSecondChancePoints(Long.parseLong((String) hMap.get("secondChancePoints")));
        hTeam.setLongestRun(Long.parseLong((String) hMap.get("longestRun")));

        Map<String, Object> totalsHomeMap = (HashMap<String, Object>) hMap.get("totals");
        hTeam.setTotals(pgsService.mapPGS(totalsHomeMap));

        Map<String, Object> vMap = (HashMap<String, Object>) boxScoreMap.get("vTeam");
        vTeam.setFastBreakPoints(Long.parseLong((String) vMap.get("fastBreakPoints")));
        vTeam.setPointsInPaint(Long.parseLong((String) vMap.get("pointsInPaint")));
        vTeam.setBiggestLead(Long.parseLong((String) vMap.get("biggestLead")));
        vTeam.setSecondChancePoints(Long.parseLong((String) vMap.get("secondChancePoints")));
        vTeam.setLongestRun(Long.parseLong((String) vMap.get("longestRun")));

        Map<String, Object> visitorsHomeMap = (HashMap<String, Object>) vMap.get("totals");
        vTeam.setTotals(pgsService.mapPGS(visitorsHomeMap));
        box.sethTeam(hTeam);
        box.setvTeam(vTeam);
        box.setActivePlayers(new ArrayList<>());
        ArrayList<LinkedHashMap<String, Object>> players = (ArrayList<LinkedHashMap<String, Object>>) boxScoreMap.get("activePlayers");
        for (LinkedHashMap<String, Object> stat : players) {
            box.getActivePlayers().add(pgsService.mapPGS(stat));
        }
        return box;
    }

}
