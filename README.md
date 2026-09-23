# COSC 251: Heap Sort vs Bubble, Selection, Insertion Sort

## Requirements
Java 11 or newer (`java -version` to check).

## Compile
From this folder:

    javac -d bin src/*.java

## Run
    java -cp bin Main

This runs all four sorts on 5, 1,000 and 1,000,000 random elements (same seed, same data for every sort).
Bubble, Selection and Insertion Sort are skipped on 1,000,000 elements because they can take hours.

To run everything, including the slow ones:

    java -cp bin Main --full

## Output
- Results table printed to the console and saved to `output/results.txt`
- Each sorted array saved to `output/<Algorithm>_<size>.txt`
- Each input dataset saved to `output/input_<size>.txt`

## Metrics
- Runtime (ms), comparisons, swaps
- Moves: element shifts (Insertion Sort)
- Extra: heapify calls (Heap Sort)
