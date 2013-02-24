package kanine.core;

/**
 * A collection of {@code float} vectors.
 */
public abstract class Dataset {

    /**
     * Applies the given {@link Scorer} to all vectors in this {@link Dataset},
     * accumulating the best results in the given {@link
     * BestResultsAccumulator}.
     *
     * @param scorer calculates the score for each vector.
     * @param accum accumulates the best {@link Result}s
     * @see Scorer
     * @see BestResultsAccumulator
     * @see Result
     */
    public abstract void apply(Scorer scorer, BestResultsAccumulator accum);

    /**
     * Applies the vector at the given index to the given {@link Scorer} and
     * returns the result.
     *
     * @param index the vector's index
     * @param scorer calculates the score
     * @return the calculated score
     * @see Scorer
     */
    public abstract float score(int index, Scorer scorer);

    /**
     * Reads the contents of the vector at the given {@code index} and stores it
     * in the given {@code buffer}, starting at the given {@code offset}.
     *
     * @param index the index of the vector that has to be read
     * @param buffer stores the vector contents
     * @param offset where {@code vector[0]} will be stored
     */
    public abstract void get(int index, float[] buffer, int offset);
}
