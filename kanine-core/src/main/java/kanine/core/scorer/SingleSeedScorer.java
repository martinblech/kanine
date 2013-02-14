package kanine.core.scorer;

import java.nio.FloatBuffer;

import kanine.core.distance.Distance;

public final class SingleSeedScorer implements Scorer {

    private final Distance distance;
    private final float[] seed;

    public SingleSeedScorer(final Distance distance, final float[] seed) {
        this.distance = distance;
        this.seed = seed;
    }

    @Override
    public float inverseScore(float[] data, int offset) {
        return distance.distance(seed, 0, data, offset, seed.length);
    }

    @Override
    public float inverseScore(FloatBuffer data, int offset) {
        return distance.distance(seed, 0, data, offset, seed.length);
    }

    @Override
    public int vectorLength() {
        return seed.length;
    }

}
