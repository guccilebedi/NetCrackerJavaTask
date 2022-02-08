package sort;

import contracts.Contract;
import repository.Repository;

import java.util.Comparator;

public class SelectionSort implements ISorter{
    @Override
    public void sort(Repository repository, Comparator<Contract> contractComparator) {
        Contract[] tempRepository = repository.getRepository();
        for (int i = 0; i < repository.getSize() - 1; i++) {
            int lower_bound = i;
            for (int j = i + 1; j < repository.getSize(); j++) {
                if (contractComparator.compare(tempRepository[lower_bound], tempRepository[j]) > 0) {
                    lower_bound = j;
                }
                Contract tempContract = tempRepository[lower_bound];
                tempRepository[lower_bound] = tempRepository[i];
                tempRepository[i] = tempContract;
            }
        }
        repository.setRepository(tempRepository);
    }
}
