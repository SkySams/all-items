package org.example.hash.map;

import java.util.Map;
import java.util.Objects;

/**
 * @author: zyh
 * @date: 2022/6/10
 */
public class Nodete<K,V>  implements Map.Entry<K,V>{

    final int hash;
    final K key;
    V value;
    Nodete<K,V> next;

    Nodete(int hash, K key, V value, Nodete<K,V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    @Override
    public final K getKey()        { return key; }
    @Override
    public final V getValue()      { return value; }
    @Override
    public final String toString() { return key + "=" + value; }

    @Override
    public final int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    @Override
    public final V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }

    @Override
    public final boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Map.Entry) {
            Map.Entry<?,?> e = (Map.Entry<?,?>)o;
            if (Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue())) {
                return true;
            }
        }
        return false;
    }


}
