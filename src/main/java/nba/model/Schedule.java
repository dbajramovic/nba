package nba.model;

import java.io.Serializable;

public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;
    private String team;
    private String year;

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

}
