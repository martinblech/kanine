package kanine.core;

public abstract class Dataset {
    public abstract void apply(Scorer scorer, BestResultsAccumulator accum);
}
