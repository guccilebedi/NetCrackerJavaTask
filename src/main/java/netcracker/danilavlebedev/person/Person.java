package netcracker.danilavlebedev.person;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import netcracker.danilavlebedev.utils.jaxb.LocalDateAdapter;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "fullName",
        "dateOfBirth",
        "sex",
        "idSeriesNumber"
})
public class Person {
    /*
     * @param id persons id
     * @param fullName persons full name
     * @param dateOfBirth persons date of birth
     * @param sex person sex (using enum Sex)
     * @param idSeries series of persons id
     * @param idNumber number of persons id
     */
    @XmlAttribute(required = true)
    private int id;
    @XmlElement(required = true)
    private String fullName;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @XmlElement(required = true)
    private LocalDate dateOfBirth;
    @XmlElement(required = true)
    private Sex sex;
    @XmlElement(required = true)
    private String idSeriesNumber;

    public Person() {

    }

    public Person(int id, String fullName, LocalDate dateOfBirth, Sex sex, String idSeriesNumber) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.idSeriesNumber = idSeriesNumber;
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

    public String getIdSeriesNumber() {
        return idSeriesNumber;
    }

    public void setIdSeriesNumber(String idSeriesNumber) {
        this.idSeriesNumber = idSeriesNumber;
    }

    @Override
    public String toString() {
        return " Person:" + id +
                ", " + fullName +
                ", " + dateOfBirth +
                ", " + sex +
                ", " + idSeriesNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && fullName.equals(person.fullName) && dateOfBirth.equals(person.dateOfBirth) && sex == person.sex && idSeriesNumber.equals(person.idSeriesNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, dateOfBirth, sex, idSeriesNumber);
    }
}
