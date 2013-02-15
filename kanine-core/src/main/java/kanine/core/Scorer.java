package kanine.core;

import java.nio.FloatBuffer;

public interface Scorer {
    float inverseScore(float[] data, int offset);
    float inverseScore(FloatBuffer data, int offset);
}
