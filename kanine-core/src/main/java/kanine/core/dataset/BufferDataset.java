package kanine.core.dataset;

import java.nio.FloatBuffer;

import kanine.core.accumulator.BestResultsAccumulator;
import kanine.core.scorer.Scorer;

public class BufferDataset implements Dataset {

    private final FloatBuffer data;
    private final int vectorLength;

    public BufferDataset(FloatBuffer data, int vectorLength) {
        this.data = data;
        this.vectorLength = vectorLength;
    }

    public void apply(Scorer scorer, BestResultsAccumulator accum) {
        int size = data.limit() / vectorLength;
        for (int i = 0; i < size; i++) {
            float score = scorer.inverseScore(data, i * vectorLength);
            accum.accumulate(i, score);
        }
    }
}
