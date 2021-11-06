package Repository;

import Contracts.Contract;

import java.util.Arrays;
import java.util.Objects;

public class Repository {
    /*
     * @param repository simple array for contracts
     * @param size number of contracts in a repository
     */
    private Contract[] repository = new Contract[16];
    private int size = 0;

    public Repository() {
    }

    /*
     * add method, after checking if the size is full,
     * adds new contract to the last cell of a repository
     * and increases @param size
     *
     * @param contract contract to add
     */
    public void add(Contract contract) {
        extendSize();
        repository[size] = contract;
        size++;
    }

    /*
    * getById method checks the equality of every
    * contract id in a repository to the given one
    *
    * @param id id of a contract which is needed to be found
    * @return contract with an equal id
     */
    public Contract getById(int id) {
        for (int i = 0; i < size; i++) {
            if (repository[i].getId() == id) {
                return repository[i];
            }
        }
        return null;
    }

    /*
    * remove method works the same as getById method,
    * but, if the contract with a given id was found,
    * it moves all repository elements after it
    * on one cell to the left
    *
    * @param id id of a contract which is needed to be removed
     */
    public void remove(int id) {
        for (int i = 0; i < size + 1; i++) {
            if (repository[i].getId() == id) {
                int j = i;
                while (j < size) {
                    repository[j] = repository[j + 1];
                    j++;
                }
                size--;
                break;
            }
        }
    }

    public int getSize() {
        return size;
    }

    /*
    * extendSize method checks if the number of
    * contracts in a repository is equal to
    * its real size and if so, it copies
    * all elements to a new array, which is
    * two times bigger than the old one
     */
    private void extendSize() {
        if (size == repository.length) {
            repository = Arrays.copyOf(repository, repository.length * 2);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return size == that.size && Arrays.equals(repository, that.repository);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(repository);
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(repository);
    }
}
