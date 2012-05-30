package kanine.core.distance;

public class CosineSimilarity extends Distance {
	protected boolean failOnZeroNorm;
	protected float defaultValue;

	public CosineSimilarity(boolean failOnZeroNorm, float defaultValue) {
		this.failOnZeroNorm = failOnZeroNorm;
		this.defaultValue = defaultValue;
	}

	@Override
	public float distance(float[] a, int aOffset, float[] b, int bOffset,
			int length) {
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
		if (aNorm == 0 || bNorm == 0) {
			if (failOnZeroNorm) {
				throw new IllegalArgumentException(
						"can't calculate cosine of a zero-norm vector");
			} else {
				return defaultValue;
			}
		}
		float cos = dotProduct / (float) Math.sqrt(aNorm * bNorm);
		return cos;
	}

	@Override
	public String toString() {
		return super.toString()
				+ String.format("(zeronorm=%s)", failOnZeroNorm ? "fail"
						: defaultValue);
	}

}
