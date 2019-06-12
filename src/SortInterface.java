public interface SortInterface {

    void recursiveSort(int start, int end, int[] data) throws UnsortedException;

    void iterativeSort(int start, int end, int[] data) throws UnsortedException;

    int getCount();

    long getTime();
}
