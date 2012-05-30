package kanine.core.accumulators;

import kanine.core.accumulators.InsertionAccumulator;

public class InsertionAccumulatorTest extends AbstractAccumulatorTest {

	@Override
	protected InsertionAccumulator createAccumulator(int indexSize, int nBest) {
		return new InsertionAccumulator(nBest);
	}

}
