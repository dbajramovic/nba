package nba.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PlayerSalaryPair implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private Long value;
    private String formattedValue;

}
