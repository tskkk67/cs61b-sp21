package deque;

import java.util.Iterator;

public interface Deque<Item> extends Iterable<Item> {

    public void addFirst(Item item);

    public void addLast(Item item);

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

    public Item removeFirst();

    public Item removeLast();

    public Item get(int index);

    @Override
    public Iterator<Item> iterator();

}
