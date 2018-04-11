package nba.helpers;

public enum PlayType {
    // @formatter:off
    SHOT("Shot"),
    REBOUND("Rebound"),
    TURNOVER("Turnover"),
    ASSIST("Assist"),
    LAYUP("Layup");
    // @formatter:on

    private String value;

    private PlayType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static PlayType byValue(String value) {
        if (value.equalsIgnoreCase(SHOT.value)) {
            return SHOT;
        }
        if (value.equalsIgnoreCase(REBOUND.value)) {
            return REBOUND;
        }
        if (value.equalsIgnoreCase(TURNOVER.value)) {
            return TURNOVER;
        }
        if (value.equalsIgnoreCase(REBOUND.value)) {
            return REBOUND;
        }

        if (value.equalsIgnoreCase(ASSIST.value)) {
            return ASSIST;
        }
        return null;
    }

    public static Boolean isPlayType(String value) {
        value = value.toUpperCase();
        if (value.equalsIgnoreCase(SHOT.value) || (value.equalsIgnoreCase(REBOUND.value)) || (value.equalsIgnoreCase(TURNOVER.value))
                || (value.equalsIgnoreCase(ASSIST.value)) || (value.equalsIgnoreCase(LAYUP.value))) {
            return true;
        }
        return false;
    }
}
