package kanine.core.distance;

import org.junit.Test;
import static org.junit.Assert.*;

public class CosineSimilarityTest extends AbstractDistanceTest {

    @Override
    protected Distance getDistance() {
        return new CosineSimilarity(true, 0f);
    }

	@Test
	public void cosine() {
        // TODO split these tests
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
}

