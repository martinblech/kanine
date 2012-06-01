package kanine.core.accumulators;

import java.util.Random;

import kanine.core.Result;

import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractAccumulatorTest {
	private static final float ERROR = .000001f;
	private static float[] randomArray;

	protected abstract BestResultsAccumulator createAccumulator(int indexSize,
			int nBest);

	@Test
	public void basictest() {
		BestResultsAccumulator a = createAccumulator(4, 3);
		a.accumulate(3, 100f);
		a.accumulate(1, 1f);
		a.accumulate(0, 0f);
		a.accumulate(2, 10f);
		Result[] results = a.get(3);

		Assert.assertEquals(0, results[0].index);
		Assert.assertEquals(0f, results[0].distance, ERROR);

		Assert.assertEquals(1, results[1].index);
		Assert.assertEquals(1f, results[1].distance, ERROR);

		Assert.assertEquals(2, results[2].index);
		Assert.assertEquals(10f, results[2].distance, ERROR);
	}

	static {
		randomArray = new float[1000];
		Random random = new Random();
		for (int i = 0; i < randomArray.length; i++) {
			randomArray[i] = random.nextFloat();
		}
	}

	@Test
	public void fuzztest() {
		BestResultsAccumulator a = createAccumulator(randomArray.length, 50);
		for (int i = 0; i < randomArray.length; i++) {
			a.accumulate(i, randomArray[i]);
		}
		Result[] results = a.get(50);
		Assert.assertEquals(50, results.length);
		Result lastResult = null;
		for (Result result : results) {
			if (lastResult != null) {
				Assert.assertTrue(lastResult.distance <= result.distance);
			}
			lastResult = result;
		}
	}

}
