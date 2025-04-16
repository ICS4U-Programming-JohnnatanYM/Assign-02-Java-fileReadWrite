import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;


final class FileReadWrite {
    // This program reads numbers from a file, processes
    //  them, and writes the results to another file.
    /**
     * This is to satisfy the style checker.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
     */
    private FileReadWrite() {
        throw new IllegalStateException("Utility Class");
    }

    /** Declare final array size. */
    private static final int ARRAY_SIZE = 100000;
    /**
     * Main Method.
     *
     * @param args Unused.
     */
    public static void main(final String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: java Program <inputFileName>");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = "output.txt";

        double[] numbers = new double[ARRAY_SIZE]; // Fixed size array
        int count = 0;

        try (Scanner scanner = new Scanner(new File(inputFileName))) {
            while (scanner.hasNext() && count < numbers.length) {
                if (scanner.hasNextDouble()) {
                    numbers[count] = scanner.nextDouble();
                    count++;
                } else {
                    scanner.next(); // skip invalid token
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + inputFileName);
            return;
        }

        if (count == 0) {
            System.out.println("No valid numbers found in the file.");
            return;
        }

        double smallest = findSmallest(numbers, count);
        double largest = findLargest(numbers, count);
        double sum = findSum(numbers, count);
        double average = sum / count;
        double[] sorted = sortArray(numbers, count);

        try (PrintWriter writer = new PrintWriter(outputFileName)) {
            writer.println("Number of values: " + count);
            writer.println("Smallest number: " + smallest);
            writer.println("Largest number: " + largest);
            writer.println("Sum: " + sum);
            writer.println("Average: " + average);
            writer.println("Sorted numbers:");
            for (int i = 0; i < count; i++) {
                writer.println(sorted[i]);
            }
        } catch (IOException e) {
            System.out.println("Error writing to output file.");
        }

        System.out.println("Processing complete. Output written to "
         + outputFileName);
    }

    public static double findSmallest(final double[] arr, final int size) {
        double min = arr[0];
        for (int i = 1; i < size; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    public static double findLargest(final double[] arr, final int size) {
        double max = arr[0];
        for (int i = 1; i < size; i++) {
            if (arr[i] >= max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static double findSum(final double[] arr, final int size) {
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static double[] sortArray(final double[] arr, final int size) {
        double[] sorted = new double[size];
        System.arraycopy(arr, 0, sorted, 0, size);

        // Simple bubble sort
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (sorted[j] > sorted[j + 1]) {
                    double temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }
        return sorted;
    }
}
