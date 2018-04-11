package nba.dao.model;

import nba.helpers.PlayType;

public class PlayInfo {

    private String player;
    private String description;
    private String outcome;
    private String connectedAction;
    private PlayType playType;

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public PlayType getPlayType() {
        return playType;
    }

    public void setPlayType(PlayType playType) {
        this.playType = playType;
    }

    public String getConnectedAction() {
        return connectedAction;
    }

    public void setConnectedAction(String connectedAction) {
        this.connectedAction = connectedAction;
    }

}
