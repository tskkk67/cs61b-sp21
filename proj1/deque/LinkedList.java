package deque;

public class LinkedList<Item> {
    /** Nested class to form a node. */
    private class Node {
        Item value;
        Node nxt, lst;

        public Node(Item x, Node n, Node l) {
            value = x;
            nxt = n;
            lst = l;
        }
    }

    /** Sentinel node to avoid null pointer.
     * The first node would be sentinel.nxt.  */
    private Node sentinel;
    private int size;

    public LinkedList() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.nxt = sentinel;
        sentinel.lst = sentinel;
    }

    public LinkedList(Item x) {
        size = 1;
        Node first = new Node(x, null, null);
        sentinel = new Node(null, first, first);
        first.lst = sentinel;
        first.nxt = sentinel;
    }

    /** Add a node to the front. */
    public void addFirst(Item x) {
        size++;
        Node first = new Node(x, sentinel.nxt, sentinel);
        sentinel.nxt.lst = first;
        sentinel.nxt = first;
    }

    /** Add a node to the bottom. */
    public void addLast(Item x) {
        size++;
        Node last = new Node(x, sentinel, sentinel.lst);
        sentinel.lst.nxt = last;
        sentinel.lst = last;
    }

    /** Remove the first node and return its value. */
    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        Item x = sentinel.nxt.value;
        sentinel.nxt = sentinel.nxt.nxt;
        sentinel.nxt.lst = sentinel;
        return x;
    }

    /** Remove the last node and return its value. */
    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        Item x = sentinel.lst.value;
        sentinel.lst = sentinel.lst.lst;
        sentinel.lst.nxt = sentinel;
        return x;
    }

    /** Get the i-th (0-base) value of the list. */
    public Item get(int i) {
        if (i >= size) {
            return null;
        }
        Node p = sentinel;
        for (int k = 0; k <= i; k++) {
            p = p.nxt;
        }
        return p.value;
    }

    /** Get the size of a list. */
    public int getSize() {
        return size;
    }

    /** Print the items in the list. */
    public void print() {
        Node p = sentinel;
        for (int i = 0; i< size; i++) {
            p = p.nxt;
            System.out.print(p.value + " ");
        }
        System.out.println();
    }

//    public static void main(String[] args){
//
//    }

}
