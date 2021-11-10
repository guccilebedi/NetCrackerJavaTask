package Contracts;

import java.time.LocalDate;
import java.util.function.Predicate;

public class Predicates {
    public static Predicate<Contract> getIdPredicate(int id) {
        return contract -> contract.getId() == id;
    }

    public static Predicate<Contract> getDateStartPredicate(LocalDate dateStart) {
        return contract -> contract.getDateStart().equals(dateStart);
    }

    public static Predicate<Contract> getDateEndPredicate(LocalDate dateEnd) {
        return contract -> contract.getDateEnd().equals(dateEnd);
    }

    public static Predicate<Contract> getNumberPredicate(int number) {
        return contract -> contract.getNumber() == number;
    }

    public static Predicate<Contract> getOwnersIdPredicate(int id) {
        return contract -> contract.getPerson().getId() == id;
    }

    public static Predicate<Contract> getOwnersFullNamePredicate(String fullName) {
        return contract -> contract.getPerson().getFullName().equals(fullName);
    }
}
