public class InsertionSort implements Sorter {
    @Override
    public String name() { return "Insertion Sort"; }

    @Override
    public boolean isQuadratic() { return true; }

    @Override
    public void sort(int[] arr, Metrics m) {
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int position = i;

            while (position > 0) {
                if (!m.greater(arr[position - 1], value)) {
                    break;
                }
                arr[position] = arr[position - 1];
                m.move();
                position--;
            }

            if (position != i) {
                arr[position] = value;
                m.move();          // the final placement is a movement too
            }
        }
    }
}
