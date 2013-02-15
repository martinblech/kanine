package kanine.core;

import java.nio.FloatBuffer;

public abstract class Scorer {
    protected abstract float inverseScore(float[] data, int offset);
    protected abstract float inverseScore(FloatBuffer data, int offset);
}
