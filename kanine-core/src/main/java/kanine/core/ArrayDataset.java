package kanine.core;

public final class ArrayDataset extends Dataset {

    private final float[] data;
    private final int vectorLength;
    private final int size;

    public ArrayDataset(float[] data, int vectorLength) {
        this.data = data;
        this.vectorLength = vectorLength;
        this.size = data.length / vectorLength;
    }

    @Override public void apply(Scorer scorer, BestResultsAccumulator accum) {
        for (int i = 0; i < size; i++) {
            accum.accumulate(i, score(i, scorer));
        }
    }

    @Override public void get(int index, float[] buf, int offset) {
        System.arraycopy(data, index * vectorLength, buf, offset, vectorLength);
    }

    @Override public float score(int index, Scorer scorer) {
        return scorer.inverseScore(data, index * vectorLength);
    }
}
