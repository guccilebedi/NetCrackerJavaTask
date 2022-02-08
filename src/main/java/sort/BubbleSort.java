package sort;

import contracts.Contract;
import repository.Repository;

import java.util.Comparator;

public class BubbleSort implements ISorter{
    @Override
    public void sort(Repository repository, Comparator<Contract> contractComparator) {
        Contract[] tempRepository = repository.getRepository();
        for (int i = 0; i < repository.getSize() - 1; i++) {
            for (int j = 0; j < repository.getSize() - 1 - i; j++) {
                if (contractComparator.compare(tempRepository[j], tempRepository[j + 1]) > 0) {
                    Contract tempContract = tempRepository[j];
                    tempRepository[j] = tempRepository[j + 1];
                    tempRepository[j + 1] = tempContract;
                }
            }
        }
        repository.setRepository(tempRepository);
    }
}
