package nba.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ScoreCooridinate implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long step;
    private Long homeScore;
    private Long visitorScore;
}
