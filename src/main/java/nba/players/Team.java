package nba.players;

import java.io.Serializable;

public class Team implements Serializable {

    private static final long serialVersionUID = 1L;
    private Boolean isNBAFranchise;
    private Boolean isAllStar;
    private String city;
    private String altCityName;
    private String fullName;
    private String tricode;
    private String nickname;
    private String urlName;
    private String teamId;
    private String confName;
    private String divName;

    private String year;

    public Boolean getIsNBAFranchise() {
        return isNBAFranchise;
    }

    public void setIsNBAFranchise(Boolean isNBAFranchise) {
        this.isNBAFranchise = isNBAFranchise;
    }

    public Boolean getIsAllStar() {
        return isAllStar;
    }

    public void setIsAllStar(Boolean isAllStar) {
        this.isAllStar = isAllStar;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAltCityName() {
        return altCityName;
    }

    public void setAltCityName(String altCityName) {
        this.altCityName = altCityName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTricode() {
        return tricode;
    }

    public void setTricode(String tricode) {
        this.tricode = tricode;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getConfName() {
        return confName;
    }

    public void setConfName(String confName) {
        this.confName = confName;
    }

    public String getDivName() {
        return divName;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Team [isNBAFranchise=");
        builder.append(isNBAFranchise);
        builder.append(", isAllStar=");
        builder.append(isAllStar);
        builder.append(", city=");
        builder.append(city);
        builder.append(", altCityName=");
        builder.append(altCityName);
        builder.append(", fullName=");
        builder.append(fullName);
        builder.append(", tricode=");
        builder.append(tricode);
        builder.append(", nickname=");
        builder.append(nickname);
        builder.append(", urlName=");
        builder.append(urlName);
        builder.append(", teamId=");
        builder.append(teamId);
        builder.append(", confName=");
        builder.append(confName);
        builder.append(", divName=");
        builder.append(divName);
        builder.append(", year=");
        builder.append(year);
        builder.append("]");
        return builder.toString();
    }

}
