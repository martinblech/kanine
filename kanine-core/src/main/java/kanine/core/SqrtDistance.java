package kanine.core;

import java.nio.FloatBuffer;

public final class SqrtDistance extends Distance {

    private final Distance d;

    public SqrtDistance(Distance d) {
        this.d = d;
    }

	@Override protected float distance(
            float[] a, int aOffset, float[] b, int bOffset, int length) {
		return (float) Math.sqrt(d.distance(a, aOffset, b, bOffset, length));
	}

	@Override protected float distance(
            float[] a, int aOffset, FloatBuffer b, int bOffset, int length) {
		return (float) Math.sqrt(d.distance(a, aOffset, b, bOffset, length));
	}

    @Override public String toString() {
        return String.format("%s(%s)", getClass().getSimpleName(), d);
    }

}
