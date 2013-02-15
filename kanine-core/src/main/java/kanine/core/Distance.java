package kanine.core;

import java.nio.FloatBuffer;

public interface Distance {
	public abstract float distance(
            float[] a, int aOffset, float[] b, int bOffset, int length);

	public abstract float distance(
            float[] a, int aOffset, FloatBuffer b, int bOffset, int length);
}
