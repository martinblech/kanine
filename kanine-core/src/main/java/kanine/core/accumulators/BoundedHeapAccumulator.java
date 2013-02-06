package kanine.core.accumulators;

import kanine.core.Result;

public final class BoundedHeapAccumulator implements BestResultsAccumulator {

	private final MaxHeap heap;

	public BoundedHeapAccumulator(int n) {
		heap = new MaxHeap(n);
	}

    @Override
	public void accumulate(int index, float distance) {
		if (heap.isFull()) {
			if (heap.isEmpty() || distance > heap.getMax()) {
				return;
			}
			heap.replaceMax(index, distance);
		} else {
			heap.insert(index, distance);
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
