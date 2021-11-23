package Utils;

import Contracts.DigitalTelevision;
import Contracts.MobileCommunication;
import Contracts.WiredInternet;
import Person.Person;
import Person.Sex;
import Repository.Repository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileUtils {
    /*
     * readFile method reads csv file into
     * a repository using opencsv
     *
     * @param fileName name of a file to read
     * @return repository the result repository
     */
    public static Repository readFile(String fileName) throws IOException, CsvValidationException {
        Repository repository = new Repository();
        CSVReader csvReader = new CSVReader(new FileReader(fileName));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            line = line[0].split(";");
            LocalDate dateStart = LocalDate.parse(line[0], formatter);
            LocalDate dateEnd = LocalDate.parse(line[1], formatter);
            Sex sex;
            if (line[5].equals("Male")) {
                sex = Sex.MALE;
            } else {
                sex = Sex.FEMALE;
            }
            Person person = new Person(repository.generatePersonId(line[6]), line[3], LocalDate.parse(line[4], formatter), sex, line[6]);
            if (line[7].equals("Television")) {
                repository.add(new DigitalTelevision(repository.generateContractId(), dateStart, dateEnd, Integer.parseInt(line[2]), person, line[8]));
            } else if (line[7].equals("Mobile")) {
                repository.add(new MobileCommunication(repository.generateContractId(), dateStart, dateEnd, Integer.parseInt(line[2]), person, Integer.parseInt(line[8]), Integer.parseInt(line[9]), Integer.parseInt(line[10])));
            } else {
                repository.add(new WiredInternet(repository.generateContractId(), dateStart, dateEnd, Integer.parseInt(line[2]), person, Integer.parseInt(line[8])));
            }
        }
        return repository;
    }
}
