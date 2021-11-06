import Person.Person;
import Person.Sex;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class PersonTests {

    Person person1 = new Person(1, "Person1", LocalDate.of(2002, 9, 23), Sex.MALE, "2017", "015203");
    Person person2 = new Person(2, "Person2", LocalDate.of(1988, 1, 1), Sex.FEMALE, "2008", "331721");
    Person person3 = new Person(3, "Person3", LocalDate.of(2000, 1, 27), Sex.MALE, "2020", "545239");

    @Test
    public void testGetAge() {
        Assert.assertEquals(19, person1.getAge());
        Assert.assertEquals(33, person2.getAge());
        Assert.assertEquals(21, person3.getAge());
    }
}
