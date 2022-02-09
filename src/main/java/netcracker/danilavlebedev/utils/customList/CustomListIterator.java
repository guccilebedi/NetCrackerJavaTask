package netcracker.danilavlebedev.utils.customList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomListIterator<E> implements Iterator<E> {

    /**
     * Current index of iterator.
     */
    private int index;

    /**
     * Array of list elements.
     */
    private final Object[] values;

    /**
     * Constructs an iterator.
     */
    public CustomListIterator(Object[] values) {
        this.index = 0;
        this.values = values;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return index != values.length;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     */
    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        return (E) values[index++];
    }
}
