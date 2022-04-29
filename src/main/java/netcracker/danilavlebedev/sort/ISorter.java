package netcracker.danilavlebedev.sort;

import netcracker.danilavlebedev.contracts.Contract;
import netcracker.danilavlebedev.repository.Repository;

import java.util.Comparator;

public interface ISorter {
    void sort(Repository repository, Comparator<Contract> contractComparator);
}
