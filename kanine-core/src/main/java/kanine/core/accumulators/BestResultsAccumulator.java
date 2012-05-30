package kanine.core.accumulators;

import kanine.core.Result;

public interface BestResultsAccumulator {
	void accumulate(int index, float distance);
	Result[] get(int n);
}
