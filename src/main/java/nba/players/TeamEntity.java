package nba.players;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;
    @Column(name = "IS_NBA_FRANCHISE")
    private Boolean isNBAFranchise;
    @Column(name = "IS_ALL_STAR")
    private Boolean isAllStar;
    @Column(name = "CITY")
    private String city;
    @Column(name = "ALT_CITY_NAME")
    private String altCityName;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "TRICODE")
    private String tricode;
    @Column(name = "NICKNAME")
    private String nickname;
    @Column(name = "URL_NAME")
    private String urlName;
    @Column(name = "TEAM_ID")
    private String teamId;
    @Column(name = "CONF_NAME")
    private String confName;
    @Column(name = "DIV_NAME")
    private String divName;
    @Column(name = "YEAR")
    private String year;

    @OneToMany(mappedBy = "team")
    private List<PlayerTeamEntity> playerTeams;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<PlayerTeamEntity> getPlayers() {
        return playerTeams;
    }

    public void setPlayers(List<PlayerTeamEntity> playerTeams) {
        this.playerTeams = playerTeams;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
