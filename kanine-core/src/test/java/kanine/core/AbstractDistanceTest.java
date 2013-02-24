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
        final int nVectors = 100;
        final int vectorLength = 10;
        List<Object[]> objects = new LinkedList<Object[]>();
        float[] randomFloats = randomArray(nVectors * vectorLength);
        for (int i = 0; i < vectorLength; i++) {
            randomFloats[randomFloats.length - 1 - i] = 0f;
        }
        FloatBuffer buf = FloatBuffer.wrap(randomFloats);
        for (int i = 0; i < nVectors; i++) {
            checkEquals(randomFloats, 0, randomFloats, buf,
                    i * vectorLength, vectorLength);
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
