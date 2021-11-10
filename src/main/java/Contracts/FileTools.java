package Contracts;

import Person.Person;
import Person.Sex;
import Repository.Repository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileTools {
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
        String[] line1;
        String[] line2;
        while ((line1 = csvReader.readNext()) != null && (line2 = csvReader.readNext()) != null) {
            line1 = line1[0].split(";");
            line2 = line2[0].split(";");
            LocalDate dateStart = LocalDate.parse(line1[0], formatter);
            LocalDate dateEnd = LocalDate.parse(line1[1], formatter);
            Sex sex;
            if (line2[3].equals("Male")) {
                sex = Sex.MALE;
            } else {
                sex = Sex.FEMALE;
            }
            Person person = new Person(repository.generatePersonId(line2[3]), line2[1], LocalDate.parse(line2[2], formatter), sex, line2[4]);
            if (line2[5].equals("Television")) {
                repository.add(new DigitalTelevision(repository.generateContractId(), dateStart, dateEnd, Integer.parseInt(line2[0]), person, line2[6]));
            } else if (line2[5].equals("Mobile")) {
                repository.add(new MobileCommunication(repository.generateContractId(), dateStart, dateEnd, Integer.parseInt(line2[0]), person, Integer.parseInt(line2[6]), Integer.parseInt(line2[7]), Integer.parseInt(line2[8])));
            } else {
                repository.add(new WiredInternet(repository.generateContractId(), dateStart, dateEnd, Integer.parseInt(line2[0]), person, Integer.parseInt(line2[6])));
            }
        }
        return repository;
    }
}
