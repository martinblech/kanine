package kanine.core;

import kanine.core.Result;

/**
 * Accumulates {@code (index, inverseScore)} pairs and lets the client get the
 * best ones.
 */
public abstract class BestResultsAccumulator {

    /**
     * Accumulate an {@code (index, inverseScore)} pair.
     *
     * <p>Used internally by {@link Dataset#apply(Scorer,
     * BestResultsAccumulator)}
     *
     * @param index the vector's index within its {@link Dataset}
     * @param inverseScore the vector's calculated score
     * @see Dataset#apply(Scorer, BestResultsAccumulator)
     */
	protected abstract void accumulate(int index, float inverseScore);

    /**
     * Get the best {@code n} {@link Result}s accumulated.
     *
     * @param n the maximum number of {@link Result}s to get
     * @return an array of {@link Result}s with at most {@code n} elements,
     * sorted by increasing {@code inverseScore}
     */
	public abstract Result[] get(int n);
}
