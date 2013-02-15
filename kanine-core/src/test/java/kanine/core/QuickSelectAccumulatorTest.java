package kanine.core;

public class QuickSelectAccumulatorTest extends AbstractAccumulatorTest {

	@Override
	protected PartialQuickSortAccumulator createAccumulator(int indexSize, int nBest) {
		return new PartialQuickSortAccumulator(indexSize);
	}
}
