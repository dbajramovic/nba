package nba.model;

import java.io.Serializable;

public class PlayerSalary implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String personId;
    private Long year;
    private Long salary;
    private Long garanteedSalary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Long getGaranteedSalary() {
        return garanteedSalary;
    }

    public void setGaranteedSalary(Long garanteedSalary) {
        this.garanteedSalary = garanteedSalary;
    }

}
