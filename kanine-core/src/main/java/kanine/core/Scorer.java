package kanine.core;

import java.nio.FloatBuffer;

/**
 * Calculates the inverse score (or <i>distance</i>) for a vector.
 */
public abstract class Scorer {

    /**
     * Get the inverse score for the vector at the given {@code float[] data}
     * with the given {@code offset}.
     *
     * @param data the {@code float[]} that holds the vector's values
     * @param offset where {@code vector[0]} starts
     * @return the inverse score
     */
    protected abstract float inverseScore(float[] data, int offset);

    /**
     * Get the inverse score for the vector at the given {@link FloatBuffer}
     * {@code data} with the given {@code offset}.
     *
     * @param data the {@link FloatBuffer} that holds the vector's values
     * @param offset where {@code vector[0]} starts
     * @return the inverse score
     */
    protected abstract float inverseScore(FloatBuffer data, int offset);
}
