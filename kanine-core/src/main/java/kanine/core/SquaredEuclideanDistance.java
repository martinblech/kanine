package kanine.core;

import java.nio.FloatBuffer;

/**
 * Calculates the squared <a
 * href="http://en.wikipedia.org/wiki/Euclidean_distance">Euclidean distance</a>
 * between two vectors.
 *
 * <p>Note: to make it a plain euclidean distance, clients must wrap it with a
 * {@link SqrtDistance}.
 */
public final class SquaredEuclideanDistance extends Distance {

	@Override protected float distance(
            float[] a, int aOffset, float[] b, int bOffset, int length) {
		float d = 0f;
		for (int i = 0; i < length; i++) {
			float ai = a[aOffset + i];
			float bi = b[bOffset + i];
			float diff = ai - bi;
			d += diff * diff;
		}
		return d;
	}

	@Override protected float distance(
            float[] a, int aOffset, FloatBuffer b, int bOffset, int length) {
		float d = 0f;
		for (int i = 0; i < length; i++) {
			float ai = a[aOffset + i];
			float bi = b.get(bOffset + i);
			float diff = ai - bi;
			d += diff * diff;
		}
		return d;
	}

    @Override public String toString() {
        return getClass().getSimpleName();
    }

}
