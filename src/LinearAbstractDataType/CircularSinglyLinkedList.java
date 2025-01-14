package LinearAbstractDataType;

import java.util.NoSuchElementException;

public class CircularSinglyLinkedList<T> {
    private SinglyLinkedListNode<T> head; 
    private int size;

    public CircularSinglyLinkedList() {
        size = 0;
    }
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds of size " + size + "."); 
        }
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }

        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            SinglyLinkedListNode<T> currNode = head.getNext();
            int count = 1; 
            while (count < index - 1) {
                currNode = currNode.getNext();
                ++count;
            }
            SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<T>(data, currNode.getNext());
            currNode.setNext(newNode);
        }
        ++size;
    }
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }

        if (head == null) {
            head = new SinglyLinkedListNode<T>(data);
            head.setNext(head);
        } else {
            SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<T>(head.getData()); 
            newNode.setNext(head.getNext());
            head.setNext(newNode);
            head.setData(data);
        }
        ++size;
    }
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }

        if (head == null) {
            head = new SinglyLinkedListNode<T>(data);
            head.setNext(head);
        } else {
            SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<T>(head.getData()); 
            newNode.setNext(head.getNext());
            head.setNext(newNode);
            head.setData(data);
            head = newNode;
        }
        ++size;
    }
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds of size " + size + ".");
        }
        if (size == 0) {
            throw new NoSuchElementException("No elements in the array to remove.");
        }

        if (index == 0) {
            return removeFromFront();
        } else if (index == 1) {
            T removed = head.getNext().getData();
            head.setNext(head.getNext().getNext());
            --size;
            return removed;
        } else if (index == size - 1) {
            return removeFromBack();
        } else {
            SinglyLinkedListNode<T> currNode = head.getNext();
            int count = 1; 
            while (count < index - 1) {
                currNode = currNode.getNext(); 
                ++count; 
            }
            T removed = currNode.getNext().getData(); 
            currNode.setNext(currNode.getNext().getNext());
            --size; 
            return removed;
        }
    }
    public T removeFromFront() {
        if (size == 0) {
            throw new NoSuchElementException("No elements in the array to remove.");
        }

        T removed = head.getData();
        if (size == 1) {
            head = null;
        } else if (size == 2) {
            head.setData(head.getNext().getData());
            head.setNext(null);
        } else {
            head.setData(head.getNext().getData());
            head.setNext(head.getNext().getNext());
        }
        --size;
        return removed;
    }
    public T removeFromBack() {
        if (size == 0) {
            throw new NoSuchElementException("No elements in the array to remove.");
        }

        if (size == 1) {
            T removed = head.getData();
            head = null;
            --size;
            return removed;
        } else {
            SinglyLinkedListNode<T> currNode = head;
            while (currNode.getNext().getNext() != head) {
                currNode = currNode.getNext();
            }
            T removed = currNode.getNext().getData(); 
            currNode.setNext(head);
            --size;
            return removed;
        }
    }
    public int size() {
        return size; 
    }
    public String toString() {
        if (size == 0) {
            return "";
        } else {
            SinglyLinkedListNode<T> currNode = head;
            StringBuilder sb = new StringBuilder(); 
            sb.append(currNode.getData());
            currNode = currNode.getNext();
            sb.append((currNode != head) ? ", " : "");
            while (currNode != head) {
                sb.append(currNode.getData());
                currNode = currNode.getNext();
                sb.append((currNode != head) ? ", " : "");
            }
            return sb.toString();
        }
    }
}
