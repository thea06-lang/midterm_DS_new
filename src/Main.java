import java.io.IOException;

/**
 * Runs every sort on the same datasets and prints a results table.
 *
 * Author: [Member 5]
 *
 *   java -cp bin Main          -> skips O(n^2) sorts on 1,000,000 elements
 *   java -cp bin Main --full   -> runs everything (O(n^2) sorts on 1M can take HOURS)
 */
public class Main {
    static final int[] SIZES = {5, 1_000, 1_000_000};
    static final long SEED = 42;
    static final int SLOW_LIMIT = 100_000;

    public static void main(String[] args) throws IOException {
        boolean full = false;
        for (String a : args) if (a.equals("--full")) full = true;

        Sorter[] sorters = {
            new HeapSort(), new BubbleSort(), new SelectionSort(), new InsertionSort()
        };

        StringBuilder report = new StringBuilder();
        String header = String.format("%-15s %10s %14s %16s %14s %14s %16s  %s%n",
                "Algorithm", "Size", "Runtime (ms)", "Comparisons", "Swaps", "Moves", "Extra", "Sorted?");
        report.append(header);
        System.out.print(header);

        for (int size : SIZES) {
            int[] original = DataGenerator.random(size, SEED);
            OutputWriter.writeArray("output/input_" + size + ".txt", original);

            if (size <= 10) System.out.println("Input (" + size + "): " + toText(original));

            for (Sorter s : sorters) {
                if (s.isQuadratic() && size > SLOW_LIMIT && !full) {
                    String row = String.format("%-15s %10d   skipped: O(n^2) on this size can take hours (run with --full)%n",
                            s.name(), size);
                    report.append(row);
                    System.out.print(row);
                    continue;
                }

                int[] copy = original.clone();   // every sort gets the SAME unsorted data
                Metrics m = new Metrics();
                m.startTimer();
                s.sort(copy, m);
                m.stopTimer();

                String file = "output/" + s.name().replace(" ", "") + "_" + size + ".txt";
                OutputWriter.writeArray(file, copy);

                String extra = s.extraLabel().equals("-") ? "-" : m.getExtra() + " " + s.extraLabel();
                String row = String.format("%-15s %10d %14.3f %16d %14d %14d %16s  %s%n",
                        s.name(), size, m.getRuntimeMs(), m.getComparisons(),
                        m.getSwaps(), m.getMoves(), extra, isSorted(copy) ? "yes" : "NO");
                report.append(row);
                System.out.print(row);

                if (size <= 10) System.out.println("  Output: " + toText(copy));
            }
            report.append(System.lineSeparator());
            System.out.println();
        }

        OutputWriter.writeText("output/results.txt", report.toString());
        System.out.println("Sorted arrays and results.txt saved in the output/ folder.");
    }

    static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) if (a[i - 1] > a[i]) return false;
        return true;
    }

    static String toText(int[] a) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a.length; i++) sb.append(i == 0 ? "" : ", ").append(a[i]);
        return sb.append("]").toString();
    }
}
