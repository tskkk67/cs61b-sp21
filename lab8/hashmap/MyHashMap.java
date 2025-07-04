package hashmap;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int initialSize = 16;
    private double maxLoad = 0.75;
    private int size = 0;

    /** Constructors */
    public MyHashMap() {}

    public MyHashMap(int in) {
        initialSize = in;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     */
    public MyHashMap(int in, double ml) {
        initialSize = in;
        maxLoad = ml;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new ArrayList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }




    // Private helper methods:

    /** Calculate which bucket a node should go using hashcode. */
    private int whichBucket(Node x) {
        return Math.floorMod(x.key.hashCode(), buckets.length);
    }

    /** Find a key in ONE bucket. */
    private Node findInBucket(K key, Collection<Node> x) {
        if (x == null) {
            return null;
        }
        for (Node it : x) {
            if (it.key.equals(key)) {
                return it;
            }
        }
        return null;
    }

    /** Find a key in the whole map (all the buckets). */
    private Node find(K key) {
        Node ret = null;
        if (buckets == null) {
            return null;
        }
        for (Collection<Node> it : buckets) {
            ret = findInBucket(key, it);
            if (ret != null) {
                break;
            }
        }
        return ret;
    }

    /** Resize the buckets[] to capacity. */
    private void resize(int capacity) {
        Collection<Node>[] temp = createTable(capacity);
        for (int i = 0; i < capacity; i++) {
            temp[i] = createBucket();
        }
        for (Collection<Node> bucket : buckets) {
            if (bucket == null) {
                continue;
            }
            for (Node node : bucket) {
                int index = whichBucket(node);
                temp[index].add(node);
            }
        }
        buckets = temp;
    }




    // Implementation of all the interfaces:

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        buckets = null;
        size = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return find(key) != null;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        Node ret = find(key);
        if (ret == null) {
            return null;
        }
        return ret.value;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        if (buckets == null) {
            buckets = createTable(initialSize);
            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = createBucket();
            }
        }
        if (size >= maxLoad * buckets.length) {
            resize(2 * buckets.length);
        }
        Node x = find(key);
        if (x != null) {
            x.value = value;
        } else {
            Node ins = new Node(key, value);
            int index = whichBucket(ins);
            buckets[index].add(ins);
            size++;
        }
    }


    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        Node target = find(key);
        if (target == null) {
            return null;
        }
        int index = whichBucket(target);
        buckets[index].remove(target);
        size--;
        return target.value;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        Node target = find(key);
        if (target == null || target.value != value) {
            return null;
        }
        size--;
        return remove(key);
    }


    /** Class of hashmap iterator. */
    private class HashMapIterator implements Iterator<K> {
        private Queue<Node> q;

        HashMapIterator() {
            q = new Queue<>();
            for (int i = 0; i < buckets.length; i++) {
                if (buckets[i] == null) {
                    continue;
                }
                for (Node it : buckets[i]) {
                    q.enqueue(it);
                }
            }
        }

        @Override
        public boolean hasNext() {
            return !q.isEmpty();
        }

        @Override
        public K next() {
            Node now = q.dequeue();
            return now.key;
        }
    }

    /** Return the iterator of keys in the map. */
    @Override
    public Iterator<K> iterator() {
        return new HashMapIterator();
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> ret = new HashSet<>();
        for (K it : this) {
            ret.add(it);
        }
        return ret;
    }

}
