package deque;

public class LinkedList<T> {
    /** Nested class to form a node. */
    private class Node {
        T value;
        Node nxt, lst;

        Node(T x, Node n, Node l) {
            value = x;
            nxt = n;
            lst = l;
        }

        /** Wow! We can have recursive method by getting the (this+i)-th node recursively! */
        public Node get(int i) {
            if (i == 0) {
                return this;
            }
            return this.nxt.get(i - 1);
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

    /** Add a node to the front. */
    public void addFirst(T x) {
        size++;
        Node first = new Node(x, sentinel.nxt, sentinel);
        sentinel.nxt.lst = first;
        sentinel.nxt = first;
    }

    /** Add a node to the bottom. */
    public void addLast(T x) {
        size++;
        Node last = new Node(x, sentinel, sentinel.lst);
        sentinel.lst.nxt = last;
        sentinel.lst = last;
    }

    /** Remove the first node and return its value. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        T x = sentinel.nxt.value;
        sentinel.nxt = sentinel.nxt.nxt;
        sentinel.nxt.lst = sentinel;
        return x;
    }

    /** Remove the last node and return its value. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        T x = sentinel.lst.value;
        sentinel.lst = sentinel.lst.lst;
        sentinel.lst.nxt = sentinel;
        return x;
    }

    /** Get the i-th (0-base) value of the list. */
    public T get(int i) {
        if (i >= size) {
            return null;
        }
        Node p = sentinel;
        for (int k = 0; k <= i; k++) {
            p = p.nxt;
        }
        return p.value;
    }

    /** Get the i-th (0-base) value, but recursively.
     * That's too bad. Can I use recursive method given that my next is a node, not a list?
     * I may temporarily use iteration first...
     * BREAKING news after 60 mins: your next is a node? Then use recursive method on your NODE!
     * What a genius XD */
    public T getRecursive(int i) {
        if (i >= size) {
            return null;
        }
        return sentinel.nxt.get(i).value;
    }

    /** Get the size of a list. */
    public int getSize() {
        return size;
    }

    /** Print the items in the list. */
    public void print() {
        Node p = sentinel;
        for (int i = 0; i < size; i++) {
            p = p.nxt;
            System.out.print(p.value + " ");
        }
        System.out.println();
    }

//    public static void main(String[] args){
//
//    }

}
