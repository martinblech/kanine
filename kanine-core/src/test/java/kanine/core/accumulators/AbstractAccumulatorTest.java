package kanine.core.accumulators;

import java.util.Random;

import kanine.core.Result;

import org.testng.annotations.*;
import static org.testng.AssertJUnit.*;

public abstract class AbstractAccumulatorTest {
	private static final float ERROR = 0f;

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

		assertEquals(0, results[0].index);
		assertEquals(0f, results[0].distance, ERROR);

		assertEquals(1, results[1].index);
		assertEquals(1f, results[1].distance, ERROR);

		assertEquals(2, results[2].index);
		assertEquals(10f, results[2].distance, ERROR);
	}

	private static float[] randomArray(int length) {
		float[] randomArray = new float[length];
		Random random = new Random(0);
		for (int i = 0; i < randomArray.length; i++) {
			randomArray[i] = random.nextFloat();
		}
        return randomArray;
	}

    private static float[] sawtooth(int length, int period) {
        float[] a = new float[length];
        for (int i = 0; i < a.length; i++) {
            a[i] = i % period;
        }
        return a;
    }

    @DataProvider(name = "accumulator-data")
    public Object[][] createAccumulatorData() {
        return new Object[][] {
            {0, randomArray(0)},
            {0, randomArray(1)},
            {1, randomArray(0)},
            {1, randomArray(10)},
            {5, randomArray(10)},
            {50, randomArray(10)},
            {50, randomArray(1000)},
            {1000, randomArray(1000)},
            {50, sawtooth(1000, 10)}
        };
    }

	@Test(dataProvider = "accumulator-data")
	public void test(int topN, float[] data) {
		BestResultsAccumulator a = createAccumulator(data.length, topN);
		for (int i = 0; i < data.length; i++) {
			a.accumulate(i, data[i]);
		}
		Result[] results = a.get(topN);
		assertEquals(Math.min(topN, data.length), results.length);
		Result lastResult = null;
		for (Result result : results) {
			if (lastResult != null) {
				assertTrue(lastResult.distance <= result.distance);
			}
			lastResult = result;
		}
	}

}
