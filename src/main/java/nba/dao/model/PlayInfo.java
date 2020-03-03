package nba.dao.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import nba.helpers.PlayType;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayInfo {

    String player;
    String description;
    String outcome;
    String connectedAction;
    PlayType playType;

}
