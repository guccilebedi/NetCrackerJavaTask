package Sort;

import Contracts.Contract;
import Repository.Repository;

import java.util.Comparator;

public interface ISorter {
    void sort(Repository repository, Comparator<Contract> contractComparator);
}
