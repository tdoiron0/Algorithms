package LinearAbstractDataType;

import java.util.NoSuchElementException;

public class ArrayList<T> {
    public static final int INITIAL_CAPACITY = 9;

    private T[] backingArray;
    private int size = 0;

    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds of size " + size + ".");
        }
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }

        if (size == backingArray.length) {
            T[] oldBackingArray = backingArray;
            backingArray = (T[]) new Object[backingArray.length * 2];
            int j = 0;
            for (T it : oldBackingArray) {
                backingArray[j++] = it;
                if (j == index) {
                    backingArray[j++] = data;
                }
            }
        } else {
            for (int i = size; i > index; --i) {
                backingArray[i] = backingArray[i - 1];
            }
            backingArray[index] = data;
        }
        ++size;
    }
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }

        if (size == backingArray.length) {
            T[] oldBackingArray = backingArray;
            backingArray = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i < oldBackingArray.length; ++i) {
                backingArray[i] = oldBackingArray[i];
            }
        }

        for (int i = size; i > 0; --i) {
            backingArray[i] = backingArray[i - 1];
        }
        backingArray[0] = data;
        ++size;
    }
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }

        if (size == backingArray.length) {
            T[] oldBackingArray = backingArray;
            backingArray = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i < oldBackingArray.length; ++i) {
                backingArray[i] = oldBackingArray[i];
            }
        }

        backingArray[size] = data;
        ++size;
    }
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds of size " + size + ".");
        }

        T temp = backingArray[index];
        backingArray[index] = null;
        for (int i = index; i < size - 1; ++i) {
            backingArray[i] = backingArray[i + 1];
        }
        backingArray[--size] = null;
        return temp;
    }
    public T removeFromFront() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        T temp = backingArray[0];
        for (int i = 0; i < size - 1; ++i) {
            backingArray[i] = backingArray[i + 1];
        }
        backingArray[size - 1] = null;
        --size;
        return temp;
    }
    public T removeFromBack() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        T temp = backingArray[size - 1];
        backingArray[size - 1] = null;
        --size;
        return temp;
    }
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return backingArray[index];
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }
    public T[] getBackingArray() {
        return backingArray;
    }
    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < backingArray.length; ++i) {
            sb.append(backingArray[i] + ((i + 1 == size) ? "" : ", "));
        }
        return sb.toString();
    }
}
