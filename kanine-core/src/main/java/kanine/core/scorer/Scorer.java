package kanine.core.scorer;

import java.nio.FloatBuffer;

public interface Scorer {
    int vectorLength();
    float inverseScore(float[] data, int offset);
    float inverseScore(FloatBuffer data, int offset);
}
