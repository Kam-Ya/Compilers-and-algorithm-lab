public class BubbleSort {
    public <T extends Comparable<T>> BubbleSort(T[] x) {
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
            }
            if (!swapped) { // no elements were swapped all is good
                break;
            }
        }
    }
}
