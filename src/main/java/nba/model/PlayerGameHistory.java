package nba.model;

import java.io.Serializable;
import java.util.Map;

public class PlayerGameHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    private Player player;
    private Map<Long, PlayerGameStats> boxscoreMap;
    private Map<Long, PlayerGameStats> yearAverages;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Map<Long, PlayerGameStats> getBoxscoreMap() {
        return boxscoreMap;
    }

    public void setBoxscoreMap(Map<Long, PlayerGameStats> boxscoreMap) {
        this.boxscoreMap = boxscoreMap;
    }

    public Map<Long, PlayerGameStats> getYearAverages() {
        return yearAverages;
    }

    public void setYearAverages(Map<Long, PlayerGameStats> yearAverages) {
        this.yearAverages = yearAverages;
    }

}
