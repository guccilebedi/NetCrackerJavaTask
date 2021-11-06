package Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Person {
    /*
     * @param id persons id
     * @param fullName persons full name
     * @param dateOfBirth persons date of birth
     * @param sex person sex (using enum Sex)
     * @param idSeries series of persons id
     * @param idNumber number of persons id
     */
    private int id;
    private String fullName;
    private LocalDate dateOfBirth;
    private Sex sex;
    private String idSeries;
    private String idNumber;

    public Person(int id, String fullName, LocalDate dateOfBirth, Sex sex, String idSeries, String idNumber) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.idSeries = idSeries;
        this.idNumber = idNumber;
    }

    /*
    * getAge method returns the amount of years between
    * the @param dateOfBirth and the system date
    *
    * @return persons age
     */
    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
                ", " + fullName +
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
        return id == person.id && fullName.equals(person.fullName) && dateOfBirth.equals(person.dateOfBirth) && sex == person.sex && idSeries.equals(person.idSeries) && idNumber.equals(person.idNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, dateOfBirth, sex, idSeries, idNumber);
    }
}
