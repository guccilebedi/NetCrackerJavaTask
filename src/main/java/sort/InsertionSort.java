package sort;

import contracts.Contract;
import repository.Repository;

import java.util.Comparator;

public class InsertionSort implements ISorter{
    @Override
    public void sort(Repository repository, Comparator<Contract> contractComparator) {
        Contract[] tempRepository = repository.getRepository();
        for (int i = 1; i < repository.getSize(); i++) {
            Contract key = tempRepository[i];
            int j = i - 1;
            while (j >= 0 && contractComparator.compare(tempRepository[j], key) > 0) {
                tempRepository[j + 1] = tempRepository[j];
                j = j - 1;
            }
            tempRepository[j + 1] = key;
        }
        repository.setRepository(tempRepository);
    }
}
