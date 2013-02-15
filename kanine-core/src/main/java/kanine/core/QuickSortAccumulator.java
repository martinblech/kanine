package kanine.core;

public final class QuickSortAccumulator implements BestResultsAccumulator {

	private final float[] inverseScores;
	private final int[] index;
	private boolean sorted = false;

	public QuickSortAccumulator(int indexSize) {
		this.inverseScores = new float[indexSize];
		this.index = new int[indexSize];
		for (int i = 0; i < indexSize; i++) {
			inverseScores[i] = Float.MAX_VALUE;
			index[i] = i;
		}
	}

    @Override
	public void accumulate(int index, float inverseScore) {
		if (sorted) {
			throw new IllegalStateException("arrays already sorted");
		}
		inverseScores[index] = inverseScore;
	}

    @Override
	public Result[] get(int n) {
		if (!sorted) {
			QuickSort.sort(inverseScores, index);
			sorted = true;
		}
        int safeN = Math.min(n, index.length);
		Result[] results = new Result[safeN];
		for (int i = 0; i < safeN; i++) {
			results[i] = new Result(index[i], inverseScores[i]);
		}
		return results;
	}

}
