package kanine.core.distance;

import java.nio.FloatBuffer;

public class CosineSimilarity extends Distance {
	protected boolean failOnZeroNorm;
	protected float defaultValue;

	public CosineSimilarity(boolean failOnZeroNorm, float defaultValue) {
		this.failOnZeroNorm = failOnZeroNorm;
		this.defaultValue = defaultValue;
	}

	@Override
	public float distance(
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
			if (failOnZeroNorm) throw new IllegalArgumentException("zero norm");
            return defaultValue;
		}
		float cos = dotProduct / (float) Math.sqrt(normProduct);
		return cos;
	}

	@Override
	public float distance(
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
		return super.toString()
				+ String.format("(zeronorm=%s)", failOnZeroNorm ? "fail"
						: defaultValue);
	}

}
