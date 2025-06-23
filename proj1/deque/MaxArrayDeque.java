package deque;
import java.util.Comparator;

public class MaxArrayDeque<Item> extends ArrayDeque<Item> {
    private ArrayList<Item> maxdeque;
    private Comparator<Item> c;

    public MaxArrayDeque(Comparator<Item> com) {
        maxdeque = new ArrayList<>();
        c = com;
    }

    /**  Returns the maximum element by this.c */
    public Item max() {
        if (maxdeque.getSize() == 0) {
            return null;
        }
        Item maxn = maxdeque.get(0);
        for (int i = 1; i < maxdeque.getSize(); i++) {
            if (c.compare(maxn, maxdeque.get(i)) < 0) {
                maxn = maxdeque.get(i);
            }
        }
        return maxn;
    }

    /** Returns the maximum element by the given comparator com. */
    public Item max(Comparator<Item> com) {
        if (maxdeque.getSize() == 0) {
            return null;
        }
        Item maxn = maxdeque.get(0);
        for (int i = 1; i < maxdeque.getSize(); i++) {
            if (com.compare(maxn, maxdeque.get(i)) < 0) {
                maxn = maxdeque.get(i);
            }
        }
        return maxn;
    }


}
