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
    public BufferDataset(FloatBuffer data, int vectorLength) {
        this.data = data;
        this.vectorLength = vectorLength;
    }

    @Override public void apply(Scorer scorer, BestResultsAccumulator accum) {
        int size = data.limit() / vectorLength;
        for (int i = 0; i < size; i++) {
            accum.accumulate(i, score(i, scorer));
        }
    }

    @Override public void get(int index, float[] buf, int offset) {
        data.position(index * vectorLength);
        data.get(buf, offset, vectorLength);
    }

    @Override public float score(int index, Scorer scorer) {
        return scorer.inverseScore(data, index * vectorLength);
    }
}
