package Contracts;

import Person.Person;

import java.time.LocalDate;
import java.util.Objects;

public class DigitalTelevision extends Contract {
    /*
     * @param channelPackage type of channel package
     */
    private String channelPackage;

    public DigitalTelevision(int id, LocalDate dateStart, LocalDate dateEnd, int number, Person person, String channelPackage) {
        super(id, dateStart, dateEnd, number, person);
        this.channelPackage = channelPackage;
    }

    public String getChannelPackage() {
        return channelPackage;
    }

    public void setChannelPackage(String channelPackage) {
        this.channelPackage = channelPackage;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", " + channelPackage + "} \n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DigitalTelevision that = (DigitalTelevision) o;
        return channelPackage.equals(that.channelPackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), channelPackage);
    }
}
