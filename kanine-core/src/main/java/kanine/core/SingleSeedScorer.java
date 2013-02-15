package kanine.core;

import java.nio.FloatBuffer;

public final class SingleSeedScorer extends Scorer {

    private final Distance distance;
    private final float[] seed;

    public SingleSeedScorer(final Distance distance, final float[] seed) {
        this.distance = distance;
        this.seed = seed;
    }

    @Override protected float inverseScore(float[] data, int offset) {
        return distance.distance(seed, 0, data, offset, seed.length);
    }

    @Override protected float inverseScore(FloatBuffer data, int offset) {
        return distance.distance(seed, 0, data, offset, seed.length);
    }

}
