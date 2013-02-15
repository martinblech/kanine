package kanine.core;

import kanine.core.Result;

public abstract class BestResultsAccumulator {
	protected abstract void accumulate(int index, float inverseScore);
	public abstract Result[] get(int n);
}
