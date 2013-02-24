package kanine.core;

import java.nio.FloatBuffer;

/**
 * Applies {@code 1 - (x + 1) / 2} to a cosine similarity in order to turn it
 * into a distance.
 */
public final class OneMinusCosineDistance extends Distance {

    private final Distance d;

    /**
     * Create a {@link OneMinusCosineDistance} with the given cosine {@code
     * distance}.
     *
     * @param distance the wrapped distance whose value will be normalized
     */
    public OneMinusCosineDistance(final Distance distance) {
        this.d = distance;
    }

    @Override protected float distance(final float[] a, final int aOffset,
            final float[] b, final int bOffset, final int length) {
        float cos = d.distance(a, aOffset, b, bOffset, length);
        return 1f - (cos + 1f) / 2f;
    }

    @Override protected float distance(final float[] a, final int aOffset,
            final FloatBuffer b, final int bOffset, final int length) {
        float cos = d.distance(a, aOffset, b, bOffset, length);
        return 1f - (cos + 1f) / 2f;
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", getClass().getSimpleName(), d);
    }
}
