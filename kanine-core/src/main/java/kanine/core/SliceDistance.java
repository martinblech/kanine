package kanine.core;

import java.nio.FloatBuffer;

/**
 * Limits the {@link Distance} calculation to a slice of the original vectors.
 */
public final class SliceDistance extends Distance {
    private final Distance d;
    private final int sliceOffset;
    private final int sliceLength;
    private final int totalLength;

    /**
     * Create a {@link SliceDistance} for the given {@link Distance}, {@code
     * sliceOffset} and {@code sliceLength}.
     *
     * @param distance does the actual distance calculation
     * @param sliceOffset the offset of the slice within the original vectors
     * @param sliceLength the length of the slice
     */
    public SliceDistance(final Distance distance, final int sliceOffset, final
            int sliceLength) {
        this.d = distance;
        this.sliceOffset = sliceOffset;
        this.sliceLength = sliceLength;
        this.totalLength = sliceOffset + sliceLength;
    }

    @Override protected float distance(final float[] a, final int aOffset,
            final float[] b, final int bOffset, final int length) {
        assert totalLength <= length;
        return d.distance(a, aOffset + sliceOffset,
                b, bOffset + sliceOffset, sliceLength);
    }

    @Override protected float distance(final float[] a, final int aOffset,
            final FloatBuffer b, final int bOffset, final int length) {
        assert totalLength <= length;
        return d.distance(a, aOffset + sliceOffset,
                b, bOffset + sliceOffset, sliceLength);
    }

    @Override public String toString() {
        return String.format("%s(distance=%s, sliceOffset=%s, sliceLength=%s)",
                getClass().getSimpleName(), d, sliceOffset, sliceLength);
    }
}
