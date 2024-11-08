public class InsertionSort {
    public int compares = 0;
    public <T extends Comparable<T>> int InsertionSort(T[] x) {
        for( int p = 0; p < a.length; p++ )
        {
            int i = p;
            boolean sorted = false;
            while ((i>0) && (!sorted))
            {
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
                compares++;
            }
        }
        return compares;
    }
}
