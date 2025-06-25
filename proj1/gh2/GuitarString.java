package gh2;

// TODO: uncomment the following import once you're ready to start this portion
import deque.Deque;
import deque.LinkedListDeque;
// TODO: maybe more imports

//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    // TODO: uncomment the following line once you're ready to start this portion
    private Deque<Double> buffer;


    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        long capacity = Math.round(SR / frequency);
        buffer = new LinkedListDeque<>();
        for (int i = 1; i <= capacity; i++) {
            buffer.addLast(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        //
        //       Make sure that your random numbers are different from each
        //       other. This does not mean that you need to check that the numbers
        //       are different from each other. It means you should repeatedly call
        //       Math.random() - 0.5 to generate new random numbers for each array index.
        int n = buffer.size();
        for (int i = 1; i <= n; i++) {
            double rd = Math.random() - 0.5;
            buffer.removeFirst();
            buffer.addLast(rd);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {

        double x = buffer.removeFirst();
        double y = buffer.get(0);
        buffer.addLast((x + y) * DECAY / 2);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);
    }
}

