import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * BenchmarkSort Class
 *
 * @author matthew.towles
 */
class BenchmarkSort {

    // length of horizontal line rule
    private static final int HR_LENGTH = 177;

    private static final int NUM_OF_DATASETS = 50;

    // sizes of data sets for testing
    private int[] sizes;

    // 50 data sets, each of size sizes[i]
    private int[][] iterativeDataSets;

    private int[] iterativeCounts = new int[NUM_OF_DATASETS];

    private long[] iterativeTimes = new long[NUM_OF_DATASETS];

    private int[][] recursiveDataSets;

    private int[] recursiveCounts = new int[NUM_OF_DATASETS];

    private long[] recursiveTimes = new long[NUM_OF_DATASETS];

    /**
     * These variables will aggregate rather than be replaced
     * as variables above are replaced
     */
    private double[] iterativeAvgCounts;

    private double[] iterativeCoeffCounts;

    private double[] iterativeAvgTimes;

    private double[] iterativeCoeffTimes;

    private double[] recursiveAvgCounts;

    private double[] recursiveCoeffCounts;

    private double[] recursiveAvgTimes;

    private double[] recursiveCoeffTimes;


    /**
     * Constructor
     * @param sizes of arrays to be sorted
     */
    BenchmarkSort(int[] sizes) {
        this.sizes = sizes;
        iterativeAvgCounts = new double[sizes.length];
        iterativeCoeffCounts = new double[sizes.length];
        iterativeAvgTimes = new double[sizes.length];
        iterativeCoeffTimes = new double[sizes.length];
        recursiveAvgCounts = new double[sizes.length];
        recursiveCoeffCounts = new double[sizes.length];
        recursiveAvgTimes = new double[sizes.length];
        recursiveCoeffTimes = new double[sizes.length];
    }

    /**
     * Loop through sizes array e.g.: {10,20,40,80,160,320,640,1280,2560,5120}
     * Create 50 datasets, each with a size = sizes[i]
     * Loop through each data set and sort them recursively and iteratively
     * Save count and time for every sort
     * Get averages of data sets per each size
     * Save averages, coefficient of variances
     * After this is called, displayReport may be called to show results
     */
    void runSorts() throws UnsortedException {
        QuickSort qs = new QuickSort();
        for(int i = 0; i < sizes.length; i++) {
            setDataSets(sizes[i]);

            for (int j = 0; j < NUM_OF_DATASETS; j++) {
                qs.iterativeSort(0, sizes[i] - 1, iterativeDataSets[j]);
                iterativeCounts[j] = qs.getCount();
                iterativeTimes[j] = qs.getTime();

                qs.recursiveSort(0, sizes[i] - 1, recursiveDataSets[j]);
                recursiveCounts[j] = qs.getCount();
                recursiveTimes[j] = qs.getTime();
            }
            iterativeAvgCounts[i] = getCtAverage(iterativeCounts);
            iterativeCoeffCounts[i] = getCtCoeffVar(iterativeCounts, iterativeAvgCounts[i]);
            iterativeAvgTimes[i] = getTmAverage(iterativeTimes);
            iterativeCoeffTimes[i] = getTmCoeffVar(iterativeTimes, iterativeAvgTimes[i]);
            recursiveAvgCounts[i] = getCtAverage(recursiveCounts);
            recursiveCoeffCounts[i] = getCtCoeffVar(recursiveCounts, recursiveAvgCounts[i]);
            recursiveAvgTimes[i] = getTmAverage(recursiveTimes);
            recursiveCoeffTimes[i] = getTmCoeffVar(recursiveTimes, recursiveAvgTimes[i]);
        }
    }


    /**
     * Returns the coefficient of variance
     * @return coefficient of variance of stats based on avg
     */
    private double getCtCoeffVar(int[] stats, double avg) {

        double sum = 0;

        for (int stat : stats) {
            sum = sum + ((stat - avg) * (stat - avg));
        }
        return (Math.sqrt(sum / (stats.length - 1)) / avg);
    }


