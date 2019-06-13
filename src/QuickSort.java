/**
 * QuickSort Implemented Recursively and Iteratively
 * Used for Benchmarking count of critical ops and real time
 * Count is reset at each initial iterative/recursive sort call
 *
 * @author matthew.towles
 */
public class QuickSort implements SortInterface {

    /**
     * Number of critical operations for sorting
     */
    private int count;

    /**
     * Actual runtime of sorting
     */
    private long time;


    /**
     * QuickSort - Recursive implementation
     *
     * @param start
     * @param end
     * @param data
     */
    @Override
    public void recursiveSort(int start, int end, int[] data) throws UnsortedException {

        // initialize count each time we sort a new data set
        count = 0;

        long begin = System.nanoTime();
        recursiveHelper(start, end, data);
        long finish = System.nanoTime();

        time = finish - begin;

        if (!sorted(data)) {
            throw new UnsortedException("recursiveSort did not sort");
        }
    }


    /**
     * This is the actual QuickSort implementation
     * Called by recursiveSort()
     *
     * @param start
     * @param end
     * @param data
     */
    private void recursiveHelper(int start, int end, int[] data) {
        // +1 for recursive calls
        count++;
        if (end <= start) {
            return;
        }
        int partition = partition(start, end, data);
        recursiveHelper(start, partition - 1, data);
        recursiveHelper(partition + 1, end, data);
    }

    /**
     * QuickSort - Iterative implementation
     *
     * @param start
     * @param end
     * @param data
     */
    @Override
    public void iterativeSort(int start, int end, int[] data) throws UnsortedException {
        count = 0;

        long begin = System.nanoTime();

        int[] stack = new int[end - start + 1];
        int top = -1;
        stack[++top] = start;
        stack[++top] = end;

        while (top >= 0) {

            count++;
            end = stack[top--];
            start = stack[top--];
            int pivot = partition(start,end,data);

            if (pivot - 1 > start) {
                stack[++top] = start;
                stack[++top] = pivot - 1;
            }
            if (pivot + 1 < end) {
                stack[++top] = pivot + 1;
                stack[++top] = end;
            }
        }

        long finish = System.nanoTime();
        time = finish - begin;

        if (!sorted(data)) {
            throw new UnsortedException("recursiveSort did not sort");
        }
    }

    /**
     * Partitions values <= some pivot value to beginning of array
     *
     * @param start
     * @param end
     * @param array
     * @return new index of pivot value
     */
    private int partition(int start, int end, int[] array) {
        int pivot = array[end];
        int storeIndex = start;
        for(int i = start; i < end; i++) {
            // +1 each call to partition loop
            count++;
            if (array[i] <= pivot) {
                swap(storeIndex, i, array);
                storeIndex++;
            }
        }
        swap(storeIndex, end, array);
        return storeIndex;
    }


    /**
     * Swaps array values in given indices
     * @param i
     * @param j
     * @param array
     */
    private void swap(int i, int j, int[] array) {
        // +1 for each swap operation
        count++;
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }


    /**
     * Checks if an array is sorted
     * @param array
     * @return boolean
     */
    private boolean sorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }


    /**
     *
     * @return
     */
    @Override
    public int getCount() {
        return count;
    }

    @Override
    public long getTime() {
        return time;
    }
}
