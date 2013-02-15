package kanine.core;

public final class PartialQuickSortAccumulator
        implements BestResultsAccumulator {

	private final float[] inverseScores;
	private final int[] index;
	private int sorted = 0;

	public PartialQuickSortAccumulator(int indexSize) {
		this.inverseScores = new float[indexSize];
		this.index = new int[indexSize];
		for (int i = 0; i < indexSize; i++) {
			inverseScores[i] = Float.MAX_VALUE;
			index[i] = i;
		}
	}

    @Override
	public void accumulate(int index, float inverseScore) {
		if (sorted > 0) {
			throw new IllegalStateException("arrays already sorted");
		}
		inverseScores[index] = inverseScore;
	}

    @Override
	public Result[] get(int n) {
		if (sorted < n) {
			QuickSort.select(inverseScores, index, n);
			sorted = n;
		}
        int safeN = Math.min(n, index.length);
		Result[] results = new Result[safeN];
		for (int i = 0; i < safeN; i++) {
			results[i] = new Result(index[i], inverseScores[i]);
		}
		return results;
	}

}
