/**
 * Metrics.java
 *
 * Shared by every sorting algorithm in this project (Heap, Bubble,
 * Selection, Insertion). Each sort gets its own fresh Metrics object,
 * counts every comparison and swap it makes, and times itself with
 * this class so the final results table compares "like with like."
 *
 * Author: [Member 2]
 *
 * Usage inside a sort method:
 *
 *   Metrics metrics = new Metrics();
 *   metrics.startTimer();
 *   // ... sort the array, calling metrics.incrementComparisons()
 *   // and metrics.incrementSwaps() at the right spots ...
 *   metrics.stopTimer();
 *   System.out.println(metrics);
 */
public class Metrics {

    private long comparisons;
    private long swaps;
    private long moves;
    private long extra;
    private long startTimeNanos;
    private long endTimeNanos;
    private boolean timerRunning;

    public Metrics() {
        comparisons = 0;
        swaps = 0;
        moves = 0;
        extra = 0;
        startTimeNanos = 0;
        endTimeNanos = 0;
        timerRunning = false;
    }

    // ---- counting ----

    /** Call this every time two elements are compared. */
    public void incrementComparisons() {
        comparisons++;
    }

    /** Call this if you ever need to add more than one comparison at once. */
    public void addComparisons(long count) {
        comparisons += count;
    }

    /** Call this every time two elements are swapped / moved. */
    public void incrementSwaps() {
        swaps++;
    }

    /** Call this if you ever need to add more than one swap at once. */
    public void addSwaps(long count) {
        swaps += count;
    }

    /** Compare two values and count the comparison. */
    public boolean less(int left, int right) {
        comparisons++;
        return left < right;
    }

    /** Compare two values and count the comparison. */
    public boolean greater(int left, int right) {
        comparisons++;
        return left > right;
    }

    /** Swap two array elements and count one swap. */
    public void swap(int[] array, int first, int second) {
        int temporary = array[first];
        array[first] = array[second];
        array[second] = temporary;
        swaps++;
    }

    /** Count one element shift performed by insertion sort. */
    public void move() {
        moves++;
    }

    /** Count one algorithm-specific operation, such as a heapify call. */
    public void countExtra() {
        extra++;
    }

    // ---- timing ----

    /** Start the stopwatch. Call this right before the sort begins. */
    public void startTimer() {
        startTimeNanos = System.nanoTime();
        timerRunning = true;
    }

    /** Stop the stopwatch. Call this right after the sort finishes. */
    public void stopTimer() {
        endTimeNanos = System.nanoTime();
        timerRunning = false;
    }

    // ---- reading the results ----

    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }

    public long getMoves() {
        return moves;
    }

    public long getExtra() {
        return extra;
    }

    /** Elapsed time in nanoseconds between startTimer() and stopTimer(). */
    public long getRuntimeNanos() {
        long end = timerRunning ? System.nanoTime() : endTimeNanos;
        return end - startTimeNanos;
    }

    public double getRuntimeMillis() {
        return getRuntimeNanos() / 1_000_000.0;
    }

    /** Short alias used by the results table. */
    public double getRuntimeMs() {
        return getRuntimeMillis();
    }

    public double getRuntimeSeconds() {
        return getRuntimeNanos() / 1_000_000_000.0;
    }

    /** Reset everything so the same object can be reused for a new run. */
    public void reset() {
        comparisons = 0;
        swaps = 0;
        moves = 0;
        extra = 0;
        startTimeNanos = 0;
        endTimeNanos = 0;
        timerRunning = false;
    }

    /** One tidy row, handy for printing straight into the results table. */
    @Override
    public String toString() {
        return String.format(
                "Comparisons: %,d | Swaps: %,d | Runtime: %.3f ms",
                comparisons, swaps, getRuntimeMillis());
    }

    /** Same numbers as a comma-separated line, handy for dumping to a CSV. */
    public String toCsvRow() {
        return comparisons + "," + swaps + "," + getRuntimeMillis();
    }
}
