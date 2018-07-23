package nba.dao.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PlayerGameStatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String personId;
    private String teamId;
    private String isOnCourt;
    private Long points;
    private String pos;
    private String min;
    private Long fgm;
    private Long fga;
    private BigDecimal fgp;
    private Long ftm;
    private Long fta;
    private BigDecimal ftp;
    private Long tpm;
    private Long tpa;
    private BigDecimal tpp;
    private Long offReb;
    private Long defReb;
    private Long totReb;
    private Long assists;
    private Long pFouls;
    private Long steals;
    private Long turnovers;
    private Long blocks;
    private String plusMinus;
    private String dnp;

    @ManyToOne
    @JoinColumn(name = "BOXSCORE")
    private BoxscoreEntity boxscore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public Long getBoxscoreId() {
        return this.boxscore.getId();
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getIsOnCourt() {
        return isOnCourt;
    }

    public void setIsOnCourt(String isOnCourt) {
        this.isOnCourt = isOnCourt;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public PlayerGameStatsEntity getItself() {
        return this;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public Long getFgm() {
        return fgm;
    }

    public void setFgm(Long fgm) {
        this.fgm = fgm;
    }

    public Long getFga() {
        return fga;
    }

    public void setFga(Long fga) {
        this.fga = fga;
    }

    public BigDecimal getFgp() {
        return fgp;
    }

    public void setFgp(BigDecimal fgp) {
        this.fgp = fgp;
    }

    public Long getFtm() {
        return ftm;
    }

    public void setFtm(Long ftm) {
        this.ftm = ftm;
    }

    public Long getFta() {
        return fta;
    }

    public void setFta(Long fta) {
        this.fta = fta;
    }

    public BigDecimal getFtp() {
        return ftp;
    }

    public void setFtp(BigDecimal ftp) {
        this.ftp = ftp;
    }

    public Long getTpm() {
        return tpm;
    }

    public void setTpm(Long tpm) {
        this.tpm = tpm;
    }

    public Long getTpa() {
        return tpa;
    }

    public void setTpa(Long tpa) {
        this.tpa = tpa;
    }

    public BigDecimal getTpp() {
        return tpp;
    }

    public void setTpp(BigDecimal tpp) {
        this.tpp = tpp;
    }

    public Long getOffReb() {
        return offReb;
    }

    public void setOffReb(Long offReb) {
        this.offReb = offReb;
    }

    public Long getDefReb() {
        return defReb;
    }

    public void setDefReb(Long defReb) {
        this.defReb = defReb;
    }

    public Long getTotReb() {
        return totReb;
    }

    public void setTotReb(Long totReb) {
        this.totReb = totReb;
    }

    public Long getAssists() {
        return assists;
    }

    public void setAssists(Long assists) {
        this.assists = assists;
    }

    public Long getpFouls() {
        return pFouls;
    }

    public void setpFouls(Long pFouls) {
        this.pFouls = pFouls;
    }

    public Long getSteals() {
        return steals;
    }

    public void setSteals(Long steals) {
        this.steals = steals;
    }

    public Long getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(Long turnovers) {
        this.turnovers = turnovers;
    }

    public Long getBlocks() {
        return blocks;
    }

    public void setBlocks(Long blocks) {
        this.blocks = blocks;
    }

    public String getPlusMinus() {
        return plusMinus;
    }

    public void setPlusMinus(String plusMinus) {
        this.plusMinus = plusMinus;
    }

    public String getDnp() {
        return dnp;
    }

    public void setDnp(String dnp) {
        this.dnp = dnp;
    }

    public BoxscoreEntity getBoxscore() {
        return boxscore;
    }

    public void setBoxscore(BoxscoreEntity boxscore) {
        this.boxscore = boxscore;
    }

}
