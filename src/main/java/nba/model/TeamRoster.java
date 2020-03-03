package nba.model;

import java.util.List;

public class TeamRoster {

    private String name;
    private String year;
    private String logo;
    private List<Player> players;
    private String totalSalary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     * @return the logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * @param logo
     *            the logo to set
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * @return the totalSalary
     */
    public String getTotalSalary() {
        return totalSalary;
    }

    /**
     * @param totalSalary
     *            the totalSalary to set
     */
    public void setTotalSalary(String totalSalary) {
        this.totalSalary = totalSalary;
    }

}
