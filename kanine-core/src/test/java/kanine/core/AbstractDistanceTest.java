package kanine.core;

import java.util.*;
import java.nio.FloatBuffer;

import kanine.core.Distance;
import org.junit.Test;
import static org.junit.Assert.*;

public abstract class AbstractDistanceTest {

    protected static final float[] EMPTY = new float[0];

	protected static final float ERROR = .0000001f;

	private static final float[] randomArray(int length) {
		float[] randomArray = new float[length];
		Random random = new Random(0);
		for (int i = 0; i < randomArray.length; i++) {
			randomArray[i] = random.nextFloat();
		}
        return randomArray;
	}

    protected abstract Distance getDistance();

    @Test
    public void arrayBufferEquivalence() {
        List<Object[]> objects = new LinkedList<Object[]>();
        float[] randomFloats = randomArray(1000);
        FloatBuffer buf = FloatBuffer.wrap(randomFloats);
        for (int i = 0; i < 100; i++) {
            checkEquals(randomFloats, 0, randomFloats, buf, i*10, 10);
        }
    }

    public void checkEquals(float[] a, int aOffset, float[] bArray,
            FloatBuffer bBuffer, int bOffset, int length) {
        Distance d = getDistance();
        assertEquals(d.distance(a, aOffset, bArray, bOffset, length),
                d.distance(a, aOffset, bBuffer, bOffset, length),
                ERROR);
    }
}
