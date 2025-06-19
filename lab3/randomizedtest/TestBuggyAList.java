package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> exp=new AListNoResizing<>();
        BuggyAList<Integer> real=new BuggyAList<>();
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
        BuggyAList<Integer> b=new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                b.addLast(randVal);
//                System.out.println("addLast(" + randVal + ")");
            }
            else if (operationNumber == 1) {
                // size
                int size = L.size();
                int s=b.size();
//                System.out.println("size: " + size);
                assertEquals(size,s);
            }
            else if(operationNumber==2){
                //getLast
                if(L.size()>0){
                    int exp=L.getLast();
                    int real=b.getLast();
//                    System.out.println("getLast: "+exp);
                    assertEquals(exp,real);
                }
//                else System.out.println("cannot get last");
            }
            else{
                //removeLast
                if(L.size()>0){
                    int exp=L.removeLast();
                    int real=b.removeLast();
//                    System.out.println("removeLast: "+exp);
                    assertEquals(exp,real);
                }
//                else System.out.println("cannot remove last");
            }
        }
    }
}
