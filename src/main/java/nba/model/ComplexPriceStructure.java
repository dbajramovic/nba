package nba.model;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ComplexPriceStructure implements Serializable {

    static final long serialVersionUID = 1L;
    Long cashPerPoint;
    Long cashForGame;

}
