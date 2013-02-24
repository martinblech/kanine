package kanine.core;

import java.nio.FloatBuffer;
import java.util.Arrays;

/**
 * Calculates a weighted sum of a set of {@link Distance}s.
 */
public final class WeightedSumDistance extends Distance {

    private final Distance[] distances;
    private final float[] weights;

    /**
     * Create a {@link WeightedSumDistance} with the given {@link Distance}s and
     * {@code weights}.
     *
     * @param distances the distances
     * @param weights the weights
     * @throws IllegalArgumentException when lengths of {@code distances} and
     * {@code weights} don't match
     */
    public WeightedSumDistance(
            final Distance[] distances, final float[] weights) {
        if (distances.length != weights.length) {
            throw new IllegalArgumentException("length mismatch");
        }
        this.distances = distances;
        this.weights = weights;
    }

    @Override protected float distance(final float[] a, final int aOffset,
            final float[] b, final int bOffset, final int length) {
        float result = 0;
        for (int i = 0; i < distances.length; i++) {
            result += weights[i] * distances[i].distance(
                    a, aOffset, b, bOffset, length);
        }
        return result;
    }

    @Override protected float distance(final float[] a, final int aOffset,
            final FloatBuffer b, final int bOffset, final int length) {
        float result = 0;
        for (int i = 0; i < distances.length; i++) {
            result += weights[i] * distances[i].distance(
                    a, aOffset, b, bOffset, length);
        }
        return result;
    }

    @Override public String toString() {
        return String.format("%s(%s, %s)", getClass().getSimpleName(),
                Arrays.toString(distances),
                Arrays.toString(weights));
    }
}
