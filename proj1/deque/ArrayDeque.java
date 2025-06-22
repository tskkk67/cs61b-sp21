package deque;

public class ArrayDeque<Item> {
    private ArrayList<Item> deque;

    public ArrayDeque() {
        deque = new ArrayList<>();
    }

    /** Add item to the front. */
    public void addFirst(Item x) {
        deque.addFirst(x);
    }

    /** Add item to the bottom. */
    public void addLast(Item x) {
        deque.addLast(x);
    }

    /** Judge whether the deque is empty.
     * Return true if empty. */
    public boolean isEmpty() {
        if (deque.getSize() == 0) {
            return true;
        }
        return false;
    }

    /** Get the size of the deque. */
    public int size() {
        return deque.getSize();
    }

    /** Print items in deque. */
    public void printDeque() {
        deque.print();
    }

    /** Remove the first item and return its value. */
    public Item removeFirst() {
        return deque.removeFirst();
    }

    /** Remove the last item and return its value. */
    public Item removeLast() {
        return deque.removeLast();
    }

    /** Get the i-th (0-base) item of deque. */
    public Item get(int i) {
        return deque.get(i);
    }

}
