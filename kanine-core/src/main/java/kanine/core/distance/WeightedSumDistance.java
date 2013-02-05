package kanine.core.distance;

import java.nio.FloatBuffer;

public class WeightedSumDistance extends Distance {

    private final Distance[] distances;
    private final float[] weights;

    public WeightedSumDistance(Distance[] distances, float[] weights) {
        if (distances.length != weights.length) {
            throw new IllegalArgumentException("length mismatch");
        }
        this.distances = distances;
        this.weights = weights;
    }

    public float distance(
            float[] a, int aOffset, float[] b, int bOffset, int length) {
        float result = 0;
        for (int i = 0; i < distances.length; i++) {
            result += weights[i] * distances[i].distance(
                    a, aOffset, b, bOffset, length);
        }
        return result;
    }

    public float distance(
            float[] a, int aOffset, FloatBuffer b, int bOffset, int length) {
        float result = 0;
        for (int i = 0; i < distances.length; i++) {
            result += weights[i] * distances[i].distance(
                    a, aOffset, b, bOffset, length);
        }
        return result;
    }
}
