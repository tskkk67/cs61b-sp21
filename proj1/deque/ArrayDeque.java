package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private ArrayList<T> deque;

    public ArrayDeque() {
        deque = new ArrayList<>();
    }

    /** Add item to the front. */
    @Override
    public void addFirst(T x) {
        deque.addFirst(x);
    }

    /** Add item to the bottom. */
    @Override
    public void addLast(T x) {
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
    public T removeFirst() {
        return deque.removeFirst();
    }

    /** Remove the last item and return its value. */
    @Override
    public T removeLast() {
        return deque.removeLast();
    }

    /** Get the i-th (0-base) item of deque. */
    @Override
    public T get(int i) {
        return deque.get(i);
    }

    /** private class to implement arraydeque iterator. */
    private class ArrayDequeIterator implements Iterator<T> {
        private int pos;
        public ArrayDequeIterator() {
            pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos < size();
        }

        @Override
        public T next() {
            T ret = deque.get(pos);
            pos++;
            return ret;
        }
    }

    /** Return the iterator of arraydeque. */
    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    /** Judge whether THIS is equal to given object o. */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if(!(o instanceof Deque)) {
            return false;
        }
        Deque other = (Deque) o;
        if(other.size() != size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (other.get(i) != get(i)) {
                return false;
            }
        }
        return true;
    }

}
