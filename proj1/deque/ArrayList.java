package deque;

public class ArrayList<T> {
    private T[] items;
    private int size;
    // "circular" array so that add/remove first takes constant time
    // nfirst: next addfirst position (nlast the same)
    private int nfirst, nlast;

    public ArrayList() {
        items = (T[]) new Object[8];
        size = 0;
        // add first from the bottom, add last from the front
        // initial length is 8
        nfirst = 7;
        nlast = 0;
    }

    private int left(int x) {
        if (x > 0) {
            return x - 1;
        }
        return items.length - 1;
    }
    private int right(int x) {
        if (x == items.length - 1) {
            return 0;
        }
        return x + 1;
    }

    /** Resize the array with capacity x. */
    private void resize(int x) {
//        T[] temp = (T[]) new Object[x];
//        System.arraycopy(items, 0, temp, 0, nlast);
//        int sourcepos = nlast;
//        while (items[sourcepos] == null) {
//            if (sourcepos == items.length - 1) {
//                break;
//            }
//            sourcepos++;
//        }
//        System.arraycopy(items, sourcepos, temp, sourcepos + x - items.length, items.length - sourcepos);
//        if (nfirst > nlast) {
//            nfirst += x - items.length;
//        }
//        items = temp;
        T[] temp = (T[]) new Object[x];
        int p = right(nfirst);
        for (int i = 0; i < size; i++) {
            temp[i] = items[p];
            p = right(p);
        }
        items = temp;
        nfirst = x - 1;
        nlast = size;
    }

    /** Add an item to the front. */
    public void addFirst(T x) {
        if (size == items.length - 1) {
            resize(items.length * 2);
        }
        items[nfirst] = x;
        nfirst = left(nfirst);
        size++;
    }

    /** Add an item to the bottom. */
    public void addLast(T x) {
        if (size == items.length - 1) {
            resize(items.length * 2);
        }
        items[nlast] = x;
        nlast = right(nlast);
        size++;
    }

    /** Remove the first item and return its value. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        nfirst = right(nfirst);
        T x = items[nfirst];
        items[nfirst] = null;
        size--;
        if (size < items.length / 4 && items.length > 4) {
            resize(items.length / 2);
        }
        return x;
    }

    /** Remove the last item and return its value. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        nlast = left(nlast);
        T x = items[nlast];
        items[nlast] = null;
        size--;
        if (size < items.length / 4 && items.length > 4) {
            resize(items.length / 2);
        }
        return x;

    }

    /** Get the i-th (o-base) item. */
    public T get(int i) {
        if (i >= size) {
            return null;
        }
        return items[(nfirst + i + 1) % items.length];
    }

    /** Get the size of the list. */
    public int getSize() {
        return size;
    }

    /** Print the items in the list. */
    public void print() {
        int i = right(nfirst);
        while (i != nlast) {
            System.out.println(items[i]);
            i = right(i);
        }
        System.out.println();
    }

}
