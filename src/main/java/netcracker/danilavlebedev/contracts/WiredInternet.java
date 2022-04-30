package netcracker.danilavlebedev.contracts;

import jakarta.xml.bind.annotation.*;
import netcracker.danilavlebedev.person.Person;

import java.time.LocalDate;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wiredInternet")
public class WiredInternet extends Contract {
    /*
     * @param speed highest internet speed that a tariff offers
     */
    @XmlElement(required = true)
    private int speed;

    public WiredInternet() {

    }

    public WiredInternet(int id, LocalDate dateStart, LocalDate dateEnd, int number, Person person, int speed) {
        super(id, dateStart, dateEnd, number, person);
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", " + speed + "} \n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WiredInternet that = (WiredInternet) o;
        return speed == that.speed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), speed);
    }
}
