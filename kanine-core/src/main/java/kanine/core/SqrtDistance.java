package kanine.core;

import java.nio.FloatBuffer;

/**
 * Applies {@code sqrt(x)} over the wrapped distance.
 */
public final class SqrtDistance extends Distance {

    private final Distance d;

    /**
     * Create a {@link SqrtDistance} with the given {@link Distance}.
     *
     * @param distance the distance whose results {@code sqrt} will be applied
     * on
     */
    public SqrtDistance(Distance distance) {
        this.d = distance;
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
