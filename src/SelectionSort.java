/**
 * Selection Sort: find the smallest value in the unsorted part, swap it into place.
 *
 * Author: [Member 3]
 */
public class SelectionSort implements Sorter {
    @Override
    public String name() { return "Selection Sort"; }

    @Override
    public boolean isQuadratic() { return true; }

    @Override
    public void sort(int[] arr, Metrics m) {
        for (int start = 0; start < arr.length - 1; start++) {
            int smallest = start;

            for (int i = start + 1; i < arr.length; i++) {
                if (m.less(arr[i], arr[smallest])) {
                    smallest = i;
                }
            }

            if (smallest != start) {
                m.swap(arr, start, smallest);
            }
        }
    }
}
