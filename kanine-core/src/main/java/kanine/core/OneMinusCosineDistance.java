package kanine.core;

import java.nio.FloatBuffer;

public final class OneMinusCosineDistance extends Distance {

	private final Distance d;

	public OneMinusCosineDistance(Distance d) {
		this.d = d;
	}

	@Override protected float distance(
            float[] a, int aOffset, float[] b, int bOffset, int length) {
		float cos = d.distance(a, aOffset, b, bOffset, length);
		return 1f - ((cos + 1f) / 2f);
	}

	@Override protected float distance(
            float[] a, int aOffset, FloatBuffer b, int bOffset, int length) {
		float cos = d.distance(a, aOffset, b, bOffset, length);
		return 1f - ((cos + 1f) / 2f);
	}

    @Override
    public String toString() {
        return String.format("%s(%s)", getClass().getSimpleName(), d);
    }
}
