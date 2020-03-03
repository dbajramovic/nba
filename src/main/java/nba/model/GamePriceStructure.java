package nba.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GamePriceStructure implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<PlayerSalaryPair> teamPriceMap;
    private List<PlayerSalaryPair> homeTeamPrice;
    private List<PlayerSalaryPair> visitorTeamPrice;
    private ComplexPriceStructure homeTeamPriceComplex;
    private ComplexPriceStructure visitorTeamPriceComplex;
    private String year;

}
