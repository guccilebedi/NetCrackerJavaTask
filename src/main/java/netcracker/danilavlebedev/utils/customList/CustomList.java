package netcracker.danilavlebedev.utils.customList;

import java.util.Arrays;
import java.util.Iterator;

public class CustomList<E> implements Iterable<E> {

    /**
     * Shared empty array instance used for empty instances.
     */
    private static final Object[] EMPTY_ELEMENT_DATA = {};

    /**
     * The array buffer into which the elements of the SimpleList are stored.
     */
    private Object[] elementData;

    /**
     * Constructs an empty list.
     */
    public CustomList() {
        this.elementData = EMPTY_ELEMENT_DATA;
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *                                  is negative
     */
    public CustomList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    /**
     * Constructs a list containing the elements of the specified list.
     */
    public CustomList(CustomList<? extends E> list) {
        Object[] temp = list.toArray();

        if (temp.length != 0) {
            elementData = temp;
        } else {
            elementData = EMPTY_ELEMENT_DATA;
        }
    }

    /**
     * Constructs a list containing the elements of the specified array.
     */
    public CustomList(E[] array) {
        if (array.length != 0) {
            elementData = array;
        } else {
            elementData = EMPTY_ELEMENT_DATA;
        }
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return elementData.length;
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param e element to be appended to this list
     * @return {@code true}
     */
    public boolean add(E e) {
        grow(elementData.length + 1);
        elementData[elementData.length - 1] = e;

        return true;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if index < 0 or index > {@code size()} - 1
     */
    public E get(int index) {
        return elementData(index);
    }

    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted to this list
     * @throws IndexOutOfBoundsException if index < 0 or index > {@code size()}
     */
    public void add(int index, E element) {
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());

        grow(elementData.length + 1);

        System.arraycopy(elementData, index,
                elementData, index + 1,
                size() - 1 - index);
        elementData[index] = element;
    }


    /**
     * Inserts the specified element at the specified position in this
     * list. The element in this position replaced by the passed element
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted to this list
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if index < 0 or index > {@code size()} - 1
     */
    public E set(int index, Object element) {
        if (index < 0 || index > size() - 1)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());

        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present. If the list does not contain the element, it is unchanged
     *
     * @param e element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     */
    public boolean remove(E e) {
        final int index = indexOfRange(e, 0, size());

        if (index == -1) {
            return false;
        } else {
            System.arraycopy(elementData, index + 1, elementData, index, size() - 1 - index);
            elementData = Arrays.copyOf(elementData, size() - 1);
            return true;
        }
    }

    /**
     * Removes the element at the specified position
     *
     * @param index index at which the specified element is to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if index < 0 or index > {@code size()} - 1
     */
    public Object remove(int index) {
        if (index < 0 || index > size() - 1)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());

        E oldValue = elementData(index);
        System.arraycopy(elementData, index + 1, elementData, index, size() - 1 - index);
        elementData = Arrays.copyOf(elementData, size() - 1);
        return oldValue;
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     *
     * @param o element whose presence in this list is to be tested
     * @return {@code true} if this list contains  the specified element
     */
    public boolean contains(Object o) {
        return indexOfRange(o, 0, size()) != -1;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     *
     * @param o element whose presence in this list is to be tested
     */
    public int indexOf(Object o) {
        return indexOfRange(o, 0, size());
    }

    /**
     * Add all elements of the specified list to this list.
     *
     * @param list list containing elements to be added to this list
     */
    public void addAll(CustomList<? extends E> list) {
        Object[] temp = list.toArray();

        grow(size() + temp.length);
        System.arraycopy(temp, 0,
                elementData, size() - temp.length,
                temp.length);
    }

    /**
     * Add all elements of the specified array to this list.
     *
     * @param array array containing elements to be added to this list
     */
    public void addAll(E[] array) {
        grow(size() + array.length);
        System.arraycopy(array, 0,
                elementData, size() - array.length,
                array.length);
    }

    /**
     * Removes all the elements from this list.
     */
    public void clear() {
        elementData = EMPTY_ELEMENT_DATA;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    public Iterator<E> iterator() {
        return new CustomListIterator<>(elementData);
    }

    /**
     * Returns an array containing all the elements in this list in proper sequence.
     *
     * @return an array containing all the elements in this list
     */
    public Object[] toArray() {
        return Arrays.copyOf(elementData, elementData.length);
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if index < 0 or index > {@code size()} - 1
     */
    private E elementData(int index) {
        if (index < 0 || index > size() - 1)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        return (E) elementData[index];
    }

    /**
     * Changes list size to the value of {@code capacity}
     *
     * @param capacity new list size
     */
    private void grow(int capacity) {
        elementData = Arrays.copyOf(elementData, capacity);
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     *
     * @param o     element whose presence in this list is to be tested
     * @param start index of start element to check
     * @param end   index of end element to check
     */
    private int indexOfRange(Object o, int start, int end) {
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
}
