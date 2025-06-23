package deque;

import java.util.Iterator;

public class ArrayDeque<Item> implements Deque<Item>, Iterable<Item> {
    private ArrayList<Item> deque;

    public ArrayDeque() {
        deque = new ArrayList<>();
    }

    /** Add item to the front. */
    @Override
    public void addFirst(Item x) {
        deque.addFirst(x);
    }

    /** Add item to the bottom. */
    @Override
    public void addLast(Item x) {
        deque.addLast(x);
    }

    /** Get the size of the deque. */
    @Override
    public int size() {
        return deque.getSize();
    }

    /** Print items in deque. */
    @Override
    public void printDeque() {
        deque.print();
    }

    /** Remove the first item and return its value. */
    @Override
    public Item removeFirst() {
        return deque.removeFirst();
    }

    /** Remove the last item and return its value. */
    @Override
    public Item removeLast() {
        return deque.removeLast();
    }

    /** Get the i-th (0-base) item of deque. */
    @Override
    public Item get(int i) {
        return deque.get(i);
    }

    /** private class to implement arraydeque iterator. */
    private class ArrayDequeIterator implements Iterator<Item> {
        private int pos;
        public ArrayDequeIterator() {
            pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos < size();
        }

        @Override
        public Item next() {
            Item ret = deque.get(pos);
            pos++;
            return ret;
        }
    }

    /** Return the iterator of arraydeque. */
    @Override
    public Iterator<Item> iterator() {
        return new ArrayDequeIterator();
    }


}
