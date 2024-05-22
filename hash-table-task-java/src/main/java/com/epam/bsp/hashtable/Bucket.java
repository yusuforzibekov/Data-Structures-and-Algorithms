package com.epam.bsp.hashtable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a set of (K,V) pairs that were assigned to the same
 * bin/chain/bucket.
 * 
 * @param <K> type of key
 * @param <V> type of value
 */
public class Bucket<K, V> {

    /**
     * List of KeyValueData in the bucket.
     */
    private List<KeyValueData<K, V>> elements;

    public Bucket() {
        // put your code here
        elements = new ArrayList<>();
    }

    public List<KeyValueData<K, V>> getElements() {
        return elements;
    }

    /**
     * Searches a value on a given key in the bucket
     * 
     * @param key the key for search
     * @return the value for a given key; otherwise null, if no corresponding (K,V)
     *         is found.
     */
    public V get(K key) {
        // put your code here
        for (KeyValueData<K, V> element : elements) {
            if (element.getKey().equals(key)) {
                return element.getValue();
            }
        }
        return null;
    }

    /**
     * Puts a given (K,V) pair into the bucket.
     * 
     * @param key   the key
     * @param value the value
     */
    public void put(K key, V value) {
        // put your code here
        for (KeyValueData<K, V> element : elements) {
            if (element.getValue().equals(key)) {
                element.setValue(value);
                return;
            }
        }
        elements.add(new KeyValueData<>(key, value));
    }

    /**
     * Removes the (K,V) pair for a given key
     * 
     * @param key the key
     * @return whether an element for a given key was really removed
     */
    public boolean remove(K key) {
        // put your code here
        for (KeyValueData<K, V> element : elements) {
            if (element.getKey().equals(key)) {
                elements.remove(element);
                return true;
            }
        }
        return false;
    }

}
