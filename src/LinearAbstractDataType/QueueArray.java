package LinearAbstractDataType;

import java.util.NoSuchElementException;

public class QueueArray<T> {
    private T[] backingArray;
    private int front;
    private int size; 
    private static final int INITIAL_CAPACITY = 9;

    public QueueArray() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        front = 0;
    }

    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }

        if (size == backingArray.length) {
            T[] oldBackingArray = backingArray; 
            backingArray = (T[]) new Object[size * 2];
            for (int i = 0; i != (front + size) % backingArray.length; ++i) {
                backingArray[i] = oldBackingArray[(front + i) % oldBackingArray.length];
            }
            front = 0;
        }

        backingArray[(front + size) % backingArray.length] = data;
        ++size; 
    }
    public T dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("No elements in the array to remove.");
        }
        
        T temp = backingArray[front]; 
        backingArray[front] = null;
        front = (front + 1) % backingArray.length;
        --size; 
        return temp; 
    }
    public T peek() {
        return backingArray[front];
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        front = 0;
    }
}
