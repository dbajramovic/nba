package nba.model;

import java.io.Serializable;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameScore implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<ScoreCooridinate> coordinates;
    private Long homePoints;
    private Long visitorPoints;
    private List<Long> periodPointsHome;
    private List<Long> periodPointsAway;
}
