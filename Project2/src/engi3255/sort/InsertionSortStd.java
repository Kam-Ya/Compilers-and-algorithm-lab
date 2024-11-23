package engi3255.sort;

public class InsertionSortStd implements Sort {
    public int compares = 0;
    public <T extends Comparable<T>> void InsertionSort(T[] x) {
        for( int p = 0; p < x.length; p++ )
        {
            int i = p;
            boolean sorted = false;
            while ((i>0) && (!sorted))
            {
                compares++;
                if (x[i].compareTo(x[i-1]) < 0)
                {
                    T tmp = x[i ];
                    x[i]=x[i-1];
                    x[i-1]=tmp;
                    i--;
                }
                else {
                    sorted = true;
                }
            }
        }
    }

    @Override
    public <T extends Comparable<T>> void sort(T[] a) {
        this.InsertionSort(a);
    }

    @Override
    public long getCompares() {
        return compares;
    }
}
