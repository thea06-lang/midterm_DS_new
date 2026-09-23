import java.util.Random;


public class DataGenerator {

    /** Return n random ints (0 to 999,999) using java.util.Random with the given seed. */
    public static int[] random(int n, long seed) {

        Random random = new Random(seed);
        int[] data = new int[n];

        for (int i = 0; i < n; i++) {
            data[i] = random.nextInt(1_000_000);
        }

        return data;
    }
}