package deque;


public interface Deque<T> {

    void addFirst(T item);

    void addLast(T item);

    /** Judge whether the deque is empty.
     * Return true if empty. */
    default boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    int size();

    void printDeque();

    T removeFirst();

    T removeLast();

    T get(int index);


}
