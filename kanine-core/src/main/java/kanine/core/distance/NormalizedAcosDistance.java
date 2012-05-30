package kanine.core.distance;

public class NormalizedAcosDistance extends Distance {
	private static final float FLOAT_PI = (float) Math.PI;
	private Distance d;

	public NormalizedAcosDistance(Distance d) {
		this.d = d;
	}

	@Override
	public float distance(float[] a, int aOffset, float[] b, int bOffset,
			int length) {
		float cos = d.distance(a, aOffset, b, bOffset, length);
		return (float) Math.acos(cos) / FLOAT_PI;
	}
}
