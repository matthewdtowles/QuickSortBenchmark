import com.sun.xml.internal.bind.v2.model.annotation.Quick;

import java.util.Arrays;

/**
 * Main Class for QuickSortBenchmark
 *
 * What the package does:
 *
 * Benchmarks the QuickSort algorithm
 * Implements QS in iterative and recursive forms
 * Benchmark done on a critical operation
 * Counts critical operations to be given as output
 * Measures actual runtime
 * Analyzes call result to make sure sorted
 *  Throws UnsortedException if unsorted
 * Randomly generates data for sorting
 * Produces 50 data sets for each value of n (size of data set)
 *  Averages all the results of data sets
 *  Same data set must go through iterative and recursive methods
 * Uses 10 different data sizes for testing
 *  To show growth rate of run time
 * Calculates the coefficient of variance of the critical operations counts
 *  and time measurements for the 50 runs (to gauge data sensitivity)
 */
public class SortMain {

    public static void main(String[] args) {

        int[] arr = new int[20];

        for (int i = 0; i < 20; i++) {
            int a = (int) (100 * Math.random());
            arr[i] = a;
        }

        QuickSort qs = new QuickSort();
        System.out.println(Arrays.toString(arr));
        qs.iterativeSort(0, arr.length - 1, arr);
        System.out.println("After: ");
        System.out.println(Arrays.toString(arr));
    }
}
