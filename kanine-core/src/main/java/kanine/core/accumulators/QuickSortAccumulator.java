package kanine.core.accumulators;

import kanine.core.Result;

public final class QuickSortAccumulator implements BestResultsAccumulator {

	private final float[] distances;
	private final int[] index;
	private boolean sorted = false;

	public QuickSortAccumulator(int indexSize) {
		this.distances = new float[indexSize];
		this.index = new int[indexSize];
		for (int i = 0; i < indexSize; i++) {
			distances[i] = Float.MAX_VALUE;
			index[i] = i;
		}
	}

    @Override
	public void accumulate(int index, float distance) {
		if (sorted) {
			throw new IllegalStateException("arrays already sorted");
		}
		distances[index] = distance;
	}

    @Override
	public Result[] get(int n) {
		if (!sorted) {
			QuickSort.sort(distances, index);
			sorted = true;
		}
        int safeN = Math.min(n, index.length);
		Result[] results = new Result[safeN];
		for (int i = 0; i < safeN; i++) {
			results[i] = new Result(index[i], distances[i]);
		}
		return results;
	}

}
