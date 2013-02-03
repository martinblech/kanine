package kanine.core.accumulators;

import kanine.core.Result;

public class QuickSortAccumulator implements BestResultsAccumulator {

	private float[] distances;
	private int[] index;
	private boolean sorted = false;

	public QuickSortAccumulator(int indexSize) {
		this.distances = new float[indexSize];
		this.index = new int[indexSize];
		for (int i = 0; i < indexSize; i++) {
			distances[i] = Float.MAX_VALUE;
			index[i] = i;
		}
	}

	public void accumulate(int index, float distance) {
		if (sorted) {
			throw new IllegalStateException("arrays already sorted");
		}
		distances[index] = distance;
	}

	public Result[] get(int n) {
		if (!sorted) {
			QuickSort.sort(distances, index);
			sorted = true;
		}
        int safeN = Math.min(n, index.length);
		Result[] results = new Result[safeN];
		for (int i = 0; i < safeN; i++) {
			Result result = new Result();
			result.index = index[i];
			result.distance = distances[i];
			results[i] = result;
		}
		return results;
	}

}
