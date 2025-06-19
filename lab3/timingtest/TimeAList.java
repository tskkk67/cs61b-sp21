package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
        printTimingTable(sNs,stimes,sopCounts);
    }

    static AList<Integer> sNs=new AList<>();
    static AList<Double> stimes=new AList<>();
    static AList<Integer> sopCounts=new AList<>();
    static int addtimes=1000;

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        for(int i=0;i<8;i++){
            AList<Integer> temp=new AList<>();
            Stopwatch sw = new Stopwatch();
            for(int j=1;j<=addtimes;j++) temp.addLast(j);
            stimes.addLast(sw.elapsedTime());
            sNs.addLast(addtimes);
            sopCounts.addLast(addtimes);
            addtimes*=2;
        }
    }
}
