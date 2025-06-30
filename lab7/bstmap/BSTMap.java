package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class BSTNode {
        K key;
        V value;
        BSTNode ls, rs;

        BSTNode(K k, V v, BSTNode l, BSTNode r) {
            key = k;
            value = v;
            ls = l;
            rs = r;
        }
    }

    private BSTNode root;
    private int size;

    /** A helper method that finds key starting from a BSTNode, and returns a BSTNode.
     * Will create a new node with value 0 (null) when key is not found. */
    private BSTNode find(K key, BSTNode x) {
        if (x == null) {
            return null;
        }

        if (key.compareTo(x.key) == 0) {
            return x;
        }
        if (key.compareTo(x.key) < 0) {
            return find(key, x.ls);
        } else {
            return find(key, x.rs);
        }
    }

    /** A helper method to create a NEW node with NEW key (previously non-exist). */
    private BSTNode insert(K key, V value, BSTNode x) {
        if (x == null) {
            size++;
            return new BSTNode(key, value, null, null);
        }

        if (key.compareTo(x.key) < 0) {
            x.ls = insert(key, value, x.ls);
        } else {
            x.rs = insert(key, value, x.rs);
        }
        return x;
    }

    public BSTMap() {
        size = 0;
        root = null;
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return find(key, root) != null;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (find(key, root) == null) {
            return null;
        }
        return find(key, root).value;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /** Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new BSTNode(key, value, null, null);
            size++;
            return;
        }
        if (containsKey(key)) {
            find(key, root).value = value;
        } else {
            insert(key, value, root);
        }
    }

    /** Print the keys in BST. */
    public void printInOrder() {

    }

    /** Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /** Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /** Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    /** The iterator of map. Just throw UnsupportedOperationException. */
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

}
