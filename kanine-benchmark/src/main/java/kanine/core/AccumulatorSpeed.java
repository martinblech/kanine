package kanine.core;

import java.util.Random;

import com.google.caliper.Param;
import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;

import kanine.core.*;

public class AccumulatorSpeed extends SimpleBenchmark {
	public static abstract class AbstractAccumulatorBenchmark
			extends SimpleBenchmark {
		protected float[] data;

		@Override
		protected void setUp() throws Exception {
			data = new float[1000];
			Random random = new Random(0);
			for (int i = 0; i < data.length; i++) {
				data[i] = random.nextFloat();
			}
		}
	}

	public static class AccumulateBenchmark
			extends AbstractAccumulatorBenchmark {
		@Param({
			"BoundedHeap",
			"Insertion",
			"PartialQuickSort",
			"QuickSort"
		})
		protected String accumType;

		@Param({
			"10",
			"100",
			"1000",
			"10000",
			"100000"
		})
		protected int topN;

		protected BestResultsAccumulator accumulator;

		private int indexSize;

		@Override
		protected void setUp() throws Exception {
			super.setUp();
			indexSize = Math.max(topN, data.length);
			accumulator = getAccumulator(
					accumType, indexSize, topN);
			for (int i = 0; i < topN; i++) {
				accumulator.accumulate(i, data[i % data.length]);
			}
		}

		public String timeAccumulate(int reps) {
			for (int i = 0; i < reps; i++) {
				accumulator.accumulate(i % indexSize, data[i % data.length]);
			}
			return accumulator.toString();
		}

		public static void main(String[] args) {
			Runner.main(AccumulateBenchmark.class, args);
		}

	}

	public static class GetTopBenchmark 
			extends AbstractAccumulatorBenchmark {
		@Param({
			"BoundedHeap",
			"Insertion",
			"PartialQuickSort",
			"QuickSort"
		})
		protected String accumType;

		@Param({
			"10",
			"100",
			"1000",
			"10000",
			"100000"
		})
		protected int topN;

		protected BestResultsAccumulator accumulator;

		@Param({
			"1000",
			"10000",
			"100000",
			"1000000",
			"10000000"
		})
		private int indexSize;

		@Override
		protected void setUp() throws Exception {
			super.setUp();
			accumulator = getAccumulator(
					accumType, Math.max(indexSize, topN), topN);
			for (int i = 0; i < indexSize; i++) {
				accumulator.accumulate(i, data[i % data.length]);
			}
		}

		public long timeGetTop(int reps) {
			long count = 0;
			for (int i = 0; i < reps; i++) {
				count += accumulator.get(topN).length;
			}
			return count;
		}

		public static void main(String[] args) {
			Runner.main(GetTopBenchmark.class, args);
		}

	}

	private static BestResultsAccumulator getAccumulator(String accumType,
			int indexSize, int topN) {
		if (accumType.equals("BoundedHeap")) {
			return new BoundedHeapAccumulator(topN);
		} else if (accumType.equals("Insertion")) {
			return new InsertionAccumulator(topN);
		} else if (accumType.equals("PartialQuickSort")) {
			return new PartialQuickSortAccumulator(indexSize);
		} else if (accumType.equals("QuickSort")) {
			return new QuickSortAccumulator(indexSize);
		} else {
			throw new IllegalArgumentException(accumType);
		}
	}
}
