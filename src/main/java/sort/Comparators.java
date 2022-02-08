package sort;

import contracts.Contract;

import java.util.Comparator;

public class Comparators {
    /*
     * different comparator realises
     */
    private static final Comparator<Contract> idComparator = Comparator.comparingInt(Contract::getId);
    private static final Comparator<Contract> dateStartComparator = Comparator.comparing(Contract::getDateStart);
    private static final Comparator<Contract> dateEndComparator = Comparator.comparing(Contract::getDateEnd);
    private static final Comparator<Contract> numberComparator = Comparator.comparingInt(Contract::getNumber);
    private static final Comparator<Contract> ownersIdComparator = Comparator.comparing(o -> o.getPerson().getId());
    private static final Comparator<Contract> ownersFullNameComparator = Comparator.comparing(o -> o.getPerson().getFullName());
    private static final Comparator<Contract> ownersDateOfBirth = Comparator.comparing(o -> o.getPerson().getDateOfBirth());
    private static final Comparator<Contract> ownersSex = Comparator.comparing(o -> o.getPerson().getSex());

    public static Comparator<Contract> getIdComparator() {
        return idComparator;
    }

    public static Comparator<Contract> getDateStartComparator() {
        return dateStartComparator;
    }

    public static Comparator<Contract> getDateEndComparator() {
        return dateEndComparator;
    }

    public static Comparator<Contract> getNumberComparator() {
        return numberComparator;
    }

    public static Comparator<Contract> getOwnersIdComparator() {
        return ownersIdComparator;
    }

    public static Comparator<Contract> getOwnersFullNameComparator() {
        return ownersFullNameComparator;
    }

    public static Comparator<Contract> getOwnersDateOfBirth() {
        return ownersDateOfBirth;
    }

    public static Comparator<Contract> getOwnersSex() {
        return ownersSex;
    }
}
