package kanine.core;

import java.nio.FloatBuffer;

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
