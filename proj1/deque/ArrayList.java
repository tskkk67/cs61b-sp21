package deque;

public class ArrayList<T> {
    private T[] items;
    private int size;

    public ArrayList() {
        items = (T[]) new Object[8];
        size = 0;
    }

    /** Resize the array with capacity x. */
    private void resize(int x) {
        T[] temp = (T[]) new Object[x];
        System.arraycopy(items, 0, temp, 0, size);
        items = temp;
    }

    /** Add an item to the front. */
    public void addFirst(T x) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        T[] temp = (T[]) new Object[items.length];
        temp[0] = x;
        System.arraycopy(items, 0, temp, 1, size);
        items = temp;
        size++;
    }

    /** Add an item to the bottom. */
    public void addLast(T x) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[size] = x;
        size++;
    }

    /** Remove the first item and return its value. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T x = items[0];
        T[] temp = (T[]) new Object[items.length];
        System.arraycopy(items, 1, temp, 0, size - 1);
        items = temp;
        size--;
        if (size < items.length / 4 && items.length > 4) {
            resize(items.length / 4);
        }
        return x;
    }

    /** Remove the last item and return its value. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T x = items[size - 1];
        size--;
        if (size < items.length / 4 && items.length > 4) {
            resize(items.length / 4);
        }
        return x;
    }

    /** Get the i-th (o-base) item. */
    public T get(int i) {
        if (i >= size) {
            return null;
        }
        return items[i];
    }

    /** Get the size of the list. */
    public int getSize() {
        return size;
    }

    /** Print the items in the list. */
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

}
