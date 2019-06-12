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

    private int[] iterativeCounts;

    private long[] iterativeTimes;

    private int[][] recursiveDataSets;

    private int[] recursiveCounts;

    private long[] recursiveTimes;

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
     * Loop through sizes array {10,20,40,80,160,320,640,1280,2560,5120}
     * Create 50 datasets, each with a size = sizes[i]
     *
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
            iterativeAvgCounts[i] = getAverage(iterativeCounts);
            // todo need to implement getCoeffVar for these guys
            iterativeCoeffCounts[i] = getAverage(iterativeCounts);
            iterativeAvgTimes[i] = getAverage(iterativeTimes);
            // todo need to implement getCoeffVar for these guys
            iterativeCoeffTimes[i] = getAverage(iterativeTimes);
            recursiveAvgCounts[i] = getAverage(recursiveCounts);
            // todo need to implement getCoeffVar for these guys
            recursiveCoeffCounts[i] = getAverage(recursiveCounts);
            recursiveAvgTimes[i] = getAverage(recursiveTimes);
            // todo need to implement getCoeffVar for these guys
            recursiveCoeffTimes[i] = getAverage(recursiveTimes);
        }
    }


    /**
     * Returns the avg of given statistics array
     * @param stats of benchmark
     * @return average of stats
     */
    private double getAverage(int[] stats) {
        double sum = 0;
        for (int stat : stats) {
            sum += stat;
        }
        return sum/stats.length;
    }

    private double getAverage(long[] stats) {
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
            int[] dataSet = getRandomNumbers(sizes.length);
            iterativeDataSets[i] = dataSet;
            recursiveDataSets[i] = dataSet;
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
        // outer loop = which row we are one
        // inner loop = which col we are on with respect to outer loop row
        for (int i = 0; i < sizes.length; i++) {
            printSize(sizes[i]); // this will actually go inside of the j loop and another loop will
            // be inside of that for the printResult

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
        String out = result + " |";
        System.out.format("%21s", out);
    }

}
