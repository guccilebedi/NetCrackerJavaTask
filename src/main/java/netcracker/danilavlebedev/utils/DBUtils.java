package netcracker.danilavlebedev.utils;

import netcracker.danilavlebedev.contracts.Contract;
import netcracker.danilavlebedev.contracts.DigitalTelevision;
import netcracker.danilavlebedev.contracts.MobileCommunication;
import netcracker.danilavlebedev.contracts.WiredInternet;
import netcracker.danilavlebedev.db.DBConnector;
import netcracker.danilavlebedev.db.DBQueries;
import netcracker.danilavlebedev.di.Singleton;
import netcracker.danilavlebedev.person.Person;
import netcracker.danilavlebedev.person.Sex;
import netcracker.danilavlebedev.repository.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

@Singleton
public class DBUtils {
    private static Person getPerson(int id, Connection connection) throws SQLException {
        PreparedStatement getPerson = connection.prepareStatement(DBQueries.getPerson());
        getPerson.setInt(1, id);
        try {
            ResultSet personSet = getPerson.executeQuery();
            getPerson.close();
            personSet.next();
            Person person = new Person(personSet.getInt("id"), personSet.getString("full_name"), personSet.getDate("date_of_birth").toLocalDate(), Objects.equals(personSet.getString("sex"), "Male") ? Sex.MALE : Sex.FEMALE, personSet.getString("id_series_number"));
            personSet.close();
            return person;
        } catch (SQLException e) {
            System.err.println("Error with getting person with id = " + id + "!");
            return null;
        }
    }

    private static Contract getContractById(int id, Connection connection) throws SQLException {
        PreparedStatement getContractById = connection.prepareStatement(DBQueries.getContractById());
        getContractById.setInt(1, id);
        try {
            ResultSet contractSet = getContractById.executeQuery();
            getContractById.close();
            if (!contractSet.next()) {
                return null;
            }
            LocalDate dateStart = contractSet.getDate("date_start").toLocalDate();
            LocalDate dateEnd = contractSet.getDate("date_end").toLocalDate();
            int number = contractSet.getInt("number");
            int personId = contractSet.getInt("person_id");
            String type = contractSet.getString("type");
            String[] properties = contractSet.getString("properties").split(";");
            Person person = getPerson(personId, connection);
            contractSet.close();
            if (person == null) {
                System.err.println("No person was found by id = " + personId + " given in this contract!");
                return null;
            }
            if (Objects.equals(type, "Television")) {
                return new DigitalTelevision(id, dateStart, dateEnd, number, person, properties[0]);
            } else if (Objects.equals(type, "Mobile")) {
                return new MobileCommunication(id, dateStart, dateEnd, number, person, Integer.parseInt(properties[0]), Integer.parseInt(properties[1]), Integer.parseInt(properties[2]));
            } else {
                return new WiredInternet(id, dateStart, dateEnd, number, person, Integer.parseInt(properties[0]));
            }
        } catch (SQLException e) {
            System.err.println("Error while getting contract with id = " + id + "!");
            return null;
        }
    }

    public static Repository getRepository(int maximumAmount) {
        try {
            Connection connection = DBConnector.connect();
            PreparedStatement getContracts;
            if (maximumAmount == 1) {
                getContracts = connection.prepareStatement(DBQueries.getContract());
            } else if (maximumAmount <= 0){
                return new Repository();
            } else {
                getContracts = connection.prepareStatement(DBQueries.getContracts());
            }
            Repository repository = new Repository();
            int counter = 0;
            int contractsAmount, id, number, personId;
            LocalDate dateStart, dateEnd;
            String type;
            String[] properties;
            ResultSet contractsCount = connection.prepareStatement(DBQueries.getContractsCount()).executeQuery();
            contractsCount.next();
            contractsAmount = contractsCount.getInt(1);
            contractsCount.close();
            while (counter < contractsAmount) {
                if (maximumAmount == 1) {
                    getContracts.setInt(1, counter + 1);
                } else {
                    getContracts.setInt(1, counter + 1);
                    getContracts.setInt(2, counter + maximumAmount);
                }
                ResultSet contractsSet = getContracts.executeQuery();
                while (contractsSet.next()) {
                    id = contractsSet.getInt("id");
                    dateStart = contractsSet.getDate("date_start").toLocalDate();
                    dateEnd = contractsSet.getDate("date_end").toLocalDate();
                    number = contractsSet.getInt("number");
                    personId = contractsSet.getInt("person_id");
                    type = contractsSet.getString("type");
                    properties = contractsSet.getString("properties").split(";");
                    Person person = getPerson(personId, connection);
                    if (person == null) {
                        break;
                    }
                    if (Objects.equals(type, "Television")) {
                        repository.add(new DigitalTelevision(id, dateStart, dateEnd, number, person, properties[0]), false);
                    } else if (Objects.equals(type, "Mobile")) {
                        repository.add(new MobileCommunication(id, dateStart, dateEnd, number, person, Integer.parseInt(properties[0]), Integer.parseInt(properties[1]), Integer.parseInt(properties[2])), false);
                    } else {
                        repository.add(new WiredInternet(id, dateStart, dateEnd, number, person, Integer.parseInt(properties[0])), false);
                    }
                }
                contractsSet.close();
                counter += maximumAmount;
            }
            getContracts.close();
            connection.close();
            return repository;
        } catch (SQLException e) {
            System.err.println("Error with getting repository!");
            return null;
        }
    }

