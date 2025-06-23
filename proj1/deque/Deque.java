package deque;

import java.util.Iterator;

public interface Deque<T> extends Iterable<T> {

    public void addFirst(T item);

    public void addLast(T item);

    /** Judge whether the deque is empty.
     * Return true if empty. */
    default public boolean isEmpty(){
        if (size() == 0) {
            return true;
        }
        return false;
    }

    public int size();

    public void printDeque();

    public T removeFirst();

    public T removeLast();

    public T get(int index);

    public Iterator<T> iterator();

}
