package nba.dao.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    private String gameId;
    private String gameUrl;
    private String seasonId;
    private String date;
    private String time;
    private String arena;
    private String city;
    private String state;
    private String country;
    private String homeStartDate;
    private String homeStartTime;
    private String visitorStartDate;
    private String visitorStartTime;
    private String previewAvailable;
    private String recapAvailable;
    private String notebookAvailable;
    private String tntOt;

    public String getTntOt() {
        return tntOt;
    }

    public void setTntOt(String tntOt) {
        this.tntOt = tntOt;
    }

    @ManyToOne
    @JoinColumn(name = "schedule")
    private ScheduleEntity schedule;

    public ScheduleEntity getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleEntity schedule) {
        this.schedule = schedule;
    }

    @OneToMany(mappedBy = "game")
    private List<PlayEntity> plays;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameUrl() {
        return gameUrl;
    }

    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
    }

    public String getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(String seasonId) {
        this.seasonId = seasonId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getArena() {
        return arena;
    }

    public void setArena(String arena) {
        this.arena = arena;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHomeStartDate() {
        return homeStartDate;
    }

    public void setHomeStartDate(String homeStartDate) {
        this.homeStartDate = homeStartDate;
    }

    public String getHomeStartTime() {
        return homeStartTime;
    }

    public void setHomeStartTime(String homeStartTime) {
        this.homeStartTime = homeStartTime;
    }

    public String getVisitorStartDate() {
        return visitorStartDate;
    }

    public void setVisitorStartDate(String visitorStartDate) {
        this.visitorStartDate = visitorStartDate;
    }

    public String getVisitorStartTime() {
        return visitorStartTime;
    }

    public void setVisitorStartTime(String visitorStartTime) {
        this.visitorStartTime = visitorStartTime;
    }

    public String getPreviewAvailable() {
        return previewAvailable;
    }

    public void setPreviewAvailable(String previewAvailable) {
        this.previewAvailable = previewAvailable;
    }

    public String getRecapAvailable() {
        return recapAvailable;
    }

    public void setRecapAvailable(String recapAvailable) {
        this.recapAvailable = recapAvailable;
    }

    public String getNotebookAvailable() {
        return notebookAvailable;
    }

    public void setNotebookAvailable(String notebookAvailable) {
        this.notebookAvailable = notebookAvailable;
    }

    public String getTnt_ot() {
        return tntOt;
    }

    public void setTnt_ot(String tnt_ot) {
        this.tntOt = tnt_ot;
    }

    public List<PlayEntity> getPlays() {
        return plays;
    }

    public void setPlays(List<PlayEntity> plays) {
        this.plays = plays;
    }

}
