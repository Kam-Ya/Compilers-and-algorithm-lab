
    /**
     * Simple insertion sort.
     * @param a an array of Comparable items.
     */
    public static void InsertionSort( Comparable [ ] a )
    {
        for( int p = 0; p < a.length; p++ )
        {
			int i = p;
			boolean sorted = false;
			while ((i>0) && (!sorted))
			{
				if (a[i].compareTo(a[i-1])<0)
				{
					Comparable tmp = a[i ];
					a[i]=a[i-1];
					a[i-1]=tmp;
					i--;
				}
				else
					sorted = true;
			 }
		  } 
	  }       

