package kanine.core.dataset;

import kanine.core.accumulator.BestResultsAccumulator;
import kanine.core.scorer.Scorer;

public interface Dataset {
    void apply(Scorer scorer, BestResultsAccumulator bestResultsAccumulator);
}
