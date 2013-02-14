package kanine.core.accumulator;

import kanine.core.Result;

public final class InsertionAccumulator implements BestResultsAccumulator {

	private final int[] indexes;
	private final float[] inverseScores;
	private int count;

	public InsertionAccumulator(int n) {
		this.indexes = new int[n];
		this.inverseScores = new float[n];
		this.count = 0;
	}

    @Override
	public void accumulate(int index, float inverseScore) {
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

    @Override
	public Result[] get(int n) {
		Result[] results = new Result[Math.min(count, n)];
		for (int i = 0; i < results.length; i++) {
			results[i] = new Result(indexes[i], inverseScores[i]);
		}
		return results;
	}

}
