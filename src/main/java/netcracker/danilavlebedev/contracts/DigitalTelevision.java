package netcracker.danilavlebedev.contracts;

import jakarta.xml.bind.annotation.*;
import netcracker.danilavlebedev.person.Person;

import java.time.LocalDate;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "digitalTelevision")
public class DigitalTelevision extends Contract {
    /*
     * @param channelPackage type of channel package
     */
    @XmlElement(required = true)
    private String channelPackage;

    public DigitalTelevision() {

    }

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
