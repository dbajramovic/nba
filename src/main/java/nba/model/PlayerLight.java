package nba.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PlayerLight implements Serializable {

    private static final long serialVersionUID = 1L;
    String firstName;
    String lastName;
    String fullName;
    String personId;
    Long id;

}
