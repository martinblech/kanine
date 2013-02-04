package kanine.core.distance;

import java.nio.FloatBuffer;

public abstract class Distance {
	public abstract float distance(
            float[] a, int aOffset, float[] b, int bOffset, int length);

	public abstract float distance(
            float[] a, int aOffset, FloatBuffer b, int bOffset, int length);

	@Override
	public String toString() {
		return String.format("<%s>", getClass().getSimpleName());
	}

}
