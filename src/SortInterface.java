public interface SortInterface {

    void recursiveSort(int start, int end, int[] data);

    void iterativeSort(int l, int h, int[] arr);

    int getCount();

    long getTime();
}
