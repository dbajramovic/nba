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

@Entity
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PERSON_ID")
    private String personId;

    @Column(name = "JERSEY")
    private String jersey;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @Column(name = "POS")
    private String pos;

    @Column(name = "HEIGHT_FEET")
    private Long heightFeet;

    @Column(name = "HEIGHT_INCHES")
    private Long heightInches;

    @Column(name = "HEIGHT_METERS")
    private BigDecimal heightMeters;

    @Column(name = "WEIGHT_POUNDS")
    private Long weightPounds;

    @Column(name = "WEIGHT_KILOGRAMS")
    private BigDecimal weightKilograms;

    @Column(name = "DATE_OF_BIRTH_UTC")
    private LocalDateTime dateOfBirthUTC;

    @Column(name = "NBA_DEBUT_YEAR")
    private String nbaDebutYear;

    @Column(name = "YEARS_PRO")
    private Long yearsPro;

    @Column(name = "COLLEGE_NAME")
    private String collegeName;

    @Column(name = "LAST_AFFILATION")
    private String lastAffliation;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "YEAR")
    private String year;

    @OneToMany(mappedBy = "player")
    private List<PlayerTeamEntity> playerTeams;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getJersey() {
        return jersey;
    }

    public void setJersey(String jersey) {
        this.jersey = jersey;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public Long getHeightFeet() {
        return heightFeet;
    }

    public void setHeightFeet(Long heightFeet) {
        this.heightFeet = heightFeet;
    }

    public Long getHeightInches() {
        return heightInches;
    }

    public void setHeightInches(Long heightInches) {
        this.heightInches = heightInches;
    }

    public BigDecimal getHeightMeters() {
        return heightMeters;
    }

    public void setHeightMeters(BigDecimal heightMeters) {
        this.heightMeters = heightMeters;
    }

    public Long getWeightPounds() {
        return weightPounds;
    }

    public void setWeightPounds(Long weightPounds) {
        this.weightPounds = weightPounds;
    }

    public BigDecimal getWeightKilograms() {
        return weightKilograms;
    }

    public void setWeightKilograms(BigDecimal weightKilograms) {
        this.weightKilograms = weightKilograms;
    }

    public LocalDateTime getDateOfBirthUTC() {
        return dateOfBirthUTC;
    }

    public void setDateOfBirthUTC(LocalDateTime dateOfBirthUTC) {
        this.dateOfBirthUTC = dateOfBirthUTC;
    }

    public String getNbaDebutYear() {
        return nbaDebutYear;
    }

    public void setNbaDebutYear(String nbaDebutYear) {
        this.nbaDebutYear = nbaDebutYear;
    }

    public Long getYearsPro() {
        return yearsPro;
    }

    public void setYearsPro(Long yearsPro) {
        this.yearsPro = yearsPro;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getLastAffliation() {
        return lastAffliation;
    }

    public void setLastAffliation(String lastAffliation) {
        this.lastAffliation = lastAffliation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<PlayerTeamEntity> getPlayerTeams() {
        return playerTeams;
    }

    public void setPlayerTeams(List<PlayerTeamEntity> playerTeams) {
        this.playerTeams = playerTeams;
    }

}
