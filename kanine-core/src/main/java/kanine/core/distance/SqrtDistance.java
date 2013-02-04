package kanine.core.distance;

import java.nio.FloatBuffer;

public class SqrtDistance extends Distance {
    private final Distance d;

    public SqrtDistance(Distance d) {
        this.d = d;
    }

	@Override
	public float distance(
            float[] a, int aOffset, float[] b, int bOffset, int length) {
		return (float) Math.sqrt(d.distance(a, aOffset, b, bOffset, length));
	}

	@Override
	public float distance(
            float[] a, int aOffset, FloatBuffer b, int bOffset, int length) {
		return (float) Math.sqrt(d.distance(a, aOffset, b, bOffset, length));
	}

}
