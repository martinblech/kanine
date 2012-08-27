package kanine.benchmark;

import java.util.Random;

import com.google.caliper.Param;
import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;

import kanine.core.distance.Distance;
import kanine.core.distance.CosineSimilarity;
import kanine.core.distance.EuclideanDistance;
import kanine.core.distance.NormalizedAcosDistance;
import kanine.core.distance.OneMinusCosineDistance;
import kanine.core.distance.SquaredEuclideanDistance;
import kanine.core.distance.TaxicabDistance;


public class DistanceSpeed extends SimpleBenchmark {
	
	@Param({"10", "100", "1000", "10000"})
	private int vectorLength;
	
	@Param
	private DistanceType distanceType;
	
	@Param("1000")
	private int nOthers;

	private float[] seed;

	private float[] other;
	
	enum DistanceType {
		TAXICAB {
			@Override
			Distance getDistance() {
				return new TaxicabDistance();
			}
		},
		SQUARED_EUCLIDEAN {
			@Override
			Distance getDistance() {
				return new SquaredEuclideanDistance();
			}
		},
		EUCLIDEAN {
		@Override
			Distance getDistance() {
				return new EuclideanDistance();
			}	
		},
		COSINE {
			@Override
			Distance getDistance() {
				return new CosineSimilarity(false, .5f);
			}
		},
		ONE_MINUS_COSINE {
			@Override
			Distance getDistance() {
				return new OneMinusCosineDistance(new CosineSimilarity(false, .5f));
			}
		},
		NORMALIZED_ACOS {
			@Override
			Distance getDistance() {
				return new NormalizedAcosDistance(new CosineSimilarity(false, .5f));
			}
		};
		abstract Distance getDistance();
	}
	
	private static final float[] randomize(float[] array, Random random) {
		for (int i = 0; i < array.length; i++) {
			array[i] = 100 * (random.nextFloat() * 2 - 1);
		}
		return array;
	}
	
	@Override
	protected void setUp() throws Exception {
		Random random = new Random(0);
		seed = randomize(new float[vectorLength], random);
		other = randomize(new float[nOthers * vectorLength], random);
	}
	
	public float timeDistance(int reps) {
		float sum = 0;
		Distance distanceFunction = distanceType.getDistance();
		for (int i = 0; i < reps; i++) {
			sum += distanceFunction.distance(seed, 0, other,
					(i % nOthers) * vectorLength, vectorLength);
		}
		return sum;
	}
	
	public static void main(String[] args) {
		Runner.main(DistanceSpeed.class, args);
	}
}
