package nba.dao.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class BoxscoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    private String timesTied;
    private String leadChanges;

    @OneToOne
    @JoinColumn(name = "HTEAM")
    private TeamBoxscoreStatEntity hTeam;

    @OneToOne
    @JoinColumn(name = "ATEAM")
    private TeamBoxscoreStatEntity vTeam;

    @OneToMany(mappedBy = "boxscore")
    private List<PlayerGameStatsEntity> activePlayers;

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

    public TeamBoxscoreStatEntity gethTeam() {
        return hTeam;
    }

    public void sethTeam(TeamBoxscoreStatEntity hTeam) {
        this.hTeam = hTeam;
    }

    public TeamBoxscoreStatEntity getvTeam() {
        return vTeam;
    }

    public void setvTeam(TeamBoxscoreStatEntity vTeam) {
        this.vTeam = vTeam;
    }

    public List<PlayerGameStatsEntity> getActivePlayers() {
        return activePlayers;
    }

    public void setActivePlayers(List<PlayerGameStatsEntity> activePlayers) {
        this.activePlayers = activePlayers;
    }

}