    /**
     * Returns the coefficient of variance
     * @return coefficient of variance of stats based on avg
     */
    private double getTmCoeffVar(long[] stats, double avg) {

        double sum = 0;

        for (long stat : stats) {
            sum = sum + ((stat - avg) * (stat - avg));
        }
        return (Math.sqrt(sum / (stats.length - 1)) / avg);
    }


    /**
     * Returns the avg of given statistics array
     * @param stats array of performance measurements
     * @return average of all elements in stats array
     */
    private double getCtAverage(int[] stats) {
        double sum = 0;
        for (int stat : stats) {
            sum += stat;
        }
        return sum/stats.length;
    }


    /**
     * Returns the avg of given statistics array
     * @param stats array of performance measurements
     * @return average of all elements in stats array
     */
    private double getTmAverage(long[] stats) {
        double sum = 0;
        for (long stat : stats) {
            sum += stat;
        }
        return sum/stats.length;
    }


    /**
     * Sets each of 50 dataSets with random numbers
     * @param size to indicate which index of sizes array
     */
    private void setDataSets(int size) {
        iterativeDataSets = new int[NUM_OF_DATASETS][size];
        recursiveDataSets = new int[NUM_OF_DATASETS][size];
        for(int i = 0; i < NUM_OF_DATASETS; i++) {
            int[] dataSet = getRandomNumbers(size);

            // copy dataSet so as not to reference to it
            for (int j = 0; j < size; j++) {
                iterativeDataSets[i][j] = dataSet[j];
                recursiveDataSets[i][j] = dataSet[j];
            }
        }
    }


    /**
     * Returns int[size] of random numbers
     * @param size of data set
     * @return randomNumbers
     */
    private int[] getRandomNumbers(int size) {
        int[] randomNumbers = new int[size];
        for (int i = 0; i < size; i++) {
            randomNumbers[i] = (int) (size * Math.random());
        }
        return randomNumbers;
    }


    /**
     * Displays the results of benchmark
     */
    void displayReport() {
        printReportHeader();

        for (int i = 0; i < sizes.length; i++) {
            printSize(sizes[i]);
            printResult(iterativeAvgCounts[i]);
            printResult(iterativeCoeffCounts[i]);
            printResult(iterativeAvgTimes[i]);
            printResult(iterativeCoeffTimes[i]);
            printResult(recursiveAvgCounts[i]);
            printResult(recursiveCoeffCounts[i]);
            printResult(recursiveAvgTimes[i]);
            printResult(recursiveCoeffTimes[i]);
            System.out.println();
            printHr();
        }
    }

    /**
     * Prints Report Header to console
     */
    private void printReportHeader() {
        printHr();
        System.out.println("| Data  |                                      Iterative                                    |                                      Recursive                                    |");
        System.out.println("| Set   |                                                                                   |                                                                                   |");
        System.out.println("|Size n |                                                                                   |                                                                                   |");
        printHr();
        System.out.println("|       | Average            | Coefficient of     | Average            | Coefficient of     | Average            | Coefficient of     | Average            | Coefficient of     |");
        System.out.println("|       | Critical           | Variance of        | Execution          | Variance of        | Critical           | Variance of        | Execution          | Variance of        |");
        System.out.println("|       | Operation          | Count              | Time               | Time               | Operation          | Count              | Time               | Time               |");
        System.out.println("|       | Count              |                    |                    |                    | Count              |                    |                    |                    |");
        printHr();
    }

    /**
     * Prints "-" HR_LENGTH times and terminates with a newline
     */
    private void printHr() {
        for (int i = 0; i < HR_LENGTH; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Prints size formatted for report
     * @param size of array sorted
     */
    private void printSize(int size) {
        String sizeString = size + " ";
        System.out.print("|");
        System.out.format("%7s", sizeString);
        System.out.print("|");
    }

    /**
     * Prints result formatted for report
     * @param result data point for report
     */
    private void printResult(double result) {
        String out = new DecimalFormat("#.################").format(result);
        out += " |";
        System.out.format("%21s", out);
    }

}
