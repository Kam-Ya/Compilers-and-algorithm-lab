package engi3255.sort;

public class BubbleSortStd implements Sort, Analyzer{

    public long compares = 0;
    public <T extends Comparable<T>> void BubbleSort(T[] x) {
        boolean swapped;
        T temp;
        for (int i = 0; i < x.length; i++) {
            swapped = false;
            for (int j = 0; j < x.length - 1 - i; j++) {
                if (x[j].compareTo(x[j + 1]) > 0) {
                    temp = x[j];
                    x[j] = x[j + 1];
                    x[j + 1] = temp;
                    swapped = true;
                }
                compares++;
            }
            if (!swapped) { // no elements were swapped all is good
                break;
            }
        }
    }

    @Override
    public <T extends Comparable<T>> void sort(T[] a) {
        this.BubbleSort(a);
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
