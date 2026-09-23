/**
 * BubbleSort.java
 *
 * Standard Bubble Sort with the classic "stop early if nothing swapped"
 * check. That check is what gives Bubble Sort its O(n) best case on
 * data that's already sorted (or a bonus nearly-sorted dataset) — the
 * same effect the plan notes for Insertion Sort, so it's worth
 * mentioning in the group's comparison section.
 *
 * Rules followed (per the team plan):
 *   - Takes an int[] and a Metrics object.
 *   - Counts every comparison and every swap.
 *   - Never touches Arrays.sort() / Collections.sort().
 *   - Does not modify the caller's original array reference behavior —
 *     whoever calls this should pass in their own copy of the dataset,
 *     since sorting happens in place.
 *
 * Author: [Member 2]
 */
public class BubbleSort implements Sorter {

    @Override
    public String name() { return "Bubble Sort"; }

    @Override
    public boolean isQuadratic() { return true; }

    /**
     * Sorts arr in place (ascending) and records comparisons and swaps.
     *
     * @param arr     the array to sort (sorted in place)
     * @param metrics a fresh Metrics object for this run
     */
    @Override
    public void sort(int[] arr, Metrics metrics) {
        int n = arr.length;
        for (int pass = 0; pass < n - 1; pass++) {
            boolean swappedThisPass = false;

            // After each pass, the largest remaining item has "bubbled"
            // to the end, so the inner loop shrinks by one each time.
            for (int j = 0; j < n - 1 - pass; j++) {
                if (metrics.greater(arr[j], arr[j + 1])) {
                    metrics.swap(arr, j, j + 1);
                    swappedThisPass = true;
                }
            }

            // Nothing swapped means the array is already sorted —
            // no point doing the remaining passes.
            if (!swappedThisPass) {
                break;
            }
        }
    }
}
