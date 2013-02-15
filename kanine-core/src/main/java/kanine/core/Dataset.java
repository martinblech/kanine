package kanine.core;

public interface Dataset {
    void apply(Scorer scorer, BestResultsAccumulator bestResultsAccumulator);
}
