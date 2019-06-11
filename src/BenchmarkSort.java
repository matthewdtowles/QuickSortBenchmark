/**
 * BenchmarkSort Class
 *
 * @author matthew.towles
 */
public class BenchmarkSort {

    static final int HR_LENGTH = 177;
    private int[] sizes;

    public BenchmarkSort(int[] sizes) {
        this.sizes = sizes;
    }

    void runSorts() {}

    /**
     * Displays the results of benchmark
     */
    public void displayReport() {

        printReportHeader();
        // outer loop = which row we are one
        // inner loop = which col we are on with respect to outer loop row
        for (int i = 0; i < sizes.length; i++) {
            printSize(sizes[i]); // this will actually go inside of the j loop and another loop will
            // be inside of that for the printResult
            for (int j = 0; j < 8; j++) {
                printResult(String.valueOf((j+10)*10000));
            }
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
     * @param size
     */
    private void printSize(int size) {
        String sizeString = size + " ";
        System.out.print("|");
        System.out.format("%7s", sizeString);
        System.out.print("|");
    }

    /**
     * Prints result formatted for report
     * @param result
     */
    private void printResult(String result) {
        result = result + " |";
        System.out.format("%21s", result);
    }

}
