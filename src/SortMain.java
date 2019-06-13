/**
 * Main Class for QuickSortBenchmark
 *
 * Critical Operations measured:
 * - iterativeSort() loops
 * - recursiveSort() calls
 * - partition() loops
 * - swap() calls
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

        int[] sizes = new int[10];
        int j = 0;
        for (int i = 10; i < (10 * Math.pow(2,10)); i = i * 2) {
            sizes[j] = i;
            j++;
        }

        // jvm warmup: (may take a minute or two)
        System.out.print("JVM warming up");
        for (int i = 0; i < 100; i++) {
            System.out.print(".");
            BenchmarkSort benchmarkSort = new BenchmarkSort(sizes);
            try {
                benchmarkSort.runSorts();
            } catch (UnsortedException e) {
                System.out.println(e.getMessage() + " during JVM warm up");
            }
        }
        System.out.println();
        System.out.println("Test results: ");

        // actual test:
        BenchmarkSort benchmark = new BenchmarkSort(sizes);

        try {
            benchmark.runSorts();
            benchmark.displayReport();
        } catch (UnsortedException e) {
            System.out.println(e.getMessage());
        }
    }
}
