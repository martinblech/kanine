package kanine.core;

/**
 * {@code float[]}-backed implementation of {@link Dataset}.
 */
public final class ArrayDataset extends Dataset {

    private final float[] data;
    private final int vectorLength;
    private final int size;

    /**
     * Create an {@link ArrayDataset} with the given {@code data} and {@code
     * vectorLength}.
     *
     * @param data the contents of the dataset's vectors
     * @param vectorLength the length of each vector
     */
    public ArrayDataset(final float[] data, final int vectorLength) {
        this.data = data;
        this.vectorLength = vectorLength;
        this.size = data.length / vectorLength;
    }

    @Override public void apply(
            final Scorer scorer, final BestResultsAccumulator accum) {
        for (int i = 0; i < size; i++) {
            accum.accumulate(i, score(i, scorer));
        }
    }

    @Override public void get(
            final int index, final float[] buf, final int offset) {
        System.arraycopy(data, index * vectorLength, buf, offset, vectorLength);
    }

    @Override public float score(final int index, final Scorer scorer) {
        return scorer.inverseScore(data, index * vectorLength);
    }
}
