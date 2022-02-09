import netcracker.danilavlebedev.contracts.DigitalTelevision;
import netcracker.danilavlebedev.contracts.MobileCommunication;
import netcracker.danilavlebedev.contracts.SearchingPredicates;
import netcracker.danilavlebedev.contracts.WiredInternet;
import netcracker.danilavlebedev.di.Application;
import netcracker.danilavlebedev.di.ApplicationContext;
import netcracker.danilavlebedev.person.Person;
import netcracker.danilavlebedev.person.Sex;
import netcracker.danilavlebedev.repository.Repository;
import netcracker.danilavlebedev.sort.*;
import netcracker.danilavlebedev.utils.FileUtils;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class RepositoryTests {
    private static final ApplicationContext app = Application.run("netcracker.danilavlebedev", new HashMap<>(Map.of(ISorter.class, SelectionSort.class)));
    private static Repository repository;
    Person person1 = new Person(1, "Person1", LocalDate.of(2002, 9, 23), Sex.MALE, "2017015203");
    Person person2 = new Person(2, "Person2", LocalDate.of(1988, 1, 1), Sex.FEMALE, "2008331721");
    Person person3 = new Person(3, "Person3", LocalDate.of(2000, 1, 27), Sex.MALE, "2020545239");
    MobileCommunication contract1 = new MobileCommunication(1, LocalDate.of(2021, 1, 3), LocalDate.of(2022, 1, 3), 1, person1, 400, 400, 40);
    WiredInternet contract2 = new WiredInternet(2, LocalDate.of(2020, 10, 5), LocalDate.of(2022, 10, 5), 2, person2, 100);
    DigitalTelevision contract3 = new DigitalTelevision(3, LocalDate.of(2020, 1, 3), LocalDate.of(2022, 1, 3), 3, person3, "Standard");

    /*
     * Tests add method
     */
    @Test
    public void testAdd() {
        repository = app.getObject(Repository.class);
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
        repository = app.getObject(Repository.class);
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
        repository = app.getObject(Repository.class);
        repository.add(contract1);
        repository.add(contract2);
        repository.add(contract3);
        Assert.assertEquals(contract1, repository.getById(1, MobileCommunication.class));
        Assert.assertEquals(contract2, repository.getById(2, WiredInternet.class));
        Assert.assertEquals(contract3, repository.getById(3, DigitalTelevision.class));
    }

    /*
     * Tests search method
     */
    @Test
    public void testSearch() {
        repository = app.getObject(Repository.class);
        repository.add(contract1);
        repository.add(contract2);
        repository.add(contract3);
        Assert.assertEquals(contract1, repository.search(SearchingPredicates.getDateStartPredicate(LocalDate.of(2021, 1, 3)), MobileCommunication.class));
        Assert.assertEquals(contract2, repository.search(SearchingPredicates.getDateEndPredicate(LocalDate.of(2022, 10, 5)), WiredInternet.class));
        Assert.assertEquals(contract3, repository.search(SearchingPredicates.getOwnersFullNamePredicate("Person3"), DigitalTelevision.class));
    }

    /*
     * Tests remove method
     */
    @Test
    public void testRemove() {
        repository = app.getObject(Repository.class);
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

    /*
     * Tests bubble sort method
     */
    @Test
    public void testBubbleSort() {
        repository = app.getObject(Repository.class);
        BubbleSort bubbleSort = new BubbleSort();
        repository.add(contract3);
        repository.add(contract2);
        repository.add(contract1);
        bubbleSort.sort(repository, Comparators.getIdComparator());
        Assert.assertEquals(0, repository.getIndex(contract1));
        Assert.assertEquals(1, repository.getIndex(contract2));
        Assert.assertEquals(2, repository.getIndex(contract3));
    }

    /*
     * Tests insertion sort method
     */
    @Test
    public void testInsertionSort() {
        repository = app.getObject(Repository.class);
        InsertionSort insertionSort = new InsertionSort();
        repository.add(contract1);
        repository.add(contract2);
        repository.add(contract3);
        insertionSort.sort(repository, Comparators.getDateStartComparator());
        Assert.assertEquals(2, repository.getIndex(contract1));
        Assert.assertEquals(1, repository.getIndex(contract2));
        Assert.assertEquals(0, repository.getIndex(contract3));
    }

    /*
     * Tests selection sort method
     */
    @Test
    public void testSelectionSort() {
        repository = app.getObject(Repository.class);
        SelectionSort selectionSort = new SelectionSort();
        repository.add(contract3);
        repository.add(contract2);
        repository.add(contract1);
        selectionSort.sort(repository, Comparators.getOwnersFullNameComparator());
        Assert.assertEquals(0, repository.getIndex(contract1));
        Assert.assertEquals(1, repository.getIndex(contract2));
        Assert.assertEquals(2, repository.getIndex(contract3));
    }

    /*
     * Tests read csv file method
     */
    @Test
    public void testReadFile() throws CsvValidationException, IOException {
        repository = app.getObject(Repository.class);
        FileUtils fileUtils = new FileUtils();
        Repository repository = fileUtils.readFile("src/test/resources/input.csv");
        Assert.assertEquals(contract1, repository.getById(1, MobileCommunication.class));
        Assert.assertEquals(contract2, repository.getById(2, WiredInternet.class));
        Assert.assertEquals(contract3, repository.getById(3, DigitalTelevision.class));
    }

    /*
     * Tests validation in read csv file method
     */
    @Test
    public void testValidation() throws CsvValidationException, IOException {
        repository = app.getObject(FileUtils.class).readFile("src/test/resources/bad_input.csv");
        Assert.assertEquals(0, repository.getSize());
        repository = app.getObject(FileUtils.class).readFile("src/test/resources/input.csv");
        Assert.assertEquals(3, repository.getSize());
    }

    /*
     * Tests sorting with DI
     */
    @Test
    public void testDISort() {
        repository = app.getObject(Repository.class);
        repository.add(contract3);
        repository.add(contract2);
        repository.add(contract1);
        repository.sort(Comparators.getOwnersFullNameComparator());
        Assert.assertEquals(0, repository.getIndex(contract1));
        Assert.assertEquals(1, repository.getIndex(contract2));
        Assert.assertEquals(2, repository.getIndex(contract3));
    }
}
