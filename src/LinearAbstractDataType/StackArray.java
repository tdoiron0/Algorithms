package LinearAbstractDataType;

import java.util.EmptyStackException;

public class StackArray<T> {
    public static final int INITIAL_CAPACITY = 100;

    private T[] backingArray; 
    private int size; 

    public StackArray() {
        this.backingArray = (T[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }

        backingArray[size] = data;
        ++size; 
    }
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        T temp = backingArray[size - 1];
        backingArray[size - 1] = null;
        --size; 
        return temp;
    }
    public boolean isEmpty() {
        return size == 0;
    }
}