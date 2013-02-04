package kanine.core.distance;

import java.nio.FloatBuffer;

public class OneMinusCosineDistance extends Distance {

	private Distance d;

	public OneMinusCosineDistance(Distance d) {
		this.d = d;
	}

	@Override
	public float distance(
            float[] a, int aOffset, float[] b, int bOffset, int length) {
		float cos = d.distance(a, aOffset, b, bOffset, length);
		return 1f - ((cos + 1f) / 2f);
	}

	@Override
	public float distance(
            float[] a, int aOffset, FloatBuffer b, int bOffset, int length) {
		float cos = d.distance(a, aOffset, b, bOffset, length);
		return 1f - ((cos + 1f) / 2f);
	}
}
