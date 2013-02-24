package kanine.core;

import java.nio.FloatBuffer;

/**
 * Calculates the distance between a pair of {@code float} vectors.
 */
public abstract class Distance {

    /**
     * Calculate the distance between the vector defined by {@code a}, {@code
     * aOffset} and {@code length}, and the vector defined by {@code b}, {@code
     * bOffset} and {@code length}.
     *
     * <p>Note: this method and {@link #distance(float[], int, FloatBuffer, int,
     * int)} must produce the same results for equivalent parameters.
     *
     * @param a the source of the first vector's values
     * @param aOffset the index of the first vector's first element
     * @param b the source of the second vector's values
     * @param bOffset the index of the second vector's first element
     * @param length the length of both vectors
     * @return the distance between the two vectors
     */
	protected abstract float distance(
            float[] a, int aOffset, float[] b, int bOffset, int length);

    /**
     * Calculate the distance between the vector defined by {@code a}, {@code
     * aOffset} and {@code length}, and the vector defined by {@code b}, {@code
     * bOffset} and {@code length}.
     *
     * <p>Note: this method and {@link #distance(float[], int, float[], int,
     * int)} must produce the same results for equivalent parameters.
     *
     * @param a the source of the first vector's values
     * @param aOffset the index of the first vector's first element
     * @param b the source of the second vector's values
     * @param bOffset the index of the second vector's first element
     * @param length the length of both vectors
     * @return the distance between the two vectors
     */
	protected abstract float distance(
            float[] a, int aOffset, FloatBuffer b, int bOffset, int length);
}
