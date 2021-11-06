package Contracts;

import Person.Person;

import java.time.LocalDate;
import java.util.Objects;

public class WiredInternet extends Contract{
    /*
    * @param speed highest internet speed that a tariff offers
     */
    private int speed;

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
