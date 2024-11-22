package engi3255.sort;

public class QuickSortStd implements Sort {

    private static final int CUTOFF = 10;
    public int compares = 0;

    public <T extends Comparable<T>> void QuickSort(T[] x) {
        quicksort(x, 0, x.length - 1);
    }

    public static <T extends Comparable<T>> void swaps(T[] a, int ind1, int ind2) {
        T temp = a[ind1];
        a[ind1] = a[ind2];
        a[ind2] = temp;
    }

    private <T extends Comparable<T>> void insert( T[] a, int low, int high )
    {
        for(int p = low + 1; p <= high; p++)
        {
            T tmp = a[ p ];
            int j;

            for(j = p; j > low && tmp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
                compares++;
            }
            a[j] = tmp;
        }
    }

    public <T extends Comparable<T>> void quicksort(T[] a, int low, int high) {
        if (low + CUTOFF > high)
            insert(a, low, high);
        else {
            // Sort low, middle, high
            int middle = (low + high) / 2;
            if (a[middle].compareTo(a[low]) < 0)
                swaps(a, low, middle);
            if (a[high].compareTo(a[low]) < 0)
                swaps(a, low, high);
            if (a[high].compareTo(a[middle]) < 0)
                swaps(a, middle, high);

            // Place pivot at position high - 1
            swaps(a, middle, high - 1);
            T pivot = a[high - 1];

            // Begin partitioning
            int i, j;
            for (i = low, j = high - 1; ; ) {
                while (a[++i].compareTo(pivot) < 0) {
                    compares++;
                }
                    ;
                while (pivot.compareTo(a[--j]) < 0) {
                    compares++;
                }
                    ;
                if (i >= j)
                    break;
                swaps(a, i, j);
            }

            // Restore pivot
            swaps(a, i, high - 1);

            quicksort(a, low, i - 1);    // Sort small elements
            quicksort(a, i + 1, high);

        }
    }

    @Override
    public <T extends Comparable<T>> void sort(T[] a) {
        this.QuickSort(a);
    }

    @Override
    public long getCompares() {
        return 0;
    }
}
