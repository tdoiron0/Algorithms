package LinearAbstractDataType;

public class DoublyLinkedListNode<T> {
    private T data; 
    private DoublyLinkedListNode<T> next; 
    private DoublyLinkedListNode<T> previous;

    public DoublyLinkedListNode(T data, DoublyLinkedListNode<T> previous, DoublyLinkedListNode<T> next) {
        this.previous = previous;
        this.data = data; 
        this.next = next;
    }
    public DoublyLinkedListNode(T data) {
        this(data, null, null);
    }

    public T getData() {
        return data;
    }
    public DoublyLinkedListNode<T> getPrevious() {
        return previous; 
    }
    public DoublyLinkedListNode<T> getNext() {
        return next; 
    }
    public void setData(T data) {
        this.data = data;
    }
    public void setPrevious(DoublyLinkedListNode<T> previous) {
        this.previous = previous;
    } 
    public void setNext(DoublyLinkedListNode<T> next) {
        this.next = next;
    }
}
