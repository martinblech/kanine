package kanine.core;

public abstract class Dataset {
    public abstract void apply(Scorer scorer, BestResultsAccumulator accum);
    public abstract void get(int index, float[] buf, int offset);
    public abstract float score(int index, Scorer scorer);
}
