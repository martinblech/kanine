package kanine.core.accumulators;

import kanine.core.accumulators.BoundedHeapAccumulator;

public class BoundedHeapAccumulatorTest extends AbstractAccumulatorTest {

	@Override
	protected BoundedHeapAccumulator createAccumulator(int indexSize, int nBest) {
		return new BoundedHeapAccumulator(nBest);
	}

}
