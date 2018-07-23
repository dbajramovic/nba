package nba.model;

import java.util.List;

public class Boxscore {

    private String timesTied;
    private String leadChanges;
    private String year;
    private TeamBoxscoreStat hTeam;
    private TeamBoxscoreStat vTeam;
    private String gameId;
    private List<PlayerGameStats> activePlayers;

    public String getTimesTied() {
        return timesTied;
    }

    public void setTimesTied(String timesTied) {
        this.timesTied = timesTied;
    }

    public String getLeadChanges() {
        return leadChanges;
    }

    public void setLeadChanges(String leadChanges) {
        this.leadChanges = leadChanges;
    }

    public TeamBoxscoreStat gethTeam() {
        return hTeam;
    }

    public void sethTeam(TeamBoxscoreStat hTeam) {
        this.hTeam = hTeam;
    }

    public TeamBoxscoreStat getvTeam() {
        return vTeam;
    }

    public void setvTeam(TeamBoxscoreStat vTeam) {
        this.vTeam = vTeam;
    }

    public List<PlayerGameStats> getActivePlayers() {
        return activePlayers;
    }

    public void setActivePlayers(List<PlayerGameStats> activePlayers) {
        this.activePlayers = activePlayers;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Boxscore [timesTied=");
        builder.append(timesTied);
        builder.append(", leadChanges=");
        builder.append(leadChanges);
        builder.append(", hTeam=");
        builder.append(hTeam);
        builder.append(", vTeam=");
        builder.append(vTeam);
        builder.append(", activePlayers=");
        builder.append(activePlayers);
        builder.append("]");
        return builder.toString();
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year
     *            the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the gameId
     */
    public String getGameId() {
        return gameId;
    }

    /**
     * @param gameId the gameId to set
     */
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

}
