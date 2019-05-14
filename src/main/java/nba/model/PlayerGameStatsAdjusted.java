package nba.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class PlayerGameStatsAdjusted implements Serializable {

    private static final long serialVersionUID = 1L;
    private String personId;
    private String teamId;
    private String isOnCourt;
    private BigDecimal points;
    private String pos;
    private String min;
    private BigDecimal fgm;
    private BigDecimal fga;
    private BigDecimal fgp;
    private BigDecimal ftm;
    private BigDecimal fta;
    private BigDecimal ftp;
    private BigDecimal tpm;
    private BigDecimal tpa;
    private BigDecimal tpp;
    private BigDecimal offReb;
    private BigDecimal defReb;
    private BigDecimal totReb;
    private BigDecimal assists;
    private BigDecimal pFouls;
    private BigDecimal steals;
    private BigDecimal turnovers;
    private BigDecimal blocks;
    private String plusMinus;
    private String dnp;

    public String getPersonId() {
        return personId;
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

    public BigDecimal getPoints() {
        return points;
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
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

    public BigDecimal getFgm() {
        return fgm;
    }

    public void setFgm(BigDecimal fgm) {
        this.fgm = fgm;
    }

    public BigDecimal getFga() {
        return fga;
    }

    public void setFga(BigDecimal fga) {
        this.fga = fga;
    }

    public BigDecimal getFgp() {
        return fgp;
    }

    public void setFgp(BigDecimal fgp) {
        this.fgp = fgp;
    }

    public BigDecimal getFtm() {
        return ftm;
    }

    public void setFtm(BigDecimal ftm) {
        this.ftm = ftm;
    }

    public BigDecimal getFta() {
        return fta;
    }

    public void setFta(BigDecimal fta) {
        this.fta = fta;
    }

    public BigDecimal getFtp() {
        return ftp;
    }

    public void setFtp(BigDecimal ftp) {
        this.ftp = ftp;
    }

    public BigDecimal getTpm() {
        return tpm;
    }

    public void setTpm(BigDecimal tpm) {
        this.tpm = tpm;
    }

    public BigDecimal getTpa() {
        return tpa;
    }

    public void setTpa(BigDecimal tpa) {
        this.tpa = tpa;
    }

    public BigDecimal getTpp() {
        return tpp;
    }

    public void setTpp(BigDecimal tpp) {
        this.tpp = tpp;
    }

    public BigDecimal getOffReb() {
        return offReb;
    }

    public void setOffReb(BigDecimal offReb) {
        this.offReb = offReb;
    }

    public BigDecimal getDefReb() {
        return defReb;
    }

    public void setDefReb(BigDecimal defReb) {
        this.defReb = defReb;
    }

    public BigDecimal getTotReb() {
        return totReb;
    }

    public void setTotReb(BigDecimal totReb) {
        this.totReb = totReb;
    }

    public BigDecimal getAssists() {
        return assists;
    }

    public void setAssists(BigDecimal assists) {
        this.assists = assists;
    }

    public BigDecimal getpFouls() {
        return pFouls;
    }

    public void setpFouls(BigDecimal pFouls) {
        this.pFouls = pFouls;
    }

    public BigDecimal getSteals() {
        return steals;
    }

    public void setSteals(BigDecimal steals) {
        this.steals = steals;
    }

    public BigDecimal getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(BigDecimal turnovers) {
        this.turnovers = turnovers;
    }

    public BigDecimal getBlocks() {
        return blocks;
    }

    public void setBlocks(BigDecimal blocks) {
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

}
