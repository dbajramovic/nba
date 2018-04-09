package nba.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
    private String personId;
    private String teamId;
    private String jersey;
    private Boolean isActive;
    private String pos;
    private Long heightFeet;
    private Long heightInches;
    private BigDecimal heightMeters;
    private Long weightPounds;
    private BigDecimal weightKilograms;
    private LocalDateTime dateOfBirthUTC;
    private String nbaDebutYear;
    private Long yearsPro;
    private String collegeName;
    private String lastAffliation;
    private String country;
    private Team team;
    private String year;

    public String getFirstName() {
        return firstName;
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

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((collegeName == null) ? 0 : collegeName.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((dateOfBirthUTC == null) ? 0 : dateOfBirthUTC.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((heightFeet == null) ? 0 : heightFeet.hashCode());
        result = prime * result + ((heightInches == null) ? 0 : heightInches.hashCode());
        result = prime * result + ((heightMeters == null) ? 0 : heightMeters.hashCode());
        result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
        result = prime * result + ((jersey == null) ? 0 : jersey.hashCode());
        result = prime * result + ((lastAffliation == null) ? 0 : lastAffliation.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((nbaDebutYear == null) ? 0 : nbaDebutYear.hashCode());
        result = prime * result + ((personId == null) ? 0 : personId.hashCode());
        result = prime * result + ((pos == null) ? 0 : pos.hashCode());
        result = prime * result + ((team == null) ? 0 : team.hashCode());
        result = prime * result + ((teamId == null) ? 0 : teamId.hashCode());
        result = prime * result + ((weightKilograms == null) ? 0 : weightKilograms.hashCode());
        result = prime * result + ((weightPounds == null) ? 0 : weightPounds.hashCode());
        result = prime * result + ((year == null) ? 0 : year.hashCode());
        result = prime * result + ((yearsPro == null) ? 0 : yearsPro.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Player other = (Player) obj;
        if (collegeName == null) {
            if (other.collegeName != null) {
                return false;
            }
        } else if (!collegeName.equals(other.collegeName)) {
            return false;
        }
        if (country == null) {
            if (other.country != null) {
                return false;
            }
        } else if (!country.equals(other.country)) {
            return false;
        }
        if (dateOfBirthUTC == null) {
            if (other.dateOfBirthUTC != null) {
                return false;
            }
        } else if (!dateOfBirthUTC.equals(other.dateOfBirthUTC)) {
            return false;
        }
        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }
        if (heightFeet == null) {
            if (other.heightFeet != null) {
                return false;
            }
        } else if (!heightFeet.equals(other.heightFeet)) {
            return false;
        }
        if (heightInches == null) {
            if (other.heightInches != null) {
                return false;
            }
        } else if (!heightInches.equals(other.heightInches)) {
            return false;
        }
        if (heightMeters == null) {
            if (other.heightMeters != null) {
                return false;
            }
        } else if (!heightMeters.equals(other.heightMeters)) {
            return false;
        }
        if (isActive == null) {
            if (other.isActive != null) {
                return false;
            }
        } else if (!isActive.equals(other.isActive)) {
            return false;
        }
        if (jersey == null) {
            if (other.jersey != null) {
                return false;
            }
        } else if (!jersey.equals(other.jersey)) {
            return false;
        }
        if (lastAffliation == null) {
            if (other.lastAffliation != null) {
                return false;
            }
        } else if (!lastAffliation.equals(other.lastAffliation)) {
            return false;
        }
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        if (nbaDebutYear == null) {
            if (other.nbaDebutYear != null) {
                return false;
            }
        } else if (!nbaDebutYear.equals(other.nbaDebutYear)) {
            return false;
        }
        if (personId == null) {
            if (other.personId != null) {
                return false;
            }
        } else if (!personId.equals(other.personId)) {
            return false;
        }
        if (pos == null) {
            if (other.pos != null) {
                return false;
            }
        } else if (!pos.equals(other.pos)) {
            return false;
        }
        if (team == null) {
            if (other.team != null) {
                return false;
            }
        } else if (!team.equals(other.team)) {
            return false;
        }
        if (teamId == null) {
            if (other.teamId != null) {
                return false;
            }
        } else if (!teamId.equals(other.teamId)) {
            return false;
        }
        if (weightKilograms == null) {
            if (other.weightKilograms != null) {
                return false;
            }
        } else if (!weightKilograms.equals(other.weightKilograms)) {
            return false;
        }
        if (weightPounds == null) {
            if (other.weightPounds != null) {
                return false;
            }
        } else if (!weightPounds.equals(other.weightPounds)) {
            return false;
        }
        if (year == null) {
            if (other.year != null) {
                return false;
            }
        } else if (!year.equals(other.year)) {
            return false;
        }
        if (yearsPro == null) {
            if (other.yearsPro != null) {
                return false;
            }
        } else if (!yearsPro.equals(other.yearsPro)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Player [firstName=");
        builder.append(firstName);
        builder.append(", lastName=");
        builder.append(lastName);
        builder.append(", personId=");
        builder.append(personId);
        builder.append(", teamId=");
        builder.append(teamId);
        builder.append(", jersey=");
        builder.append(jersey);
        builder.append(", isActive=");
        builder.append(isActive);
        builder.append(", pos=");
        builder.append(pos);
        builder.append(", heightFeet=");
        builder.append(heightFeet);
        builder.append(", heightInches=");
        builder.append(heightInches);
        builder.append(", heightMeters=");
        builder.append(heightMeters);
        builder.append(", weightPounds=");
        builder.append(weightPounds);
        builder.append(", weightKilograms=");
        builder.append(weightKilograms);
        builder.append(", dateOfBirthUTC=");
        builder.append(dateOfBirthUTC);
        builder.append(", nbaDebutYear=");
        builder.append(nbaDebutYear);
        builder.append(", yearsPro=");
        builder.append(yearsPro);
        builder.append(", collegeName=");
        builder.append(collegeName);
        builder.append(", lastAffliation=");
        builder.append(lastAffliation);
        builder.append(", country=");
        builder.append(country);
        builder.append(", team=");
        builder.append(team);
        builder.append(", year=");
        builder.append(year);
        builder.append("]");
        return builder.toString();
    }

}
