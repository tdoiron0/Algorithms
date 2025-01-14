package LinearAbstractDataType;

public class StackSinglyLinkedList<T> {
    private SinglyLinkedList<T> list = new SinglyLinkedList<>();

    public void push(T data) {
        list.addToFront(data);
    }
    public T pop() {
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