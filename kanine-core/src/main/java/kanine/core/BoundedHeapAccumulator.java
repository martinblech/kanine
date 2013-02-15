package kanine.core;

public final class BoundedHeapAccumulator implements BestResultsAccumulator {

	private final MaxHeap heap;

	public BoundedHeapAccumulator(int n) {
		heap = new MaxHeap(n);
	}

    @Override
	public void accumulate(int index, float inverseScore) {
		if (heap.isFull()) {
			if (heap.isEmpty() || inverseScore > heap.getMax()) {
				return;
			}
			heap.replaceMax(index, inverseScore);
		} else {
			heap.insert(index, inverseScore);
		}
	}

    @Override
	public Result[] get(int n) {
		int size = Math.min(n, heap.size());
		Result[] results = new Result[size];
		for (int i = results.length - 1; i >= 0; i--) {
			results[i] = new Result(heap.getMaxIndex(), heap.getMax());
			heap.removeMax();
		}
		return results;
	}

}
