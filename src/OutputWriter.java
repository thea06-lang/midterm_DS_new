import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OutputWriter {

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