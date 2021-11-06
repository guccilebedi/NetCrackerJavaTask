package Contracts;

import Person.Person;

import java.time.LocalDate;
import java.util.Objects;

public class MobileCommunication extends Contract {
    /*
     * @param minutes amount of minutes that a tariff offers
     * @param messages amount of SMS messages that a tariff offers
     * @param gigabytes amount of gigabytes that a tariff offers
     */
    private int minutes;
    private int messages;
    private int gigabytes;

    public MobileCommunication(int id, LocalDate dateStart, LocalDate dateEnd, int number, Person person, int minutes, int messages, int gigabytes) {
        super(id, dateStart, dateEnd, number, person);
        this.minutes = minutes;
        this.messages = messages;
        this.gigabytes = gigabytes;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getMessages() {
        return messages;
    }

    public void setMessages(int messages) {
        this.messages = messages;
    }

    public int getGigabytes() {
        return gigabytes;
    }

    public void setGigabytes(int gigabytes) {
        this.gigabytes = gigabytes;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", " + minutes +
                ", " + messages +
                ", " + gigabytes + "} \n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MobileCommunication that = (MobileCommunication) o;
        return minutes == that.minutes && messages == that.messages && gigabytes == that.gigabytes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), minutes, messages, gigabytes);
    }
}
