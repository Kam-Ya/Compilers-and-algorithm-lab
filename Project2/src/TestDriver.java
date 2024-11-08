public class TestDriver {

    static final int INSERTION = 0, SELECTION = 1, 
	BUBBLE = 2, QUICK = 3;
    static Sort[] sorts = {
	new InsertionSort(),
	new SelectionSort(),
	new BubbleSort(),
	new QuickSort()
    };
    static String[] names = {
	"insertion",
	"selection",
	"bubble",
	"quick"
    };

    static int[] sizes = {
	100, 200, 400, 800, 1600
    };

    static final int SORTED = 0, REVERSE = 1, RANDOM = 2;
    static String[] types = {
	"sorted",
	"reverse",
	"random"
    };

    static long[][] sortedCompares = {
	{99, 199, 399, 799, 1599},
	{4950, 19900, 79800, 319600, 1279200},
	{99, 199, 399, 799, 1599},
	{488, 1173, 2742, 6279, 14152},
    };
    static long[][] reverseCompares = {
	{4950, 19900, 79800, 319600, 1279200},
	{4950, 19900, 79800, 319600, 1279200},
	{4950, 19900, 79800, 319600, 1279200},
	{638, 1543, 3748, 8964, 20968},
    };
    static long[][] randomCompares = {
	{2833, 10392, 39881, 157673, 643482},
	{4950, 19900, 79800, 319600, 1279200},
	{4935, 19809, 79239, 319275, 1278900},
	{672, 1472, 3322, 7629, 17655},
    };
    static long[][][] compares = {
	sortedCompares,
	reverseCompares,
	randomCompares
    };

    static int[][] tests = {
	{10, 20, 30, 40, 50, 60, 70, 80},
	{1, 2, 3, 4, 5, 6, 7, 8, 9},
	{128, 256, 512, 1024, 2048, 4096, 8192},
	{100, 200, 300, 400, 500, 600},
	{2, 4, 8, 16},
	{1, 2, 3, 4, 5, 6, 7, 8, 9},
	{10, 20, 30, 40, 50, 60, 70, 80}
    };
    static long[][] values = {
	{100, 200, 300, 400, 500, 600, 700, 800},
	{1, 8, 27, 64, 125, 216, 343, 512, 719},
	{7, 8, 9, 10, 11, 12, 13},
	{100, 100, 100, 100, 100, 100},
	{2, 8, 24, 64},
	{2, 4, 8, 16, 32, 64, 128, 256, 512},
	{110, 420, 930, 1640, 2550, 3660, 4970, 6480}
    };
    static String[] expected = {
	"O(N)",
	"O(N^3)",
	"O(log N)",
	"O(1)",
	"O(N log N)",
	"O(2^N)",
	"O(N^2)"
    };

    static Comparable[][][] makeData(String[] types, int[] sizes) {
	Comparable[][][] data = new Comparable[types.length][][];
	java.util.Random r = new java.util.Random(0);
	for (int type = 0; type < types.length; type++) {
	    data[type] = new Comparable[sizes.length][];
	    for( int size = 0; size < sizes.length; size++ ) {
		data[type][size] = new Integer[sizes[size]];
		for( int i = 0; i < sizes[size]; i++ )
		    switch(type) {
		    case SORTED:
			data[type][size][i] = new Integer( i );
			break;
		    case REVERSE:
			data[type][size][i] = new Integer(sizes[size]-i);
			break;
		    case RANDOM:
			data[type][size][i] = 
			    new Integer(r.nextInt(sizes[size]));
			break;
		    }
	    }
	}
	return data;
    }

    static void checkData(long[] expected, long[] actual) {
	for (int i = 0; i < expected.length; i++) {
	    if (expected[i] != actual[i]) {
		System.out.println("expected: " + expected[i]);
		System.out.println("actual: " + actual[i]);
		assert false;
	    }
	}
    }
    static void checkSort(Comparable[][] arrays) {
	for( int i = 0; i < arrays.length; i++ ) {
	    for( int j = 0; j < arrays[i].length-1; j++ )
		if( arrays[i][j].compareTo(arrays[i][j+1]) > 0 ) {
		    System.out.println( "array not sorted array["+j+"]=" +
					arrays[i][j] + " array["+ (j+1) +
					"]=" + arrays[i][j+1]);
		    assert false;
		}
	}
    }
    static void testSorts() {
	for (int sort = 0; sort < sorts.length; sort++) {
	    Comparable[][][] data = makeData(types, sizes);
	    for (int type = 0; type < types.length; type++) {
		System.out.println("TESTING: " + names[sort] + " sort with " +
				   types[type] + " data");
		long[] cmps = new long[sizes.length];
		for (int size = 0; size < sizes.length; size++) {
		    sorts[sort].sort( data[type][size] );
		    cmps[size] = sorts[sort].getCompares();
		}
		checkSort(data[type]);
		System.out.println("checking compares");
		checkData(compares[type][sort], cmps);
		System.out.println("\t\tPASSED");
	    }
	    System.out.println();
	}
    }
    static void testAnalyzer() {
	System.out.println("Testing Analyzer");
	Analyzer foo = new AnalyzerImpl();
	for (int test = 0; test < tests.length; test++) {
	    foo.analyze(tests[test], values[test]);
	    if (!expected[test].equals(foo.getBigOh())) {
		System.out.println("expected: " + expected[test]);
		System.out.println("actual: " + foo.getBigOh());
		assert false;
	    }
	}
	System.out.println("\t\tPASSED");
    }
    public static void main (String[] args) {
	testSorts();
	testAnalyzer();
    }
}
