package LinearAbstractDataType;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class SinglyLinkedList<T> implements Iterable<T> {
    private SinglyLinkedListNode<T> head; 
    private int size = 0;

    public SinglyLinkedList() {}

    public void addAtIndex(T data, int index) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds of size " + size + "."); 
        }

        if (index == 0) {
            addToFront(data);
        } else {
            SinglyLinkedListNode<T> currNode = head; 
            int count = 0;
            while (count < index - 1) {
                currNode = currNode.getNext();
                ++count; 
            }
            SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data, currNode.getNext());
            currNode.setNext(newNode);
            ++size;
        }
    }
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }

        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<T>(data, head); 
        head = newNode;
        ++size;
    }
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }

        if (head == null) {
            head = new SinglyLinkedListNode<T>(data);
        } else {
            SinglyLinkedListNode<T> currNode = head; 
            while (currNode.getNext() != null) {
                currNode = currNode.getNext();
            }
            currNode.setNext(new SinglyLinkedListNode<T>(data));
        }
        ++size;
    }
    public T removeFromIndex(int index) {
        if (size == 0) {
            throw new NoSuchElementException("No elements in the list to remove.");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds of size " + size + "."); 
        }

        T removed = null;
        if (index == 0) {
            removed = head.getData(); 
            head = head.getNext();
        } else {
            SinglyLinkedListNode<T> currNode = head; 
            int count = 0;
            while (count < index - 1) {
                currNode = currNode.getNext();
                ++count; 
            }
            removed = currNode.getNext().getData(); 
            currNode.setNext(currNode.getNext().getNext());
        }
        --size;
        return removed;
    }
    public T removeFromFront() {
        if (size == 0) {
            throw new NoSuchElementException("No elements in the list to remove.");
        }

        T temp = head.getData();
        head = head.getNext();
        --size; 
        return temp;
    }
    public T removeFromBack() {
        if (size == 0) {
            throw new NoSuchElementException("No elements in the list to remove.");
        }

        if (head.getNext() == null) {
            T temp = head.getData(); 
            head = null;
            --size;
            return temp;
        } else {
            SinglyLinkedListNode<T> currNode = head; 
            while (currNode.getNext().getNext() != null) {
                currNode = currNode.getNext(); 
            }
            T temp = currNode.getNext().getData(); 
            currNode.setNext(null);
            --size; 
            return temp;
        }
    }
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds of size " + size + "."); 
        }

        SinglyLinkedListNode<T> curNode = head; 
        for (int i = 0; i < index; ++i) {
            curNode = curNode.getNext();
        }
        return curNode.getData();
    }
    public SinglyLinkedListNode<T> getHead() {
        return head; 
    }
    public int size() {
        return size; 
    }
    public boolean isEmpty() {
        return head == null;
    }
    public void clear() {
        head = null;
        size = 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        SinglyLinkedListNode<T> currNode = head; 
        while (currNode != null) {
            sb.append(currNode.toString()); 
            currNode = currNode.getNext();
            sb.append((currNode == null) ? "" : ", ");
        }
        return sb.toString();
    }
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private SinglyLinkedListNode<T> currNode = head;

            public boolean hasNext() {
                return currNode != null;
            }
            public T next() {
                if (hasNext()) {
                    T temp = currNode.getData(); 
                    currNode = currNode.getNext();
                    return temp;
                }
                return null;
            }
        };
    }
}
