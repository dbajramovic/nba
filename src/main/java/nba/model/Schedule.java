package nba.model;

import java.io.Serializable;
import java.util.List;

public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;
    private String team;
    private String year;

    private List<Game> games;

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

}
