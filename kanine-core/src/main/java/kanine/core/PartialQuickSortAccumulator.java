package kanine.core;

import java.util.Arrays;

/**
 * <a href="http://en.wikipedia.org/wiki/Quicksort">Quicksort</a>-backed
 * implementation of {@link BestResultsAccumulator} that sorts only the part
 * that it needs.
 */
public final class PartialQuickSortAccumulator extends BestResultsAccumulator {

	private final float[] inverseScores;
	private final int[] index;
	private int sorted = 0;

    /**
     * Create a {@link PartialQuickSortAccumulator} with the given {@code
     * indexSize}.
     *
     * <p>Note: {@link PartialQuickSortAccumulator} needs to allocate a {@code
     * float[]} and an {@code int[]} of {@code size==indexSize}. This can be an
     * issue with big {@code Dataset}s.
     *
     * @param indexSize accumulated indexes must be {@code 0 <= index <
     * indexSize}
     */
	public PartialQuickSortAccumulator(int indexSize) {
		this.inverseScores = new float[indexSize];
		this.index = new int[indexSize];
        Arrays.fill(inverseScores, Float.MAX_VALUE);
	}

    @Override protected void accumulate(int index, float inverseScore) {
		if (sorted > 0) {
			throw new IllegalStateException("arrays already sorted");
		}
        this.index[index] = index;
		inverseScores[index] = inverseScore;
	}

    @Override public Result[] get(int n) {
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
