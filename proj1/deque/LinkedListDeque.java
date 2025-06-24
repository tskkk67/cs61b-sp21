package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private LinkedList<T> deque;

    public LinkedListDeque() {
        deque = new LinkedList<>();
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

    /** Get the size of deque. */
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

    /** Get the i-th (0-base) item, but recursively.
     * But I will use iteration... See linkedlist for detail.
     * Big news: no more iteration! Still, see linkedlist for detail. */
    public T getRecursive(int i) {
        return deque.getRecursive(i);
    }

    /** private class to implement linkedlistdeque iterator. */
    private class LinkedlistIterator implements Iterator<T> {
        private int pos;
        public LinkedlistIterator() {
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

    /** Return the iterator of linkedlistdeque. */
    @Override
    public Iterator<T> iterator() {
        return new LinkedlistIterator();
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

//    public static void main(String[] args){
//        LinkedListDeque<Integer> test = new LinkedListDeque<>();
//        test.addFirst(1);
//        test.addLast(6);
//        test.addFirst(3);
//        test.addFirst(100);
//        System.out.println(test.getRecursive(1));
//        System.out.println(test.getRecursive(2));
//    }


}
