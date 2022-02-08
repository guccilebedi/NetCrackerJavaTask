package utils;

import contracts.DigitalTelevision;
import contracts.MobileCommunication;
import contracts.WiredInternet;
import person.Person;
import person.Sex;
import repository.Repository;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import utils.validator.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;

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
        String[] temp = fileName.split("\\.");
        CSVWriter csvWriter = new CSVWriter(new FileWriter(temp[temp.length - 2] + "_validated." + temp[temp.length - 1]));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        ContractValidator contractValidator = new ContractValidator();
        DigitalTelevisionValidator digitalTelevisionValidator = new DigitalTelevisionValidator();
        MobileCommunicationValidator mobileCommunicationValidator = new MobileCommunicationValidator();
        WiredInternetValidator wiredInternetValidator = new WiredInternetValidator();
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            line = line[0].split(";");
            LocalDate dateStart;
            LocalDate dateEnd;
            LocalDate dateOfBirth;
            try {
                dateStart = LocalDate.parse(line[0], formatter);
            } catch (DateTimeParseException e) {
                dateStart = null;
            }
            try {
                dateEnd = LocalDate.parse(line[1], formatter);
            } catch (DateTimeParseException e) {
                dateEnd = null;
            }
            try {
                dateOfBirth = LocalDate.parse(line[4], formatter);
            } catch (DateTimeParseException e) {
                dateOfBirth = null;
            }
            Sex sex = null;
            if (line[5].equals("Male")) {
                sex = Sex.MALE;
            } else if (line[5].equals("Female")) {
                sex = Sex.FEMALE;
            }
            Person person = new Person(repository.generatePersonId(line[6]), line[3], dateOfBirth, sex, line[6]);
            int contractNumber;
            try {
                contractNumber = Integer.parseInt(line[2]);
            } catch (NumberFormatException e) {
                contractNumber = 0;
            }
            if (line[7].equals("Television")) {
                DigitalTelevision digitalTelevision;
                if (line.length == 9) {
                    digitalTelevision = new DigitalTelevision(repository.generateContractId(), dateStart, dateEnd, contractNumber, person, line[8]);
                } else {
                    digitalTelevision = new DigitalTelevision(repository.generateContractId(), dateStart, dateEnd, contractNumber, person, null);
                }
                repository.add(digitalTelevision);
                ValidationResult validationResult = contractValidator.validate(digitalTelevision, new ValidationResult());
                validationResult = digitalTelevisionValidator.validate(digitalTelevision, validationResult);
                csvWriter.writeAll(Collections.singleton(new String[]{Arrays.toString(line), String.valueOf(validationResult.getErrors())}));
            } else if (line[7].equals("Mobile")) {
                MobileCommunication mobileCommunication;
                if (line.length == 11) {
                    int minutes;
                    int messages;
                    int gigabytes;
                    try {
                        minutes = Integer.parseInt(line[8]);
                    } catch (NumberFormatException e) {
                        minutes = 0;
                    }
                    try {
                        messages = Integer.parseInt(line[9]);
                    } catch (NumberFormatException e) {
                        messages = 0;
                    }
                    try {
                        gigabytes = Integer.parseInt(line[10]);
                    } catch (NumberFormatException e) {
                        gigabytes = 0;
                    }
                    mobileCommunication = new MobileCommunication(repository.generateContractId(), dateStart, dateEnd, contractNumber, person, minutes, messages, gigabytes);
                } else {
                    mobileCommunication = new MobileCommunication(repository.generateContractId(), dateStart, dateEnd, contractNumber, person, 0, 0, 0);
                }
                repository.add(mobileCommunication);
                ValidationResult validationResult = contractValidator.validate(mobileCommunication, new ValidationResult());
                validationResult = mobileCommunicationValidator.validate(mobileCommunication, validationResult);
                csvWriter.writeAll(Collections.singleton(new String[]{Arrays.toString(line), String.valueOf(validationResult.getErrors())}));
            } else {
                WiredInternet wiredInternet;
                if (line.length == 9) {
                    int speed;
                    try {
                        speed = Integer.parseInt(line[8]);
                    } catch (NumberFormatException e) {
                        speed = 0;
                    }
                    wiredInternet = new WiredInternet(repository.generateContractId(), dateStart, dateEnd, contractNumber, person, speed);
                } else {
                    wiredInternet = new WiredInternet(repository.generateContractId(), dateStart, dateEnd, contractNumber, person, 0);
                }
                repository.add(wiredInternet);
                ValidationResult validationResult = contractValidator.validate(wiredInternet, new ValidationResult());
                validationResult = wiredInternetValidator.validate(wiredInternet, validationResult);
                csvWriter.writeAll(Collections.singleton(new String[]{Arrays.toString(line), String.valueOf(validationResult.getErrors())}));
            }
        }
        csvWriter.close();
        return repository;
    }
}
