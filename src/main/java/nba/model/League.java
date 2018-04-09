package nba.model;

import java.io.Serializable;
import java.util.List;

public class League implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Player> standard;

    public List<Player> getStandard() {
        return standard;
    }

    public void setStandard(List<Player> standard) {
        this.standard = standard;
    }

}
