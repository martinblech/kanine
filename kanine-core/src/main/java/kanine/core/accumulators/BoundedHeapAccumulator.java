package kanine.core.accumulators;

import kanine.core.Result;

public class BoundedHeapAccumulator implements BestResultsAccumulator {

	private BinaryHeap heap;

	public BoundedHeapAccumulator(int n) {
		heap = new BinaryHeap(n);
	}

	public void accumulate(int index, float distance) {
		heap.insert(index, distance);
	}

	public Result[] get(int n) {
		int size = Math.min(n, heap.size());
		Result[] results = new Result[size];
		for (int i = 0; i < results.length; i++) {
			Result result = new Result();
			result.index = heap.getMinimumIndex();
			result.distance = heap.getMinimum();
			heap.removeMin();
			results[i] = result;
		}
		return results;
	}

}
