package nba.model;

import java.io.Serializable;

public class PlayerSalaryPair implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private Long value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

}
