package Sort;

import Contracts.Contract;

import java.util.Comparator;

public class Comparators {
    /*
     * different comparator realises
     */
    private final Comparator<Contract> idComparator = Comparator.comparingInt(Contract::getId);
    private final Comparator<Contract> dateStartComparator = Comparator.comparing(Contract::getDateStart);
    private final Comparator<Contract> dateEndComparator = Comparator.comparing(Contract::getDateEnd);
    private final Comparator<Contract> numberComparator = Comparator.comparingInt(Contract::getNumber);
    private final Comparator<Contract> ownersIdComparator = Comparator.comparing(o -> o.getPerson().getId());
    private final Comparator<Contract> ownersFullNameComparator = Comparator.comparing(o -> o.getPerson().getFullName());
    private final Comparator<Contract> ownersDateOfBirth = Comparator.comparing(o -> o.getPerson().getDateOfBirth());
    private final Comparator<Contract> ownersSex = Comparator.comparing(o -> o.getPerson().getSex());

    public Comparator<Contract> getIdComparator() {
        return idComparator;
    }

    public Comparator<Contract> getDateStartComparator() {
        return dateStartComparator;
    }

    public Comparator<Contract> getDateEndComparator() {
        return dateEndComparator;
    }

    public Comparator<Contract> getNumberComparator() {
        return numberComparator;
    }

    public Comparator<Contract> getOwnersIdComparator() {
        return ownersIdComparator;
    }

    public Comparator<Contract> getOwnersFullNameComparator() {
        return ownersFullNameComparator;
    }

    public Comparator<Contract> getOwnersDateOfBirth() {
        return ownersDateOfBirth;
    }

    public Comparator<Contract> getOwnersSex() {
        return ownersSex;
    }
}
