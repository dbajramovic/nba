package nba.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PlayerTeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private TeamEntity team;

    @ManyToOne
    @JoinColumn(name = "PLAYER_ID")
    private PlayerEntity player;

    @Column(name = "PLAYER_REF_ID")
    private String playerRefId;

    @Column(name = "TEAM_REF_ID")
    private String teamRefId;

    private String seasonStart;

    private String seasonEnd;

    public PlayerTeamEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public String getSeasonStart() {
        return seasonStart;
    }

    public void setSeasonStart(String seasonStart) {
        this.seasonStart = seasonStart;
    }

    public String getSeasonEnd() {
        return seasonEnd;
    }

    public void setSeasonEnd(String seasonEnd) {
        this.seasonEnd = seasonEnd;
    }

    public String getPlayerRefId() {
        return playerRefId;
    }

    public void setPlayerRefId(String playerRefId) {
        this.playerRefId = playerRefId;
    }

    public String getTeamRefId() {
        return teamRefId;
    }

    public void setTeamRefId(String teamRefId) {
        this.teamRefId = teamRefId;
    }

}
