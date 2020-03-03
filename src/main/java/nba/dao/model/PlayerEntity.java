package nba.dao.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    Long id;

    @Column(name = "FIRST_NAME")
    String firstName;

    @Column(name = "LAST_NAME")
    String lastName;

    @Column(name = "PERSON_ID")
    String personId;

    @Column(name = "JERSEY")
    String jersey;

    @Column(name = "IS_ACTIVE")
    Boolean isActive;

    @Column(name = "POS")
    String pos;

    @Column(name = "HEIGHT_FEET")
    Long heightFeet;

    @Column(name = "HEIGHT_INCHES")
    Long heightInches;

    @Column(name = "HEIGHT_METERS")
    BigDecimal heightMeters;

    @Column(name = "WEIGHT_POUNDS")
    Long weightPounds;

    @Column(name = "WEIGHT_KILOGRAMS")
    BigDecimal weightKilograms;

    @Column(name = "DATE_OF_BIRTH_UTC")
    LocalDateTime dateOfBirthUTC;

    @Column(name = "NBA_DEBUT_YEAR")
    String nbaDebutYear;

    @Column(name = "YEARS_PRO")
    Long yearsPro;

    @Column(name = "COLLEGE_NAME")
    String collegeName;

    @Column(name = "LAST_AFFILATION")
    String lastAffliation;

    @Column(name = "COUNTRY")
    String country;

    @Column(name = "YEAR")
    String year;

    @OneToMany(mappedBy = "player")
    List<PlayerTeamEntity> playerTeams;

    @OneToMany(mappedBy = "player")
    List<PlayerSalaryEntity> playerSalaries;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
