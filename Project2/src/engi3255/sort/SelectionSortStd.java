package engi3255.sort;

public class SelectionSortStd implements Sort, Analyzer {
    public long compares = 0;

    public <T extends Comparable<T>> void SelectionSort(T[] a)
    {
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

    @Override
    public void analyze(int[] sizes, long[] data) {

    }

    @Override
    public double[] getRatios() {
        return new double[0];
    }

    @Override
    public double getError() {
        return 0;
    }

    @Override
    public String getBigOh() {
        return "";
    }
}
