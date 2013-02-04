package kanine.core.distance;

import java.nio.FloatBuffer;

public final class SliceDistance extends Distance {
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
        return super.toString() + String.format(
                "(%s, %s, %s)", d, sliceOffset, sliceLength);
    }

    public final float distance(
            float[] a, int aOffset, float[] b, int bOffset, int length) {
        assert totalLength <= length;
        return d.distance(a, aOffset + sliceOffset,
                b, bOffset + sliceOffset, sliceLength);
    }

    public final float distance(
            float[] a, int aOffset, FloatBuffer b, int bOffset, int length) {
        assert totalLength <= length;
        return d.distance(a, aOffset + sliceOffset,
                b, bOffset + sliceOffset, sliceLength);
    }
}
