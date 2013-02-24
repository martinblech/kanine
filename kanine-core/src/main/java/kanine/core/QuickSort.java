package kanine.core;

final class QuickSort {

	static void sort(float[] main, int[] index) {
		sort(main, index, 0, index.length - 1);
	}

	// quicksort a[left] to a[right]
	static void sort(float[] a, int[] index, int left, int right) {
		if (right <= left)
			return;
		int i = partition(a, index, left, right);
		sort(a, index, left, i - 1);
		sort(a, index, i + 1, right);
	}

	static void select(float[] main, int[] index, int k) {
		select(main, index, 0, index.length - 1, k);
	}

	// quicksort a[left] to a[right]
	static void select(float[] a, int[] index, int left, int right, int k) {
		if (right <= left)
			return;
		int i = partition(a, index, left, right);
		sort(a, index, left, i - 1);
		if (i < k) {
			sort(a, index, i + 1, right);
		}
	}

	// partition a[left] to a[right], assumes left < right
	private static int partition(float[] a, int[] index, int left, int right) {
		int i = left - 1;
		int j = right;
		while (true) {
			while (less(a[++i], a[right]))
				// find item on left to swap
				; // a[right] acts as sentinel
			while (less(a[right], a[--j]))
				// find item on right to swap
				if (j == left)
					break; // don't go out-of-bounds
			if (i >= j)
				break; // check if pointers cross
			exch(a, index, i, j); // swap two elements into place
		}
		exch(a, index, i, right); // swap with partition element
		return i;
	}

	// is x < y ?
	private static boolean less(float x, float y) {
		return (x < y);
	}

	// exchange a[i] and a[j]
	private static void exch(float[] a, int[] index, int i, int j) {
		float swap = a[i];
		a[i] = a[j];
		a[j] = swap;
		int b = index[i];
		index[i] = index[j];
		index[j] = b;
	}
}
