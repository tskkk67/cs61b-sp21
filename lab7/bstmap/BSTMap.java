package bstmap;

import edu.princeton.cs.algs4.StdRandom;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

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

    /** Find the number of children of a node x. */
    private int numberOfChild(BSTNode x) {
        if (x.ls == null && x.rs == null) {
            return 0;
        }
        if (x.ls != null) {
            if (x.rs == null) {
                return 1;
            }
            return 2;
        }
        return 1;
    }

    /** Return the child for a node x that has ONLy 1 child. */
    private BSTNode findOnlyChild(BSTNode x) {
        if (x.ls != null) {
            return x.ls;
        }
        return x.rs;
    }

    /** Find a successor to replace the 2 children should-be-deleted node, that is,
     * for a node x which has both ls & rs, this helper method will find the biggest
     * node that is smaller than x. */
    private BSTNode findSuccessor(BSTNode x) {
        BSTNode ret = x.ls;
        while (ret.rs != null) {
            ret = ret.rs;
        }
        return ret;
    }

    /** Find the parent of a node x. */
    private BSTNode findParent(BSTNode x, BSTNode now) {
        if (x == null || x == root || x == now || now == null) {
            return null;
        }
        if (now.ls == x || now.rs == x) {
            return now;
        }
        if (x.key.compareTo(now.key) < 0) {
            return findParent(x, now.ls);
        }
        return findParent(x, now.rs);
    }

    /** Delete the reference of parent to child. */
    private void deleteChild(BSTNode fa, BSTNode son) {
        if (fa.ls == son) {
            fa.ls = null;
        } else if (fa.rs == son) {
            fa.rs = null;
        }
    }

    /** A helper method that get a node, delete it, and return the value of that node. */
    private V delete(BSTNode x) {
        V ret = x.value;
        if (numberOfChild(x) == 0) {
            if (x == root) {
                root = null;
            } else {
                BSTNode fa = findParent(x, root);
                deleteChild(fa, x);
            }
        } else if (numberOfChild(x) == 1) {
            BSTNode son = findOnlyChild(x);
            if (x == root) {
                root = son;
            } else {
                BSTNode fa= findParent(x, root);
                if (x == fa.ls) {
                    fa.ls = son;
                } else {
                    fa.rs = son;
                }
            }
        } else {
            BSTNode successor = findSuccessor(x);
            x.value = successor.value;
            x.key = successor.key;
            delete(successor);
        }
        return ret;
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
        for (K it : this) {
            System.out.print(it + " ");
        }
    }

    /** Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    @Override
    public Set<K> keySet() {
        Set<K> ret = new HashSet<>();
        for (K it : this) {
            ret.add(it);
        }
        return ret;
    }

    /** Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        BSTNode target = find(key, root);
        if (target == null) {
            return null;
        }
        size--;
        return delete(target);
    }

    /** Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        BSTNode target = find(key, root);
        if (target == null || target.value != value) {
            return null;
        }
        size--;
        return delete(target);
    }


    private class BSTMapIterator implements Iterator<K> {
        private Stack<BSTNode> stack = new Stack<>();

        public BSTMapIterator() {
            BSTNode x = root;
            while (x != null) {
                stack.push(x);
                x = x.ls;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.empty();
        }

        @Override
        public K next() {
            BSTNode x = stack.pop();
            K ret = x.key;
            x = x.rs;
            while (x != null) {
                stack.push(x);
                x = x.ls;
            }
            return ret;
        }
    }
    /** The iterator of map. */
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }




/*

    public static void main(String[] args) {
        BSTMap<Integer, Integer> map = new BSTMap<>();
        int n = 10;
        for (int i = 1; i <= n; i++) {
            int key = StdRandom.uniform(0, 100);
            System.out.println(key + " " + i);
            map.put(key, i);
        }
        map.printInOrder();
    }

*/


}
