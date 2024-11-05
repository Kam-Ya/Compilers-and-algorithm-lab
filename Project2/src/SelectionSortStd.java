
	/**
     * selection sort.
	 * @param a an array of Comparable items.
	 */
		public static void SelectionSort(Comparable[] a)
	{ 
		for (int last = a.length - 1; last >= 1; last--)
		{
			int max = 0;
			for (int i = 1; i <= last; i++)
				if (a[i].compareTo(a[max]) > 0)
					max = i;

			Comparable tmp = a[max];
			a[max] = a[last];
			a[last] = tmp;
		}
	}
	