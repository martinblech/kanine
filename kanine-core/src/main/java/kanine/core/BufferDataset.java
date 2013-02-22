package kanine.core;

import java.nio.FloatBuffer;

public final class BufferDataset extends Dataset {

    private final FloatBuffer data;
    private final int vectorLength;

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
