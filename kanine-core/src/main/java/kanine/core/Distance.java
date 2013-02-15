package kanine.core;

import java.nio.FloatBuffer;

public abstract class Distance {
	protected abstract float distance(
            float[] a, int aOffset, float[] b, int bOffset, int length);

	protected abstract float distance(
            float[] a, int aOffset, FloatBuffer b, int bOffset, int length);
}
