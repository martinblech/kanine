package kanine.benchmark;

import java.util.Arrays;
import java.util.Random;

import kanine.core.distance.CosineSimilarity;
import kanine.core.distance.Distance;
import kanine.core.distance.EuclideanDistance;
import kanine.core.distance.NormalizedAcosDistance;
import kanine.core.distance.OneMinusCosineDistance;
import kanine.core.distance.SquaredEuclideanDistance;
import kanine.core.distance.TaxicabDistance;


public class DistanceSpeed {

	public static void main(String[] args) {
		int vectorLength = Integer.parseInt(args[0]);
		System.out.println("vector length: " + vectorLength);
		int numVectors = Integer.parseInt(args[1]);
		System.out.println("#vectors: " + numVectors);
		int iterations = Integer.parseInt(args[2]);
		System.out.println("iterations: " + iterations);
		Distance[] functions = new Distance[] { new TaxicabDistance(),
				new SquaredEuclideanDistance(), new EuclideanDistance(),
				new CosineSimilarity(false, .5f),
				new OneMinusCosineDistance(new CosineSimilarity(false, .5f)),
				new NormalizedAcosDistance(new CosineSimilarity(false, .5f)) };
		System.out.println("allocating memory");
		float[] seed = new float[vectorLength];
		float[] data = new float[vectorLength * numVectors];
		System.out.println("initializing random vectors");
		Random random = new Random();
		randomFloats(seed, random, 100, -100);
		randomFloats(data, random, 100, -100);
		System.out.println("seed: " + Arrays.toString(seed));
		System.out.println("warming up");
		for (Distance f : functions) {
			for (int i = 0; i < numVectors; i++) {
				f.distance(seed, 0, data, i * vectorLength, vectorLength);
			}
		}
		System.out.println("benchmarking");
		for (Distance f : functions) {
			System.out.println("----------------");
			System.out.println(f);
			float d = 0;
			long time = System.nanoTime();
			for (int iteration = 0; iteration < iterations; iteration++) {
				for (int i = 0; i < numVectors; i++) {
					d = f.distance(seed, 0, data, i * vectorLength,
							vectorLength);
				}
			}
			time = System.nanoTime() - time;
			if (d != 0) {
				System.out.println(".");
			}
			float nanoseconds = ((float) time);
			nanoseconds /= iterations;
			nanoseconds /= numVectors;
			System.out.println(String.format("average time per vector: %fns",
					nanoseconds));
		}
	}

	private static void randomFloats(float[] data, Random random, float max,
			float min) {
		double diff = (double) max - (double) min;
		for (int i = 0; i < data.length; i++) {
			data[i] = (float) ((random.nextDouble() * diff) - (diff / 2));
		}
	}
}
