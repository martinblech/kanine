package kanine.benchmark;

import java.util.Arrays;
import java.util.Random;

import kanine.core.accumulators.BestResultsAccumulator;
import kanine.core.accumulators.BoundedHeapAccumulator;
import kanine.core.accumulators.InsertionAccumulator;
import kanine.core.accumulators.QuickSelectAccumulator;
import kanine.core.accumulators.QuickSortAccumulator;


public class AccumulatorSpeed {
	public static void main(String[] args) {
		int iterations = Integer.parseInt(args[0]);
		int[] indexSizes = new int[] { 10000, 100000, 1000000, 10000000 };
		int[] topNs = new int[] { 1, 10, 100, 1000, 10000 };
		System.out.println(String.format("iterations: %d", iterations));
		System.out.println(String.format("indexSizes (rows): %s",
				Arrays.toString(indexSizes)));
		System.out.println(String.format("topNs (columns): %s",
				Arrays.toString(topNs)));
		Random random = new Random();
		@SuppressWarnings("unchecked")
		Class<? extends BestResultsAccumulator>[] accumulatorClasses = new Class[] {
				BoundedHeapAccumulator.class, InsertionAccumulator.class,
				QuickSelectAccumulator.class, QuickSortAccumulator.class };
		for (Class<? extends BestResultsAccumulator> accumClass : accumulatorClasses) {
			System.out.println("------------");
			System.out.println(String.format("accumulator: %s",
					accumClass.getSimpleName()));
			try {
				for (int indexSize : indexSizes) {
					for (int topN : topNs) {
						if (topN > indexSize) {
							break;
						}
						System.gc();
						// warmup
						BestResultsAccumulator accum = getAccumulator(
								accumClass, indexSize, topN);
						for (int i = 0; i < indexSize; i++) {
							accum.accumulate(i, random.nextFloat());
						}
						accum.get(topN);
						// actual testing
						long t = System.nanoTime();
						for (int iter = 0; iter < iterations; iter++) {
							accum = getAccumulator(accumClass, indexSize, topN);
							for (int i = 0; i < indexSize; i++) {
								accum.accumulate(i, random.nextFloat());
							}
							accum.get(topN);
						}
						t = System.nanoTime() - t;
						float millis = ((float) t) / 1000000f;
						millis /= iterations;
						System.out.print(String.format("%f\t", millis));
					}
					System.out.println();
				}
			} catch (Exception e) {
				System.out.println(String.format("fail: %s", e));
			}
		}
	}

	private static BestResultsAccumulator getAccumulator(
			Class<? extends BestResultsAccumulator> accumClass, int indexSize,
			int topN) {
		if (accumClass == BoundedHeapAccumulator.class) {
			return new BoundedHeapAccumulator(topN);
		} else if (accumClass == InsertionAccumulator.class) {
			return new InsertionAccumulator(topN);
		} else if (accumClass == QuickSelectAccumulator.class) {
			return new QuickSelectAccumulator(indexSize);
		} else if (accumClass == QuickSortAccumulator.class) {
			return new QuickSortAccumulator(indexSize);
		} else {
			throw new IllegalArgumentException();
		}
	}
}
