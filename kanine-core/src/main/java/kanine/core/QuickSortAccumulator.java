package kanine.core;

import java.util.Arrays;

/**
 * <a href="http://en.wikipedia.org/wiki/Quicksort">Quicksort</a>-backed
 * implementation of {@link BestResultsAccumulator}.
 */
public final class QuickSortAccumulator extends BestResultsAccumulator {

	private final float[] inverseScores;
	private final int[] index;
	private boolean sorted = false;

    /**
     * Create a {@link QuickSortAccumulator} with the given {@code indexSize}.
     *
     * <p>Note: {@link QuickSortAccumulator} needs to allocate a {@code float[]}
     * and an {@code int[]} of {@code size==indexSize}. This can be an issue
     * with big {@code Dataset}s.
     *
     * @param indexSize accumulated indexes must be {@code 0 <= index <
     * indexSize}
     */
	public QuickSortAccumulator(int indexSize) {
		this.inverseScores = new float[indexSize];
		this.index = new int[indexSize];
        Arrays.fill(inverseScores, Float.MAX_VALUE);
	}

    @Override protected void accumulate(int index, float inverseScore) {
		if (sorted) {
			throw new IllegalStateException("arrays already sorted");
		}
        this.index[index] = index;
		inverseScores[index] = inverseScore;
	}

    @Override public Result[] get(int n) {
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
