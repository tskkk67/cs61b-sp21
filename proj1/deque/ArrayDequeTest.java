package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ArrayDequeTest {
    // YOUR TESTS HERE

    public class AListNoResizing<Item> {
        private Item[] items;
        private int size;

        /** Creates an empty list. */
        public AListNoResizing() {
            items = (Item[]) new Object[50000];
            size = 0;
        }

        /** Inserts X into the back of the list. */
        public void addLast(Item x) {
            items[size] = x;
            size = size + 1;
        }

        /** Gets the ith item in the list (0 is the front). */
        public Item get(int i) {
            return items[i];
        }

        /** Returns the number of items in the list. */
        public int size() {
            return size;
        }

        /** Deletes item from back of the list and
         * returns deleted item. */
        public Item removeLast() {
            Item x = items[size - 1];
            items[size - 1] = null;
            size = size - 1;
            return x;
        }

        public void addFirst(Item x) {
            Item[] temp = (Item[]) new Object[items.length];
            temp[0] = x;
            System.arraycopy(items, 0, temp, 1, size);
            size++;
            items = temp;
        }
        public Item removeFirst() {
            Item x = items[0];
            Item[] temp = (Item[]) new Object[items.length];
            System.arraycopy(items, 1, temp, 0, size - 1);
            size--;
            items = temp;
            return x;
        }
        public void printDeque() {
            for(int i = 0; i < size; i++) {
                System.out.print(items[i] + " ");
            }
            System.out.println();
        }
    }


    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> exp=new AListNoResizing<>();
        ArrayDeque<Integer> real=new ArrayDeque<>();
        for(int i=5;i<=7;i++){
            exp.addLast(i);
            real.addLast(i);
        }
        for(int i=1;i<=3;i++){
            assertEquals(exp.removeLast(),real.removeLast());
        }
    }
    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        ArrayDeque<Integer> b=new ArrayDeque<>();
        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 5);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                b.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            }
            else if (operationNumber == 1) {
                // size
                int size = L.size();
                int s=b.size();
                System.out.println("size: " + size);
                assertEquals(size,s);
            }
            else if(operationNumber==2){
                //getLast
                if(L.size()>0){
                    int randVal = StdRandom.uniform(0, 100);
                    L.addFirst(randVal);
                    b.addFirst(randVal);
                    System.out.println("addfirst: "+randVal);
                }
                else System.out.println("size = 0");
            }
            else if(operationNumber==3){
                //removeLast
                if(L.size()>0){
                    int exp=L.removeLast();
                    int real=b.removeLast();
                    System.out.println("removeLast: "+exp);
                    assertEquals(exp, real);
                }

                else System.out.println("size = 0");
            }
            else if(operationNumber==4) {
                if(L.size()>0){
                    int exp=L.removeFirst();
                    int real = b.removeFirst();
                    System.out.println("removefirst: " + exp);
                    assertEquals(exp, real);
                }
                else System.out.println("size = 0");
            }
        }
        L.printDeque();
        b.printDeque();
    }
}
