package nba.model;

import java.io.Serializable;

public class Play implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String event;
    private String clock;
    private String description;
    private String playerCode;
    private String personId;
    private String homeScore;
    private String visitorScore;
    private String teamAbr;
    private String period;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlayerCode() {
        return playerCode;
    }

    public void setPlayerCode(String playerCode) {
        this.playerCode = playerCode;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(String homeScore) {
        this.homeScore = homeScore;
    }

    public String getVisitorScore() {
        return visitorScore;
    }

    public void setVisitorScore(String visitorScore) {
        this.visitorScore = visitorScore;
    }

    public String getTeamAbr() {
        return teamAbr;
    }

    public void setTeamAbr(String teamAbr) {
        this.teamAbr = teamAbr;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Play [id=");
        builder.append(id);
        builder.append(", event=");
        builder.append(event);
        builder.append(", clock=");
        builder.append(clock);
        builder.append(", description=");
        builder.append(description);
        builder.append(", playerCode=");
        builder.append(playerCode);
        builder.append(", personId=");
        builder.append(personId);
        builder.append(", homeScore=");
        builder.append(homeScore);
        builder.append(", visitorScore=");
        builder.append(visitorScore);
        builder.append(", teamAbr=");
        builder.append(teamAbr);
        builder.append(", period=");
        builder.append(period);
        builder.append(", getId()=");
        builder.append(getId());
        builder.append(", getEvent()=");
        builder.append(getEvent());
        builder.append(", getClock()=");
        builder.append(getClock());
        builder.append(", getDescription()=");
        builder.append(getDescription());
        builder.append(", getPlayerCode()=");
        builder.append(getPlayerCode());
        builder.append(", getPersonId()=");
        builder.append(getPersonId());
        builder.append(", getHomeScore()=");
        builder.append(getHomeScore());
        builder.append(", getVisitorScore()=");
        builder.append(getVisitorScore());
        builder.append(", getTeamAbr()=");
        builder.append(getTeamAbr());
        builder.append(", getPeriod()=");
        builder.append(getPeriod());
        builder.append(", getClass()=");
        builder.append(getClass());
        builder.append(", hashCode()=");
        builder.append(hashCode());
        builder.append(", toString()=");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }

}
