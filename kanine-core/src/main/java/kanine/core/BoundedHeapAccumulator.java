package kanine.core;

/**
 * <a href="http://en.wikipedia.org/wiki/Heap_(data_structure)">Heap</a>-backed
 * implementation of {@link BestResultsAccumulator}.
 *
 * <p>Benchmarks show that {@link BoundedHeapAccumulator} outperforms all other
 * {@link BestResultsAccumulator} implementations in {@link kanine.core} for
 * most use cases. As a rule of thumb, clients should always use this unless
 * there is a very clear reason against it.
 */
public final class BoundedHeapAccumulator extends BestResultsAccumulator {

    private final MaxHeap heap;

    /**
     * Create a {@link BoundedHeapAccumulator} with the given {@code capacity}.
     *
     * @param capacity the accumulator's capacity (it will be able to hold at
     * most this number of best results)
     */
    public BoundedHeapAccumulator(final int capacity) {
        heap = new MaxHeap(capacity);
    }

    @Override protected void accumulate(
            final int index, final float inverseScore) {
        if (heap.isFull()) {
            if (heap.isEmpty() || inverseScore > heap.getMax()) {
                return;
            }
            heap.replaceMax(index, inverseScore);
        } else {
            heap.insert(index, inverseScore);
        }
    }

    @Override public Result[] get(final int n) {
        int size = Math.min(n, heap.size());
        Result[] results = new Result[size];
        for (int i = results.length - 1; i >= 0; i--) {
            results[i] = new Result(heap.getMaxIndex(), heap.getMax());
            heap.removeMax();
        }
        return results;
    }

}
