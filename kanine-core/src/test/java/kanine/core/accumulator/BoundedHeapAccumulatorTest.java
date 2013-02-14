package kanine.core.accumulator;

public class BoundedHeapAccumulatorTest extends AbstractAccumulatorTest {

	@Override
	protected BoundedHeapAccumulator createAccumulator(int indexSize, int nBest) {
		return new BoundedHeapAccumulator(nBest);
	}

}
