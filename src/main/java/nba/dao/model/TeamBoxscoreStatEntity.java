package nba.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class TeamBoxscoreStatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    private Long fastBreakPoints;
    private Long pointsInPaint;
    private Long biggestLead;
    private Long secondChancePoints;
    private Long longestRun;

    @OneToOne
    @JoinColumn(name = "BOXSCORE_ID")
    private BoxscoreEntity boxscore;

    public Long getFastBreakPoints() {
        return fastBreakPoints;
    }

    public void setFastBreakPoints(Long fastBreakPoints) {
        this.fastBreakPoints = fastBreakPoints;
    }

    public Long getPointsInPaint() {
        return pointsInPaint;
    }

    public void setPointsInPaint(Long pointsInPaint) {
        this.pointsInPaint = pointsInPaint;
    }

    public Long getBiggestLead() {
        return biggestLead;
    }

    public void setBiggestLead(Long biggestLead) {
        this.biggestLead = biggestLead;
    }

    public Long getSecondChancePoints() {
        return secondChancePoints;
    }

    public void setSecondChancePoints(Long secondChancePoints) {
        this.secondChancePoints = secondChancePoints;
    }

    public Long getLongestRun() {
        return longestRun;
    }

    public void setLongestRun(Long longestRun) {
        this.longestRun = longestRun;
    }

    public BoxscoreEntity getBoxscore() {
        return boxscore;
    }

    public void setBoxscore(BoxscoreEntity boxscore) {
        this.boxscore = boxscore;
    }

}
