import Contracts.DigitalTelevision;
import Contracts.MobileCommunication;
import Contracts.WiredInternet;
import Person.Person;
import Person.Sex;
import Repository.Repository;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class RepositoryTests {
    Person person1 = new Person(1, "Person1", LocalDate.of(2002, 9, 23), Sex.MALE, "2017", "015203");
    Person person2 = new Person(2, "Person2", LocalDate.of(1988, 1, 1), Sex.FEMALE, "2008", "331721");
    Person person3 = new Person(3, "Person3", LocalDate.of(2000, 1, 27), Sex.MALE, "2020", "545239");
    MobileCommunication contract1 = new MobileCommunication(1, LocalDate.of(2021, 1, 3), LocalDate.of(2022, 1, 3), 1, person1, 400, 400, 40);
    WiredInternet contract2 = new WiredInternet(2, LocalDate.of(2020, 10, 5), LocalDate.of(2022, 10, 5), 1, person2, 100);
    DigitalTelevision contract3 = new DigitalTelevision(3, LocalDate.of(2021, 1, 3), LocalDate.of(2022, 1, 3), 1, person3, "Standard");

    /*
     * Tests add method
     */
    @Test
    public void testAdd() {
        Repository repository = new Repository();
        repository.add(contract1);
        repository.add(contract2);
        repository.add(contract3);
        Assert.assertEquals(3, repository.getSize());
    }

    /*
     * Tests sizeExtension method
     */
    @Test
    public void testSizeExtension() {
        Repository repository = new Repository();
        for (int i = 0; i < 20; i++) {
            repository.add(contract1);
        }
        Assert.assertEquals(20, repository.getSize());
    }

    /*
     * Tests getByID method
     */
    @Test
    public void testGetById() {
        Repository repository = new Repository();
        repository.add(contract1);
        repository.add(contract2);
        repository.add(contract3);
        Assert.assertEquals(contract1, repository.getById(1, MobileCommunication.class));
        Assert.assertEquals(contract2, repository.getById(2, WiredInternet.class));
        Assert.assertEquals(contract3, repository.getById(3, DigitalTelevision.class));
    }

    /*
     * Tests remove method
     */
    @Test
    public void testRemove() {
        Repository repository = new Repository();
        repository.add(contract1);
        repository.add(contract2);
        repository.add(contract3);
        repository.remove(1);
        Assert.assertNull(repository.getById(1, MobileCommunication.class));
        repository.remove(2);
        Assert.assertNull(repository.getById(2, WiredInternet.class));
        repository.remove(3);
        Assert.assertNull(repository.getById(3, DigitalTelevision.class));
    }
}
