package kanine.core.accumulator;

import kanine.core.Result;

public interface BestResultsAccumulator {
	void accumulate(int index, float inverseScore);
	Result[] get(int n);
}
