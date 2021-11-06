package Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Person {
    private int id;
    private String fio;
    private LocalDate dateOfBirth;
    private Sex sex;
    private String idSeries;
    private String idNumber;

    public Person(int id, String fio, LocalDate dateOfBirth, Sex sex, String idSeries, String idNumber) {
        this.id = id;
        this.fio = fio;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.idSeries = idSeries;
        this.idNumber = idNumber;
    }

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getIdSeries() {
        return idSeries;
    }

    public void setIdSeries(String idSeries) {
        this.idSeries = idSeries;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }


    @Override
    public String toString() {
        return "\n Person:" + id +
                ", " + fio +
                ", " + dateOfBirth +
                ", " + sex +
                ", " + idSeries +
                ", " + idNumber + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && fio.equals(person.fio) && dateOfBirth.equals(person.dateOfBirth) && sex == person.sex && idSeries.equals(person.idSeries) && idNumber.equals(person.idNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, dateOfBirth, sex, idSeries, idNumber);
    }
}
