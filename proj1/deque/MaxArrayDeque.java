package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private ArrayList<T> maxdeque;
    private Comparator<T> c;

    public MaxArrayDeque(Comparator<T> com) {
        maxdeque = new ArrayList<>();
        c = com;
    }

    /**  Returns the maximum element by this.c */
    public T max() {
        if (maxdeque.getSize() == 0) {
            return null;
        }
        T maxn = maxdeque.get(0);
        for (int i = 1; i < maxdeque.getSize(); i++) {
            if (c.compare(maxn, maxdeque.get(i)) < 0) {
                maxn = maxdeque.get(i);
            }
        }
        return maxn;
    }

    /** Returns the maximum element by the given comparator com. */
    public T max(Comparator<T> com) {
        if (maxdeque.getSize() == 0) {
            return null;
        }
        T maxn = maxdeque.get(0);
        for (int i = 1; i < maxdeque.getSize(); i++) {
            if (com.compare(maxn, maxdeque.get(i)) < 0) {
                maxn = maxdeque.get(i);
            }
        }
        return maxn;
    }


}
