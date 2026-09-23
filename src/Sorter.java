/**
 * Every sorting algorithm implements this, so Main can run them all the same way.
 */
public interface Sorter {
    /** Display name, e.g. "Heap Sort". */
    String name();

    /** Sort arr in place (ascending), counting work in m. */
    void sort(int[] arr, Metrics m);

    /** True for O(n^2) sorts. Main skips these on 1,000,000 elements unless run with --full. */
    default boolean isQuadratic() { return false; }

    /** Label for the extra metric, e.g. "heapify calls". "-" means not used. */
    default String extraLabel() { return "-"; }
}
