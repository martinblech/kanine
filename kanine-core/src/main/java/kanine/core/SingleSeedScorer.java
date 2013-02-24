package kanine.core;

import java.nio.FloatBuffer;

/**
 * Scores vectors according to their {@link Distance} from a given {@code seed}.
 */
public final class SingleSeedScorer extends Scorer {

    private final Distance distance;
    private final float[] seed;

    /**
     * Create a {@link SingleSeedScorer} with the given {@link Distance} and
     * {@code seed} vector.
     *
     * @param distance the {@link Distance}
     * @param seed the seed vector
     */
    public SingleSeedScorer(final Distance distance, final float[] seed) {
        this.distance = distance;
        this.seed = seed;
    }

    @Override protected float inverseScore(
            final float[] data, final int offset) {
        return distance.distance(seed, 0, data, offset, seed.length);
    }

    @Override protected float inverseScore(
            final FloatBuffer data, final int offset) {
        return distance.distance(seed, 0, data, offset, seed.length);
    }

}
