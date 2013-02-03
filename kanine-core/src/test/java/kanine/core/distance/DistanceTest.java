package kanine.core.distance;

import kanine.core.distance.CosineSimilarity;
import kanine.core.distance.Distance;
import kanine.core.distance.EuclideanDistance;
import kanine.core.distance.NormalizedAcosDistance;
import kanine.core.distance.OneMinusCosineDistance;
import kanine.core.distance.SquaredEuclideanDistance;

import org.testng.annotations.*;
import static org.testng.AssertJUnit.*;

public class DistanceTest {

	private static final float ERROR = .0000001f;

	@Test
	public void cosine() {
		CosineSimilarity d = new CosineSimilarity(true, 0f);
		// cos([1,1], [1,1]) = 1
		assertEquals(1f, d.distance(new float[] { 1f, 1f }, 0,
				new float[] { 1f, 1f }, 0, 2), ERROR);
		// cos([1,0], [0,1]) = 0
		assertEquals(0f, d.distance(new float[] { 0f, 1f }, 0,
				new float[] { 1f, 0f }, 0, 2), ERROR);
		// cos([1,0], [1,1]) = sqrt(.5)
		assertEquals(Math.sqrt(.5), d.distance(new float[] { 0f, 1f },
				0, new float[] { 1f, 1f }, 0, 2), ERROR);
		// fail on zero norm
		try {
			d.distance(new float[] { 0f, 0f }, 0, new float[] { 1f, 1f }, 0, 2);
			fail();
		} catch (IllegalArgumentException e) {
		}
		try {
			d.distance(new float[] { 1f, 1f }, 0, new float[] { 0f, 0f }, 0, 2);
			fail();
		} catch (IllegalArgumentException e) {
		}
		try {
			d.distance(new float[] { 0f, 0f }, 0, new float[] { 0f, 0f }, 0, 2);
			fail();
		} catch (IllegalArgumentException e) {
		}
		// default on zero norm
		assertEquals(
				.12345f,
				new CosineSimilarity(false, .12345f).distance(new float[] { 0f,
						0f }, 0, new float[] { 0f, 0f }, 0, 2), ERROR);
	}

	class MockDistance extends Distance {
		public float d;

		@Override
		public float distance(float[] a, int aOffset, float[] b, int bOffset,
				int length) {
			return d;
		}
	}

	@Test
	public void normalizedAcos() {
		MockDistance mockCosine = new MockDistance();
		NormalizedAcosDistance d = new NormalizedAcosDistance(mockCosine);
		// acos(1)/PI = 0
		mockCosine.d = 1f;
		assertEquals(0f, d.distance(null, 0, null, 0, 0), ERROR);
		// acos(sqrt(.5))/PI = .25
		mockCosine.d = (float) Math.sqrt(.5f);
		assertEquals(.25f, d.distance(null, 0, null, 0, 0), ERROR);
		// acos(0)/PI = .5
		mockCosine.d = 0f;
		assertEquals(.5f, d.distance(null, 0, null, 0, 0), ERROR);
		// acos(-sqrt(.5))/PI = .75
		mockCosine.d = (float) -Math.sqrt(.5f);
		assertEquals(.75f, d.distance(null, 0, null, 0, 0), ERROR);
		// acos(-1)/PI = 1
		mockCosine.d = -1f;
		assertEquals(1f, d.distance(null, 0, null, 0, 0), ERROR);
	}

	@Test
	public void oneMinusCosine() {
		MockDistance mockCosine = new MockDistance();
		OneMinusCosineDistance d = new OneMinusCosineDistance(mockCosine);
		// omc(1) = 0
		mockCosine.d = 1f;
		assertEquals(0f, d.distance(null, 0, null, 0, 0), ERROR);
		// omc(.5) = .25
		mockCosine.d = .5f;
		assertEquals(.25f, d.distance(null, 0, null, 0, 0), ERROR);
		// omc(0) = .5
		mockCosine.d = 0f;
		assertEquals(.5f, d.distance(null, 0, null, 0, 0), ERROR);
		// omc(-.5) = .75
		mockCosine.d = -.5f;
		assertEquals(.75f, d.distance(null, 0, null, 0, 0), ERROR);
		// omc(-1) = 1
		mockCosine.d = -1f;
		assertEquals(1f, d.distance(null, 0, null, 0, 0), ERROR);
	}

	@Test
	public void sqEuclidean() {
		SquaredEuclideanDistance d = new SquaredEuclideanDistance();
		// euc([1,1], [1,1]) = 0
		assertEquals(0f, d.distance(new float[] { 1f, 1f }, 0,
				new float[] { 1f, 1f }, 0, 2), ERROR);
		// euc([1,0], [0,1]) = 2
		assertEquals(2, d.distance(new float[] { 0f, 1f }, 0,
				new float[] { 1f, 0f }, 0, 2), ERROR);
		// euc([1,0], [1,1]) = 1
		assertEquals(1f, d.distance(new float[] { 0f, 1f }, 0,
				new float[] { 1f, 1f }, 0, 2), ERROR);
	}
	
	@Test
	public void euclidean() {
		EuclideanDistance d = new EuclideanDistance();
		// euc([1,1], [1,1]) = 0
		assertEquals(0f, d.distance(new float[] { 1f, 1f }, 0,
				new float[] { 1f, 1f }, 0, 2), ERROR);
		// euc([1,0], [0,1]) = sqrt(2)
		assertEquals(Math.sqrt(2), d.distance(new float[] { 0f, 1f }, 0,
				new float[] { 1f, 0f }, 0, 2), ERROR);
		// euc([1,0], [1,1]) = 1
		assertEquals(1f, d.distance(new float[] { 0f, 1f }, 0,
				new float[] { 1f, 1f }, 0, 2), ERROR);
	}

}
