package Repository;

import Contracts.Contract;

import java.util.Arrays;
import java.util.Objects;

public class Repository {
    private Contract[] rep = new Contract[16];
    private int size = 0;

    public Repository() {
    }

    public void add(Contract contract) {
        extendSize();
        rep[size] = contract;
        size++;
    }

    public Contract get(int id) {
        for (int i = 0; i < rep.length; i++) {
            if (rep[i].getId() == id) {
                return rep[i];
            }
        }
        return null;
    }

    public void remove(int id) {
        for (int i = 0; i < rep.length; i++) {
            if (rep[i].getId() == id) {
                size--;
                int j = i;
                while (rep[j] != null && j < rep.length - 1) {
                    rep[j] = rep[j + 1];
                    j++;
                }
                break;
            }
        }
    }

    private void extendSize() {
        if (size == rep.length) {
            rep = Arrays.copyOf(rep, rep.length * 2);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return size == that.size && Arrays.equals(rep, that.rep);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(rep);
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(rep);
    }
}
