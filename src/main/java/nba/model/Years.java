package nba.model;

public enum Years {
    /** STARTYEAR */
    STARTYEAR("2012"),
    /** ENDYEAR */
    ENDYEAR("2019");

    private final String value;

    /**
     * 
     * @param value
     */
    private Years(final String value) {
        this.value = value;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

}
