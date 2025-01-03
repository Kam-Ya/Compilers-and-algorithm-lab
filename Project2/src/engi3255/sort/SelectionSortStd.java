package engi3255.sort;

public class SelectionSortStd implements Sort {
    public long compares = 0;

    public <T extends Comparable<T>> void SelectionSort(T[] a)
    {
        compares = 0;
        for (int last = a.length - 1; last >= 1; last--)
        {
            int max = 0;
            for (int i = 1; i <= last; i++) {
                if (a[i].compareTo(a[max]) > 0) {
                    max = i;
                }
                compares++;
            }

            T tmp = a[max];
            a[max] = a[last];
            a[last] = tmp;
        }
    }

    @Override
    public <T extends Comparable<T>> void sort(T[] a) {
        this.SelectionSort(a);
    }

    @Override
    public long getCompares() {
        return compares;
    }
}
