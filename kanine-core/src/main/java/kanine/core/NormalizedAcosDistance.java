package kanine.core;

import java.nio.FloatBuffer;

public final class NormalizedAcosDistance implements Distance {
	private static final float FLOAT_PI = (float) Math.PI;
	private final Distance d;

	public NormalizedAcosDistance(Distance d) {
		this.d = d;
	}

	@Override
	public float distance(
            float[] a, int aOffset, float[] b, int bOffset, int length) {
		float cos = d.distance(a, aOffset, b, bOffset, length);
		return (float) Math.acos(cos) / FLOAT_PI;
	}

	@Override
	public float distance(
            float[] a, int aOffset, FloatBuffer b, int bOffset, int length) {
		float cos = d.distance(a, aOffset, b, bOffset, length);
		return (float) Math.acos(cos) / FLOAT_PI;
	}

    @Override
    public String toString() {
        return String.format("%s(%s)", getClass().getSimpleName(), d);
    }
}
