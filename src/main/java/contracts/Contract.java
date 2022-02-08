package contracts;

import person.Person;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Contract {
    /*
     * @param id contracts id
     * @param dateStart contracts starting date
     * @param dateEnd contracts ending date
     * @param number contracts number
     * @param person person, who has signed the contract
     */
    private int id;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private int number;
    private Person person;

    public Contract(int id, LocalDate dateStart, LocalDate dateEnd, int number, Person person) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.number = number;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "{Contract: " + id +
                ", " + dateStart +
                ", " + dateEnd +
                ", " + number +
                ", " + person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return id == contract.id && number == contract.number && dateStart.equals(contract.dateStart) && Objects.equals(dateEnd, contract.dateEnd) && person.equals(contract.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateStart, dateEnd, number, person);
    }
}
