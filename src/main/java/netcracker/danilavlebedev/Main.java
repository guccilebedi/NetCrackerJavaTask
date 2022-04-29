package netcracker.danilavlebedev;

import netcracker.danilavlebedev.contracts.Contract;
import netcracker.danilavlebedev.contracts.MobileCommunication;
import netcracker.danilavlebedev.person.Person;
import netcracker.danilavlebedev.person.Sex;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Person person1 = new Person(1, "Person1", LocalDate.of(2002, 9, 23), Sex.MALE, "2017015203");
        Contract contract = new MobileCommunication(1, LocalDate.of(2021, 1, 3), LocalDate.of(2022, 1, 3), 1, person1, 400, 400, 40);
        System.out.println(((MobileCommunication) contract).getGigabytes());
    }
}
