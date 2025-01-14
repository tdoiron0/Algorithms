package LinearAbstractDataType;

public class SinglyLinkedListNode<T> {
    private SinglyLinkedListNode<T> next; 
    private T data; 

    public SinglyLinkedListNode(T data, SinglyLinkedListNode<T> next) {
        this.data = data; 
        this.next = next; 
    }
    public SinglyLinkedListNode(T data) {
        this(data, null);
    }

    public T getData() {
        return data;
    }
    public SinglyLinkedListNode<T> getNext() {
        return next; 
    }
    public void setData(T data) {
        this.data = data; 
    }
    public void setNext(SinglyLinkedListNode<T> next) {
        this.next = next; 
    }
}
