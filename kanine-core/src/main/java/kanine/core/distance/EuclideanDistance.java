package kanine.core.distance;

public class EuclideanDistance extends Distance {
	SquaredEuclideanDistance squaredEuclidean = new SquaredEuclideanDistance();

	@Override
	public float distance(float[] a, int aOffset, float[] b, int bOffset,
			int length) {
		return (float) Math.sqrt(squaredEuclidean.distance(a, aOffset, b,
				bOffset, length));
	}

}
