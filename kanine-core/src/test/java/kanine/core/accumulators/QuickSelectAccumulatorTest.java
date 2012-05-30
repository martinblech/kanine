package kanine.core.accumulators;

import kanine.core.accumulators.QuickSelectAccumulator;

public class QuickSelectAccumulatorTest extends AbstractAccumulatorTest {

	@Override
	protected QuickSelectAccumulator createAccumulator(int indexSize, int nBest) {
		return new QuickSelectAccumulator(indexSize);
	}
}
