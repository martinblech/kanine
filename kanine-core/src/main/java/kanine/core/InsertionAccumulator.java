package kanine.core;

/**
 * <a href="http://en.wikipedia.org/wiki/Insertion_sort">Insertion
 * sort</a>-backed implementation of {@link BestResultsAccumulator}
 */
public final class InsertionAccumulator extends BestResultsAccumulator {

	private final int[] indexes;
	private final float[] inverseScores;
	private int count;

    /**
     * Create an {@link InsertionAccumulator} with the given {@code capacity}.
     *
     * @param capacity the capacity of this acummulator (it will be able to hold
     * at most this number of best results)
     */
	public InsertionAccumulator(int capacity) {
		this.indexes = new int[capacity];
		this.inverseScores = new float[capacity];
		this.count = 0;
	}

    @Override protected void accumulate(int index, float inverseScore) {
		int i;
		int n = indexes.length;
		for (i = count; i > 0; i--) {
			if (inverseScore < inverseScores[i - 1]) {
				if (i < n) {
					inverseScores[i] = inverseScores[i - 1];
					indexes[i] = indexes[i - 1];
				}
			} else {
				break;
			}
		}
		if (i < n) {
			inverseScores[i] = inverseScore;
			indexes[i] = index;
			if (count < n) {
				count++;
			}
		}
	}

    @Override public Result[] get(int n) {
		Result[] results = new Result[Math.min(count, n)];
		for (int i = 0; i < results.length; i++) {
			results[i] = new Result(indexes[i], inverseScores[i]);
		}
		return results;
	}

}
