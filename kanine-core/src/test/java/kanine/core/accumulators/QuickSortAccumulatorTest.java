package kanine.core.accumulators;

import kanine.core.accumulators.QuickSortAccumulator;

public class QuickSortAccumulatorTest extends AbstractAccumulatorTest {

	@Override
	protected QuickSortAccumulator createAccumulator(int indexSize, int nBest) {
		return new QuickSortAccumulator(indexSize);
	}

}
