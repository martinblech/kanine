package kanine.core.accumulators;

import kanine.core.Result;

public class BoundedHeapAccumulator implements BestResultsAccumulator {

	private MaxHeap heap;

	public BoundedHeapAccumulator(int n) {
		heap = new MaxHeap(n);
	}

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

	public Result[] get(int n) {
		int size = Math.min(n, heap.size());
		Result[] results = new Result[size];
		for (int i = results.length - 1; i >= 0; i--) {
			Result result = new Result();
			result.index = heap.getMaxIndex();
			result.distance = heap.getMax();
			heap.removeMax();
			results[i] = result;
		}
		return results;
	}

}
