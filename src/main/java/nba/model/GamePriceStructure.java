package nba.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class GamePriceStructure implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<PlayerSalaryPair> teamPriceMap;
    private List<PlayerSalaryPair> homeTeamPrice;
    private List<PlayerSalaryPair> visitorTeamPrice;
    private Map<String, ComplexPriceStructure> homeTeamPriceComplex;
    private Map<String, ComplexPriceStructure> visitorTeamPriceComplex;
    private String year;

    public Map<String, ComplexPriceStructure> getHomeTeamPriceComplex() {
        return homeTeamPriceComplex;
    }

    public void setHomeTeamPriceComplex(Map<String, ComplexPriceStructure> homeTeamPriceComplex) {
        this.homeTeamPriceComplex = homeTeamPriceComplex;
    }

    public Map<String, ComplexPriceStructure> getVisitorTeamPriceComplex() {
        return visitorTeamPriceComplex;
    }

    public void setVisitorTeamPriceComplex(Map<String, ComplexPriceStructure> visitorTeamPriceComplex) {
        this.visitorTeamPriceComplex = visitorTeamPriceComplex;
    }

    public List<PlayerSalaryPair> getTeamPriceMap() {
        return teamPriceMap;
    }

    public void setTeamPriceMap(List<PlayerSalaryPair> teamPriceMap) {
        this.teamPriceMap = teamPriceMap;
    }

    public List<PlayerSalaryPair> getHomeTeamPrice() {
        return homeTeamPrice;
    }

    public void setHomeTeamPrice(List<PlayerSalaryPair> homeTeamPrice) {
        this.homeTeamPrice = homeTeamPrice;
    }

    public List<PlayerSalaryPair> getVisitorTeamPrice() {
        return visitorTeamPrice;
    }

    public void setVisitorTeamPrice(List<PlayerSalaryPair> visitorTeamPrice) {
        this.visitorTeamPrice = visitorTeamPrice;
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

}
