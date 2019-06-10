import java.util.Stack;

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
    public void recursiveSort(int start, int end, int[] data) {
        if (end <= start) {
            return;
        }

        int partition = partition(start, end, data);
        recursiveSort(start, partition - 1, data);
        recursiveSort(partition + 1, end, data);
    }

    @Override
    public void iterativeSort(int l, int h, int[] arr) {
        // create auxiliary stack
        int stack[] = new int[h - l + 1];

        // initialize top of stack
        int top = -1;

        // push initial values in the stack
        stack[++top] = l;
        stack[++top] = h;

        // keep popping elements until stack is not empty
        while (top >= 0) {
            // pop h and l
            h = stack[top--];
            l = stack[top--];

            // set pivot element at it's proper position
            int p = partition(l,h,arr);

            // If there are elements on left side of pivot,
            // then push left side to stack
            if (p - 1 > l) {
                stack[++top] = l;
                stack[++top] = p - 1;
            }

            // If there are elements on right side of pivot,
            // then push right side to stack
            if (p + 1 < h) {
                stack[++top] = p + 1;
                stack[++top] = h;
            }
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
    private static void swap(int i, int j, int[] array) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public long getTime() {
        return 0;
    }
}
