package kanine.core.distance;

import java.nio.FloatBuffer;

public final class SliceDistance implements Distance {
    private final Distance d;
    private final int sliceOffset;
    private final int sliceLength;
    private final int totalLength;

    public SliceDistance(Distance d, int sliceOffset, int sliceLength) {
        this.d = d;
        this.sliceOffset = sliceOffset;
        this.sliceLength = sliceLength;
        this.totalLength = sliceOffset + sliceLength;
    }

    @Override
    public String toString() {
        return String.format("%s(d=%s, sliceOffset=%s, sliceLength=%s)",
                getClass().getSimpleName(), d, sliceOffset, sliceLength);
    }

    @Override
    public final float distance(
            float[] a, int aOffset, float[] b, int bOffset, int length) {
        assert totalLength <= length;
        return d.distance(a, aOffset + sliceOffset,
                b, bOffset + sliceOffset, sliceLength);
    }

    @Override
    public final float distance(
            float[] a, int aOffset, FloatBuffer b, int bOffset, int length) {
        assert totalLength <= length;
        return d.distance(a, aOffset + sliceOffset,
                b, bOffset + sliceOffset, sliceLength);
    }
}
