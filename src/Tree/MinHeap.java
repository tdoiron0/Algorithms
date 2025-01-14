package Tree;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 *
 * @author Trent Doiron
 * @version 1.0
 * @userid tdoiorn6
 * @GTID 903833296
 *
 * Collaborators: N/A
 *
 * Resources: N/A
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap when created with the default
     * constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new MinHeap.
     *
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     * To initialize the backing array, create a Comparable array and then cast
     * it to a T array.
     */
    public MinHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the BuildHeap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     * As a reminder, this is the algorithm that involves building the heap
     * from the bottom up by repeated use of downHeap operations.
     *
     * Before doing the algorithm, first copy over the data from the
     * ArrayList to the backingArray (leaving index 0 of the backingArray
     * empty). The data in the backingArray should be in the same order as it
     * appears in the passed in ArrayList before you start the BuildHeap
     * algorithm.
     *
     * The backingArray should have capacity 2n + 1 where n is the
     * size of the passed in ArrayList (not INITIAL_CAPACITY). Index 0 should
     * remain empty, indices 1 to n should contain the data in proper order, and
     * the rest of the indices should be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public MinHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }
        backingArray = (T[]) new Comparable[data.size() * 2 + 1];
        for (int i = 0; i < data.size(); ++i) {
            if (data.get(i) == null) {
                throw new IllegalArgumentException("Data cannot be equal to null.");
            }
            backingArray[i + 1] = data.get(i);
        }
        size = data.size();
        for (int i = size / 2; i >= 1; --i) {
            downHeap(i);
        }
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     * The order property of the heap must be maintained after adding. You can
     * assume that no duplicate data will be passed in.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }

        if (size + 1 == backingArray.length) {
            T[] oldBackingArray = backingArray;
            backingArray = (T[]) new Comparable[oldBackingArray.length * 2];
            for (int i = 0; i < oldBackingArray.length; ++i) {
                backingArray[i] = oldBackingArray[i];
            }
        }
        backingArray[++size] = data;
        upHeap(size);
    }

    /**
     * Sifts specified node up to it's correct point in the tree.
     *
     * @param index index of node to be moved
     */
    private void upHeap(int index) {
        if (index / 2 > 0 && backingArray[index].compareTo(backingArray[index / 2]) < 0) {
            T temp = backingArray[index / 2];
            backingArray[index / 2] = backingArray[index];
            backingArray[index] = temp;
            upHeap(index / 2);
        }
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     * The order property of the heap must be maintained after removing.
     *
     * @return the data that was removed
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("No elements in the tree to remove.");
        }

        T result = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        --size;
        downHeap(1);
        return result;
    }

    /**
     * Sifts specified node down to it's propper place in the tree.
     *
     * @param index index of node to be moved
     */
    private void downHeap(int index) {
        int left = 2 * index;
        int right = 2 * index + 1;
        int smallest = index;
        if (left <= size && backingArray[left].compareTo(backingArray[smallest]) < 0) {
            smallest = left;
        }
        if (right <= size && backingArray[right].compareTo(backingArray[smallest]) < 0) {
            smallest = right;
        }
        if (smallest != index) {
            T temp = backingArray[smallest];
            backingArray[smallest] = backingArray[index];
            backingArray[index] = temp;
            downHeap(smallest);
        }
    }

    /**
     * Returns the minimum element in the heap.
     *
     * @return the minimum element
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T getMin() {
        if (size == 0) {
            throw new NoSuchElementException("No elements in the tree.");
        }
        return backingArray[1];
    }

    /**
     * Returns whether or not the heap is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the heap.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     */
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
