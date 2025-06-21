package deque;

public class ArrayList<Item> {
    private Item[] items;
    private int size;

    public ArrayList() {
        items = (Item[]) new Object[100];
        size = 0;
    }

    public ArrayList(Item x) {
        items = (Item[]) new Object[100];
        items[0] = x;
        size = 1;
    }

    /** Resize the array with capacity x. */
    private void resize(int x) {
        Item[] temp = (Item[]) new Object[x];
        System.arraycopy(items, 0, temp, 0, size);
        items = temp;
    }

    /** Add an item to the front. */
    public void addFirst(Item x) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        Item[] temp = (Item[]) new Object[items.length];
        temp[0] = x;
        System.arraycopy(items, 0, temp, 1, size);
        size++;
    }

    /** Add an item to the bottom. */
    public void addLast(Item x) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[size] = x;
        size++;
    }

    /** Remove the first item and return its value. */
    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        Item x = items[0];
        Item[] temp = (Item[]) new Object[items.length];
        System.arraycopy(items, 1, temp, 0, size - 1);
        items = temp;
        return x;
    }

    /** Remove the last item and return its value. */
    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        Item x = items[size-1];
        size--;
        return x;
    }

    /** Get the i-th (o-base) item. */
    public Item get(int i) {
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
