package nba.model;

import java.math.BigDecimal;

public class PlayerGameStats {

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

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PlayerGameStats [personId=");
        builder.append(personId);
        builder.append(", teamId=");
        builder.append(teamId);
        builder.append(", isOnCourt=");
        builder.append(isOnCourt);
        builder.append(", points=");
        builder.append(points);
        builder.append(", pos=");
        builder.append(pos);
        builder.append(", min=");
        builder.append(min);
        builder.append(", fgm=");
        builder.append(fgm);
        builder.append(", fga=");
        builder.append(fga);
        builder.append(", fgp=");
        builder.append(fgp);
        builder.append(", ftm=");
        builder.append(ftm);
        builder.append(", fta=");
        builder.append(fta);
        builder.append(", ftp=");
        builder.append(ftp);
        builder.append(", tpm=");
        builder.append(tpm);
        builder.append(", tpa=");
        builder.append(tpa);
        builder.append(", tpp=");
        builder.append(tpp);
        builder.append(", offReb=");
        builder.append(offReb);
        builder.append(", defReb=");
        builder.append(defReb);
        builder.append(", totReb=");
        builder.append(totReb);
        builder.append(", assists=");
        builder.append(assists);
        builder.append(", pFouls=");
        builder.append(pFouls);
        builder.append(", steals=");
        builder.append(steals);
        builder.append(", turnovers=");
        builder.append(turnovers);
        builder.append(", blocks=");
        builder.append(blocks);
        builder.append(", plusMinus=");
        builder.append(plusMinus);
        builder.append(", dnp=");
        builder.append(dnp);
        builder.append("]");
        return builder.toString();
    }

}
