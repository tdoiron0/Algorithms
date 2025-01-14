package AbstractDataType;

public class Pair<K,V> {
    private K key; 
    private V value; 
    private boolean removed;

    public Pair(K key, V value) {
        this.key = key; 
        this.value = value; 
    }

    public K getKey() {
        return key;
    }
    public void setKey(K key) {
        this.key = key;
    }
    public V getValue() {
        return value;
    }
    public void setValue(V value) {
        this.value = value;
    }
    public boolean isRemoved() {
        return removed;
    }
    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
}
