package LinearAbstractDataType;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements Iterable<T> {
    private DoublyLinkedListNode<T> head; 
    private DoublyLinkedListNode<T> tail; 
    private int size; 

    public DoublyLinkedList() {}

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
            if (index < size / 2) {
                DoublyLinkedListNode<T> currNode = head; 
                int count = 0;
                while (count < index - 1) {
                    currNode = currNode.getNext();
                    ++count;
                }
                DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(data, currNode, currNode.getNext());
                currNode.getNext().setPrevious(newNode);
                currNode.setNext(newNode);
            } else {
                DoublyLinkedListNode<T> currNode = tail;
                int count = size - 1;
                while (count > index) {
                    currNode = currNode.getPrevious();
                    --count;
                }
                DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(data, currNode.getPrevious(), currNode);
                currNode.getPrevious().setNext(newNode);
                currNode.setPrevious(newNode);
            }
            ++size;
        }
    }
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }

        if (head == null) {
            head = new DoublyLinkedListNode<T>(data);
            tail = head;
        } else {
            DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<T>(data, tail, null);
            tail.setNext(newNode);
            tail = newNode;
        }
        ++size;
    }
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }

        if (head == null) {
            head = new DoublyLinkedListNode<T>(data);
            tail = head;
        } else {
            DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<T>(data, null, head);
            head.setPrevious(newNode);
            head = newNode;
        }
        ++size;
    }
    public T removeFromIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds of size " + size + "."); 
        }

        if (index == 0) {
            return removeFromFront();
        } else if (index == size - 1) {
            return removeFromBack();
        } else {
            if (index < size / 2) {
                DoublyLinkedListNode<T> currNode = head;
                int count = 0;
                while (count < index - 1) {
                    currNode = currNode.getNext();
                    ++count;
                }
                T temp = currNode.getNext().getData();
                currNode.getNext().getNext().setPrevious(currNode);
                currNode.setNext(currNode.getNext().getNext());
                --size;
                return temp;
            } else {
                DoublyLinkedListNode<T> currNode = tail;
                int count = size - 1;
                while (count > index) {
                    currNode = currNode.getPrevious();
                    --count;
                }
                T temp = currNode.getData();
                currNode.getPrevious().setNext(currNode.getNext());
                currNode.getNext().setPrevious(currNode.getPrevious());
                --size;
                return temp;
            }
        }
    }
    public T removeFromBack() {
        if (size == 0) {
            throw new NoSuchElementException("No elements in the array to remove.");
        }

        T temp = tail.getData();
        tail = tail.getPrevious();
        tail.getNext().setPrevious(null);
        tail.setNext(null);
        --size;
        return temp;
    }
    public T removeFromFront() {
        if (size == 0) {
            throw new NoSuchElementException("No elements in the array to remove.");
        }

        T temp = head.getData();
        head = head.getNext();
        head.getPrevious().setNext(null);
        head.setPrevious(null);
        --size;
        return temp;
    }
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds of size " + size + "."); 
        }

        if (index < size / 2) {
            DoublyLinkedListNode<T> currNode = head; 
            int count = 0;
            while (count < index) {
                currNode = currNode.getNext();
                ++count;
            }
            return currNode.getData();
        } else {
            DoublyLinkedListNode<T> currNode = tail;
            int count = size - 1;
            while (count > index) {
                currNode = currNode.getPrevious();
                --count;
            }
            return currNode.getData();
        }
    }
    public int size() {
        return size;
    }
    public DoublyLinkedListNode<T> getHead() {
        return head;
    }
    public DoublyLinkedListNode<T> getTail() {
        return tail;
    }
    public void clear() {
        head = null;
        tail = head;
        size = 0;
    }
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }

        DoublyLinkedListNode<T> currNode = tail;
        T removed = null;
        while (currNode != null) {
            if (currNode.getData().equals(data)) {
                removed = currNode.getData();
                currNode.getPrevious().setNext(currNode.getNext());
                currNode.getNext().setPrevious(currNode.getPrevious());
                --size;
                break;
            }
            currNode = currNode.getPrevious();
        }
        if (removed == null) {
            throw new NoSuchElementException("Data not found.");
        }
        return removed;
    }
    public Object[] toArray() {
        Object[] result = new Object[size];
        DoublyLinkedListNode<T> currNode = head; 
        int count = 0; 
        while (currNode != null) {
            result[count] = currNode.getData();
            currNode = currNode.getNext(); 
            ++count;
        }
        return result;
    }
    public boolean isEmpty() {
        return head == null;
    }
    public String toString() {
        DoublyLinkedListNode<T> currNode = head;
        StringBuilder sb = new StringBuilder(); 
        while (currNode != null) {
            sb.append(currNode.getData()); 
            currNode = currNode.getNext();
            sb.append((currNode != null) ? ", " : "");
        }
        return sb.toString();
    }
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            DoublyLinkedListNode<T> curr = head;

            public boolean hasNext() {
                return curr != null;
            }

            public T next() {
                if (hasNext()) {
                    T temp = curr.getData(); 
                    curr = curr.getNext();
                    return temp;
                }
                return null;
            }
        };
    }
}