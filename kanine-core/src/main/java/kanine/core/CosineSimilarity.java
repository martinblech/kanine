package kanine.core;

import java.nio.FloatBuffer;

/**
 * Calculates the <a
 * href="http://en.wikipedia.org/wiki/Cosine_similarity">Cosine Similarity</a>
 * between pairs of vectors.
 *
 * <p>This is not a proper {@link Distance} and should be wrapped with a {@link
 * NormalizedAcosDistance} or a {@link OneMinusCosineDistance} in order to
 * produce sane results.
 */
public final class CosineSimilarity extends Distance {

	private final boolean failOnZeroNorm;
	private final float defaultValue;

    /**
     * Create a {@link CosineSimilarity}.
     *
     * @param failOnZeroNorm if {@code true}, throws an {@link
     * ArithmeticException} when undefined
     * @param defaultValue if {@code failOnZeroNorm==false}, falls back to this
     * value when undefined
     */
	public CosineSimilarity(boolean failOnZeroNorm, float defaultValue) {
		this.failOnZeroNorm = failOnZeroNorm;
		this.defaultValue = defaultValue;
	}

	@Override protected float distance(
            float[] a, int aOffset, float[] b, int bOffset, int length) {
		float dotProduct = 0f;
		float aNorm = 0f;
		float bNorm = 0f;
		for (int i = 0; i < length; i++) {
			float ai = a[aOffset + i];
			float bi = b[bOffset + i];
			dotProduct += ai * bi;
			aNorm += ai * ai;
			bNorm += bi * bi;
		}
        float normProduct = aNorm * bNorm;
		if (normProduct == 0) {
			if (failOnZeroNorm) throw new ArithmeticException("zero norm");
            return defaultValue;
		}
		float cos = dotProduct / (float) Math.sqrt(normProduct);
		return cos;
	}

	@Override protected float distance(
            float[] a, int aOffset, FloatBuffer b, int bOffset, int length) {
		float dotProduct = 0f;
		float aNorm = 0f;
		float bNorm = 0f;
		for (int i = 0; i < length; i++) {
			float ai = a[aOffset + i];
			float bi = b.get(bOffset + i);
			dotProduct += ai * bi;
			aNorm += ai * ai;
			bNorm += bi * bi;
		}
        float normProduct = aNorm * bNorm;
		if (normProduct == 0) {
			if (failOnZeroNorm) throw new IllegalArgumentException("zero norm");
            return defaultValue;
		}
		float cos = dotProduct / (float) Math.sqrt(normProduct);
		return cos;
	}

	@Override
	public String toString() {
		return String.format("%s(zeronorm=%s)", getClass().getSimpleName(),
                failOnZeroNorm ? "fail" : defaultValue);
	}

}
