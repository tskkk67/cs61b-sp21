package deque;

import java.util.Iterator;

public class LinkedListDeque<Item> implements Deque<Item>, Iterable<Item> {
    private LinkedList<Item> deque;

    public LinkedListDeque() {
        deque = new LinkedList<>();
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

    /** Get the i-th (0-base) item, but recursively.
     * But I will use iteration... See linkedlist for detail.
     * Big news: no more iteration! Still, see linkedlist for detail. */
    public Item getRecursive(int i) {
        return deque.getRecursive(i);
    }

    /** private class to implement linkedlistdeque iterator. */
    private class LinkedlistIterator implements Iterator<Item> {
        private int pos;
        public LinkedlistIterator() {
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

    /** Return the iterator of linkedlistdeque. */
    @Override
    public Iterator<Item> iterator() {
        return new LinkedlistIterator();
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
