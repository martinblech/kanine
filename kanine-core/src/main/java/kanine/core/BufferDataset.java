package kanine.core;

import java.nio.FloatBuffer;

/**
 * {@link FloatBuffer}-backed implementation of {@link Dataset}.
 */
public final class BufferDataset extends Dataset {

    private final FloatBuffer data;
    private final int vectorLength;

    /**
     * Create a {@link BufferDataset} with the given data and vector length.
     *
     * @param data the contents of the dataset's vectors
     * @param vectorLength the length of each vector
     */
    public BufferDataset(final FloatBuffer data, final int vectorLength) {
        this.data = data;
        this.vectorLength = vectorLength;
    }

    @Override public void apply(
            final Scorer scorer, final BestResultsAccumulator accum) {
        final int size = data.limit() / vectorLength;
        for (int i = 0; i < size; i++) {
            accum.accumulate(i, score(i, scorer));
        }
    }

    @Override public void get(
            final int index, final float[] buf, final int offset) {
        data.position(index * vectorLength);
        data.get(buf, offset, vectorLength);
    }

    @Override public float score(final int index, final Scorer scorer) {
        return scorer.inverseScore(data, index * vectorLength);
    }
}
