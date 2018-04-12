package nba.model;

public class TeamBoxscoreStat {
    private Long fastBreakPoints;
    private Long pointsInPaint;
    private Long biggestLead;
    private Long secondChancePoints;
    private Long longestRun;
    private String teamId;

    private PlayerGameStats totals;

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

    public PlayerGameStats getTotals() {
        return totals;
    }

    public void setTotals(PlayerGameStats totals) {
        this.totals = totals;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TeamBoxscoreStat [fastBreakPoints=");
        builder.append(fastBreakPoints);
        builder.append(", pointsInPaint=");
        builder.append(pointsInPaint);
        builder.append(", biggestLead=");
        builder.append(biggestLead);
        builder.append(", secondChancePoints=");
        builder.append(secondChancePoints);
        builder.append(", longestRun=");
        builder.append(longestRun);
        builder.append(", teamId=");
        builder.append(teamId);
        builder.append(", totals=");
        builder.append(totals);
        builder.append("]");
        return builder.toString();
    }

}
