package nba.helpers;

public enum ShotDescriptor {
    // @formatter:off
    JUMP("JUMP"),
    FLOATING("FLOATING"),
    DRIVING("DRIVING"),
    RUNNING("RUNNING"),
    TURNAROUND("TURNAROUND"),
    PUTBACK("PUTBACK"),
    THREEPOINT("3PT"),
    LAYUP("LAYUP");
    // @formatter:on

    private String value;

    private ShotDescriptor(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Boolean isDescriptor(String value) {
        value = value.toUpperCase();
        if (value.equalsIgnoreCase(JUMP.value) || (value.equalsIgnoreCase(FLOATING.value)) || (value.equalsIgnoreCase(DRIVING.value))
                || (value.equalsIgnoreCase(RUNNING.value)) || (value.equalsIgnoreCase(PUTBACK.value)
                        || value.equalsIgnoreCase(THREEPOINT.value) || value.equalsIgnoreCase(TURNAROUND.value))) {
            return true;
        }
        return false;
    }
}
