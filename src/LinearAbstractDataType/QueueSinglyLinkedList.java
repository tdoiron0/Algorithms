package LinearAbstractDataType;

public class QueueSinglyLinkedList<T> {
    private SinglyLinkedListTail<T> list = new SinglyLinkedListTail<>();

    public QueueSinglyLinkedList() {}

    public void enqueue(T data) {
        list.addToBack(data);
    }
    public T dequeue() {
        return list.removeFromFront();
    }
    public T peek() {
        return list.getHead().getData();
    }
    public boolean isEmpty() {
        return list.isEmpty();
    }
    public void clear() {
        list.clear();
    }
}