    private static boolean addPerson(Person person, Connection connection) throws SQLException {
        PreparedStatement addPerson = connection.prepareStatement(DBQueries.addPerson());
        addPerson.setInt(1, person.getId());
        addPerson.setString(2, person.getFullName());
        addPerson.setString(3, person.getDateOfBirth().toString());
        addPerson.setString(4, person.getSex() == Sex.MALE ? "Male" : "Female");
        addPerson.setString(5, person.getIdSeriesNumber());
        try {
            addPerson.executeUpdate();
            addPerson.close();
            return true;
        } catch (SQLException e) {
            System.err.println("Error while adding person + " + person + "!");
            return false;
        }
    }

    public static void addContract(Contract contract) {
        try {
            Connection connection = DBConnector.connect();
            if (getContractById(contract.getId(), connection) != null) {
                System.err.println("Contract with id = " + contract.getId() + "already exists!");
                return;
            }
            if (getPerson(contract.getPerson().getId(), connection) == null) {
                boolean result = addPerson(contract.getPerson(), connection);
                if (!result) {
                    return;
                }
            }
            PreparedStatement addContract = connection.prepareStatement(DBQueries.addContract());
            addContract.setInt(1, contract.getId());
            addContract.setString(2, contract.getDateStart().toString());
            addContract.setString(3, contract.getDateEnd().toString());
            addContract.setInt(4, contract.getNumber());
            addContract.setInt(5, contract.getPerson().getId());
            addContract.setString(6, contract.getClass() == DigitalTelevision.class ? "Television" : contract.getClass() == MobileCommunication.class ? "Mobile" : "Internet");
            addContract.setString(7, contract.getClass() == DigitalTelevision.class ? ((DigitalTelevision) contract).getChannelPackage() : contract.getClass() == MobileCommunication.class ? ((MobileCommunication) contract).getMinutes() + ";" + ((MobileCommunication) contract).getMessages() + ";" + ((MobileCommunication) contract).getGigabytes() + ";" : String.valueOf(((WiredInternet) contract).getSpeed()));
            addContract.executeUpdate();
            addContract.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Error while adding contract " + contract + "!");
        }
    }

    private static void deletePerson(int id, Connection connection) throws SQLException {
        PreparedStatement deletePerson = connection.prepareStatement(DBQueries.deletePerson());
        deletePerson.setInt(1, id);
        try {
            deletePerson.executeUpdate();
            deletePerson.close();
        } catch (SQLException e) {
            System.err.println("Error while deleting person with id = " + id + "!");
        }
    }

    public static void deleteContract(int id) {
        try {
            Connection connection = DBConnector.connect();
            Contract contract = getContractById(id, connection);
            if (contract == null) {
                return;
            }
            PreparedStatement getContractsCountByPersonsId = connection.prepareStatement(DBQueries.getContractsCountByPersonsId());
            getContractsCountByPersonsId.setInt(1, contract.getPerson().getId());
            ResultSet contractsCountByPersonsId = getContractsCountByPersonsId.executeQuery();
            getContractsCountByPersonsId.close();
            contractsCountByPersonsId.next();
            PreparedStatement deleteContract = connection.prepareStatement(DBQueries.deleteContract());
            deleteContract.setInt(1, id);
            deleteContract.executeUpdate();
            if (contractsCountByPersonsId.getInt(1) == 0) {
                deletePerson(contract.getPerson().getId(), connection);
            }
            contractsCountByPersonsId.close();
            deleteContract.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Error while deleting contract with id = " + id + "!");
        }
    }
}
