package LinearAbstractDataType;

import java.util.NoSuchElementException;

/**
 * Your implementation of a LinkedDeque.
 *
 * @author Trent Doiron
 * @version 1.0
 * @userid tdoiron6
 * @GTID 903833296
 *
 * Collaborators: N/A
 *
 * Resources: N/A
 */
public class DequeDoublyLinkedList<T> {

    // Do not add new instance variables or modify existing ones.
    private DoublyLinkedListNode<T> head;
    private DoublyLinkedListNode<T> tail;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the element to the front of the deque.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addFirst(T data) {
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

    /**
     * Adds the element to the back of the deque.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addLast(T data) {
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

    /**
     * Removes and returns the first element of the deque.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("No elements in the deque to remove.");
        }

        T temp = head.getData();
        head = head.getNext();
        head.getPrevious().setNext(null);
        head.setPrevious(null);
        --size;
        return temp;
    }

    /**
     * Removes and returns the last element of the deque.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("No elements in the deque to remove.");
        }

        T temp = tail.getData();
        tail = tail.getPrevious();
        tail.getNext().setPrevious(null);
        tail.setNext(null);
        --size;
        return temp;
    }

    /**
     * Returns the first data of the deque without removing it.
     *
     * Must be O(1).
     *
     * @return the data located at the front of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("No elements in the deque.");
        }

        return head.getData();
    }

    /**
     * Returns the last data of the deque without removing it.
     *
     * Must be O(1).
     *
     * @return the data located at the back of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T getLast() {
        if (tail == null) {
            throw new NoSuchElementException("No elements in the deque.");
        }

        return tail.getData();
    }

    /**
     * Returns the head node of the deque.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return node at the head of the deque
     */
    public DoublyLinkedListNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail node of the deque.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return node at the head of the deque
     */
    public DoublyLinkedListNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }

    /**
     * Returns the size of the deque.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the deque
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
