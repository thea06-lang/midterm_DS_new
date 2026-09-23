import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Saves arrays and results to .txt files.
 *
 * Author: [Member 4]
 */
public class OutputWriter {

    /**
     * Write one number per line to path.
     * Create the parent folder if missing.
     * Use a BufferedWriter for large files such as 1,000,000 elements.
     */
    public static void writeArray(String path, int[] data) throws IOException {

        Path filePath = Paths.get(path);
        Path parent = filePath.getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {

            for (int value : data) {
                writer.write(Integer.toString(value));
                writer.newLine();
            }
        }
    }

    /** Write a block of text, such as the results table, to path. */
    public static void writeText(String path, String text) throws IOException {

        Path filePath = Paths.get(path);
        Path parent = filePath.getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write(text);
        }
    }
}