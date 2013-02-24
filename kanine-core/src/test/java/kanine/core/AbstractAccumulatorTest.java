package kanine.core;

import java.util.Random;

import kanine.core.BestResultsAccumulator;
import kanine.core.Result;

import org.junit.Test;
import static org.junit.Assert.*;

public abstract class AbstractAccumulatorTest {
	private static final float ERROR = 0f;

	protected abstract BestResultsAccumulator createAccumulator(int indexSize,
			int nBest);

	@Test
	public void basictest() {
		BestResultsAccumulator a = createAccumulator(4, 3);
        testAccumulator(a);
	}

    protected void testAccumulator(BestResultsAccumulator a) {
		a.accumulate(3, 100f);
		a.accumulate(1, 1f);
		a.accumulate(0, 0f);
		a.accumulate(2, 10f);
		Result[] results = a.get(3);

		assertEquals(0, results[0].getIndex());
		assertEquals(0f, results[0].getInverseScore(), ERROR);

		assertEquals(1, results[1].getIndex());
		assertEquals(1f, results[1].getInverseScore(), ERROR);

		assertEquals(2, results[2].getIndex());
		assertEquals(10f, results[2].getInverseScore(), ERROR);
    }

	private static final float[] randomArray(int length) {
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

    @Test
    public void edgeCases() {
        for (int topN = 0; topN <= 1; topN++) {
            for (int n = 0; n <= 1; n++) {
                checkCorrect(topN, randomArray(n));
            }
        }
    }

    @Test
    public void top10percent() {
        for (int n = 10; n <= 10000; n *= 10) {
            checkCorrect(n / 10, randomArray(n));
        }
    }

    @Test
    public void topAll() {
        for (int n = 10; n <= 10000; n *= 10) {
            checkCorrect(n, randomArray(n));
        }
    }

    @Test
    public void sawtooth() {
        for (int n = 10; n <= 10000; n *= 10) {
            checkCorrect(n / 10, sawtooth(n, 10));
        }
    }

	public void checkCorrect(int topN, float[] data) {
		BestResultsAccumulator a = createAccumulator(data.length, topN);
		for (int i = 0; i < data.length; i++) {
			a.accumulate(i, data[i]);
		}
		Result[] results = a.get(topN);
		assertEquals(Math.min(topN, data.length), results.length);
		Result lastResult = null;
		for (Result result : results) {
			if (lastResult != null) {
				assertTrue(
                        lastResult.getInverseScore() <=
                        result.getInverseScore());
			}
			lastResult = result;
		}
	}

}
