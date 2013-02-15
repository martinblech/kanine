package kanine.core;

public class ArrayDataset implements Dataset {

    private final float[] data;
    private final int vectorLength;
    private final int size;

    public ArrayDataset(float[] data, int vectorLength) {
        this.data = data;
        this.vectorLength = vectorLength;
        this.size = data.length / vectorLength;
    }

    public void apply(Scorer scorer, BestResultsAccumulator accum) {
        for (int i = 0; i < size; i++) {
            float score = scorer.inverseScore(data, i * vectorLength);
            accum.accumulate(i, score);
        }
    }
}
