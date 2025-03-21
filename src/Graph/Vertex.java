package Graph;

public class Vertex<T> {
    private T data;

    public Vertex(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null");
        }
        this.data = data; 
    }
    
    public T getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Vertex) {
            return data.equals(((Vertex<?>) o).data);
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return data.toString();
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }
}
