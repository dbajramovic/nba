package nba.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class PlayerGameStatsTimeline implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<PlayerGameStats> stats;
    private LocalDateTime start;
    private LocalDateTime end;
    private Map<String, String> teamsInPeriod;
    private PlayerGameStats cumulativeStats;
    private PlayerGameStatsAdjusted cumulativeStatsAdjusted;

    public List<PlayerGameStats> getStats() {
        return stats;
    }

    public void setStats(List<PlayerGameStats> stats) {
        this.stats = stats;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Map<String, String> getTeamsInPeriod() {
        return teamsInPeriod;
    }

    public void setTeamsInPeriod(Map<String, String> teamsInPeriod) {
        this.teamsInPeriod = teamsInPeriod;
    }

    public PlayerGameStats getCumulativeStats() {
        return cumulativeStats;
    }

    public void setCumulativeStats(PlayerGameStats cumulativeStats) {
        this.cumulativeStats = cumulativeStats;
    }

    /**
     * @return the cumulativeStatsAdjusted
     */
    public PlayerGameStatsAdjusted getCumulativeStatsAdjusted() {
        return cumulativeStatsAdjusted;
    }

    /**
     * @param cumulativeStatsAdjusted
     *            the cumulativeStatsAdjusted to set
     */
    public void setCumulativeStatsAdjusted(PlayerGameStatsAdjusted cumulativeStatsAdjusted) {
        this.cumulativeStatsAdjusted = cumulativeStatsAdjusted;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PlayerGameStatsTimeline [stats=");
        builder.append(stats);
        builder.append(", start=");
        builder.append(start);
        builder.append(", end=");
        builder.append(end);
        builder.append(", teamsInPeriod=");
        builder.append(teamsInPeriod);
        builder.append(", cumulativeStats=");
        builder.append(cumulativeStats);
        builder.append(", cumulativeStatsAdjusted=");
        builder.append(cumulativeStatsAdjusted);
        builder.append("]");
        return builder.toString();
    }

}
