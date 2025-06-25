package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> c;

    public MaxArrayDeque(Comparator<T> com) {
        c = com;
    }

    /**  Returns the maximum element by this.c */
    public T max() {
        if (size() == 0) {
            return null;
        }
        T maxn = get(0);
        for (int i = 1; i < size(); i++) {
            if (c.compare(maxn, get(i)) < 0) {
                maxn = get(i);
            }
        }
        return maxn;
    }

    /** Returns the maximum element by the given comparator com. */
    public T max(Comparator<T> com) {
        if (size() == 0) {
            return null;
        }
        T maxn = get(0);
        for (int i = 1; i < size(); i++) {
            if (com.compare(maxn, get(i)) < 0) {
                maxn = get(i);
            }
        }
        return maxn;
    }


    public static class IntCompare implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    }
    public static class IntComparesmall implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return b - a;
        }
    }

//    public static void main(String[] args){
//        IntCompare c1 = new IntCompare();
//        IntComparesmall c2 = new IntComparesmall();
//        MaxArrayDeque<Integer> d = new MaxArrayDeque<>(c1);
//        for (int i=1;i<=100;i++) {
//            d.addLast(StdRandom.uniform(0,500));
//        }
//        d.printDeque();
//        System.out.println(d.max());
//        System.out.println(d.max(c2));
//    }


}
