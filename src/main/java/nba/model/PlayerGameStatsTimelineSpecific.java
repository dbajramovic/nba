package nba.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PlayerGameStatsTimelineSpecific implements Serializable {

    private static final long serialVersionUID = 1L;
    private Map<String, List<Long>> mapOfStats;
    /**
     * @return the mapOfStats
     */
    public Map<String, List<Long>> getMapOfStats() {
        return mapOfStats;
    }
    /**
     * @param mapOfStats the mapOfStats to set
     */
    public void setMapOfStats(Map<String, List<Long>> mapOfStats) {
        this.mapOfStats = mapOfStats;
    }

}
