package nba.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ScheduleGameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    private String homeAbrv;
    private String visitAbrv;
    private String gameId;
    private String date;
    private Boolean isLeaguePass;

    @ManyToOne
    @JoinColumn(name = "SCHEDULE")
    private ScheduleEntity schedule;

    @ManyToOne
    @JoinColumn(name = "GAME")
    private GameEntity game;

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
    }

    public ScheduleEntity getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleEntity schedule) {
        this.schedule = schedule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHomeAbrv() {
        return homeAbrv;
    }

    public void setHomeAbrv(String homeAbrv) {
        this.homeAbrv = homeAbrv;
    }

    public String getVisitAbrv() {
        return visitAbrv;
    }

    public void setVisitAbrv(String visitAbrv) {
        this.visitAbrv = visitAbrv;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getIsLeaguePass() {
        return isLeaguePass;
    }

    public void setIsLeaguePass(Boolean isLeaguePass) {
        this.isLeaguePass = isLeaguePass;
    }

}
