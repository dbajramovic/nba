package nba.model;

import java.io.Serializable;

public class GameLight implements Serializable {

    private static final long serialVersionUID = 1L;
    private String matchDescription;
    private String gameId;
    private String gameUrl;
    private String arena;
    private String city;
    private String date;

    public GameLight(String gameId, String gameUrl, String arena, String city, String date) {
        super();
        this.gameId = gameId;
        this.gameUrl = gameUrl;
        this.setCity(city);
        this.setArena(arena);
        this.setDate(date);
        StringBuilder sb = new StringBuilder();
        sb.append(this.getDate().substring(0, 4));
        sb.append("/");
        sb.append(this.getDate().substring(4, 6));
        sb.append("/");
        sb.append(this.getDate().substring(6, 8));
        sb.append(" - ");
        sb.append(this.getArena());
        sb.append(" ");
        sb.append(this.getCity());
        sb.append(" - ");
        sb.append(this.getGameUrl().split("/")[1].substring(0, 3));
        sb.append("@");
        sb.append(this.getGameUrl().split("/")[1].substring(3, 6));
        this.setMatchDescription(sb.toString());
    }

    public String getMatchDescription() {
        return matchDescription;
    }

    public void setMatchDescription(String matchDescription) {
        this.matchDescription = matchDescription;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    /**
     * @return the gameUrl
     */
    public String getGameUrl() {
        return gameUrl;
    }

    /**
     * @param gameUrl
     *            the gameUrl to set
     */
    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
    }

    /**
     * @return the arena
     */
    public String getArena() {
        return arena;
    }

    /**
     * @param arena
     *            the arena to set
     */
    public void setArena(String arena) {
        this.arena = arena;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     *            the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

}
