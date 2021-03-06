package kanine.core;

import java.nio.FloatBuffer;

/**
 * Applies {@code acos(x)/PI} to a cosine similarity to turn it into a distance.
 */
public final class NormalizedAcosDistance extends Distance {

    private static final float FLOAT_PI = (float) Math.PI;
    private final Distance d;

    /**
     * Create a {@link NormalizedAcosDistance} with the given cosine {@code
     * distance}.
     *
     * @param distance the wrapped distance whose value will be normalized
     */
    public NormalizedAcosDistance(final Distance distance) {
        this.d = distance;
    }

    @Override protected float distance(final float[] a, final int aOffset,
            final float[] b, final int bOffset, final int length) {
        float cos = d.distance(a, aOffset, b, bOffset, length);
        return (float) Math.acos(cos) / FLOAT_PI;
    }

    @Override protected float distance(final float[] a, final int aOffset,
            final FloatBuffer b, final int bOffset, final int length) {
        float cos = d.distance(a, aOffset, b, bOffset, length);
        return (float) Math.acos(cos) / FLOAT_PI;
    }

    @Override public String toString() {
        return String.format("%s(%s)", getClass().getSimpleName(), d);
    }
}
