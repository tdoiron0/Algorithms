package AbstractDataType;

import java.util.LinkedList;
import java.util.Set;

public class LinearProbingHashMap<K,V> {
    public static final int INITIAL_CAPACITY = 13;

    private static final double MAX_LOAD_FACTOR = 0.67;

    private Pair<K,V>[] table;
    private int size; 

    public LinearProbingHashMap() {
        table = new Pair[INITIAL_CAPACITY];
        size = 0;
    }

    public V put(K key, V value) {
        V result = null;
        if (size == table.length) {
            Pair<K,V>[] oldTable = table; 
            table = new Pair[oldTable.length * 2 + 1];

        }

        Pair<K,V> newElement = new Pair<K,V>(key, value);
        int index = hashFunction(key);
        int saved = -1;
        boolean foundDeleated = false;
        while (table[index] != null) {
            if (table[index].getKey().equals(key)) {
                result = table[index].getValue();
                table[index] = newElement; 
                break;
            }
            if (table[index].isRemoved() && !foundDeleated) {
                saved = index;
                foundDeleated = true;
            }
            
            index = (1 + index) % table.length;
        }
        if (result == null) {
            if (foundDeleated) {
                table[saved] = newElement;
            } else {
                table[index] = newElement;
            }
        }
        ++size;

        return result;
    }
    public V remove(K key) {
        V result = null;
        int index = hashFunction(key);
        int count = 0;
        while (count < table.length && table[index] != null) {
            if (table[index].getKey().equals(key)) {
                result = table[index].getValue();
                table[index].setRemoved(true);
                break;
            }
            index = (1 + index) % table.length;
            ++count; 
        }

        return result; 
    }
    public V search(K key) {
        return null; //TODO
    }
    public Set<K> keySet() {
        return null; //TODO
    }
    public LinkedList<V> values() {
        return null; //TODO
    }
    public String toString() {
        StringBuilder sb = new StringBuilder(); 
        for (Pair<K,V> it : table) {
            if (it != null) {
                if (it.isRemoved()) {
                    sb.append("DEL, ");
                } else {
                    sb.append("<" + it.getKey() + "(" + hashFunction(it.getKey()) + ")," + it.getValue()  + ">, ");
                }
            } else {
                sb.append(it + ", ");
            }
        }
        return sb.toString();
    }

    private int hashFunction(K key) {
        return key.hashCode() % table.length;
    }
}
