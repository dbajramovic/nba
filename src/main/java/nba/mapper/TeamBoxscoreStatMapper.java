package nba.mapper;

import java.util.ArrayList;
import java.util.List;

import nba.dao.model.TeamBoxscoreStatEntity;
import nba.model.TeamBoxscoreStat;

public class TeamBoxscoreStatMapper {
    public TeamBoxscoreStat entitytoDto(TeamBoxscoreStatEntity boxEnt) {
        TeamBoxscoreStat boxStat = new TeamBoxscoreStat();
        boxStat.setBiggestLead(boxEnt.getBiggestLead());
        boxStat.setFastBreakPoints(boxEnt.getFastBreakPoints());
        boxStat.setLongestRun(boxEnt.getLongestRun());
        boxStat.setSecondChancePoints(boxEnt.getSecondChancePoints());
        return boxStat;
    }

    public List<TeamBoxscoreStat> entitesToDtos(List<TeamBoxscoreStatEntity> ents) {
        List<TeamBoxscoreStat> boxs = new ArrayList<>();
        for (TeamBoxscoreStatEntity ent : ents) {
            boxs.add(entitytoDto(ent));
        }
        return boxs;
    }
}
