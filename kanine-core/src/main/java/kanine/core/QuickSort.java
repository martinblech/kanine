package kanine.core;

final class QuickSort {

    private QuickSort() {
        throw new IllegalStateException("should never happen");
    }

    static void sort(final float[] main, final int[] index) {
        sort(main, index, 0, index.length - 1);
    }

    // quicksort a[left] to a[right]
    static void sort(final float[] a, final int[] index, final int left, final
            int right) {
        if (right <= left) { return; }
        int i = partition(a, index, left, right);
        sort(a, index, left, i - 1);
        sort(a, index, i + 1, right);
    }

    static void select(final float[] main, final int[] index, final int k) {
        select(main, index, 0, index.length - 1, k);
    }

    // quicksort a[left] to a[right]
    static void select(final float[] a, final int[] index, final int left, final
            int right, final int k) {
        if (right <= left) { return; }
        int i = partition(a, index, left, right);
        sort(a, index, left, i - 1);
        if (i < k) {
            sort(a, index, i + 1, right);
        }
    }

    // partition a[left] to a[right], assumes left < right
    private static int partition(final float[] a, final int[] index, final int
            left, final int right) {
        int i = left - 1;
        int j = right;
        while (true) {
            do {
                // find item on left to swap
                // a[right] acts as sentinel
                i++;
            } while (less(a[i], a[right]));
            while (less(a[right], a[--j])) {
                // find item on right to swap
                if (j == left) {
                    break; // don't go out-of-bounds
                }
            }
            if (i >= j) {
                break; // check if pointers cross
            }
            exch(a, index, i, j); // swap two elements into place
        }
        exch(a, index, i, right); // swap with partition element
        return i;
    }

    // is x < y ?
    private static boolean less(final float x, final float y) {
        return x < y;
    }

    // exchange a[i] and a[j]
    private static void exch(final float[] a, final int[] index, final int i,
            final int j) {
        float swap = a[i];
        a[i] = a[j];
        a[j] = swap;
        int b = index[i];
        index[i] = index[j];
        index[j] = b;
    }
}
