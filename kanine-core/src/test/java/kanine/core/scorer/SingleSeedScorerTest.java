package kanine.core.scorer;

import java.nio.FloatBuffer;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;

import kanine.core.distance.Distance;

public class SingleSeedScorerTest {

    @Test
    public void array() {
        final Distance distance = mock(Distance.class);
        final float[] seed = new float[] {1f, 2f, 3f, 4f};
        final Scorer scorer = new SingleSeedScorer(distance, seed);
        final float[] data = new float[] {5f, 6f, 7f, 8f, 9f};
        final int offset = 1;
        when(distance.distance(
                    any(float[].class), anyInt(), any(float[].class), anyInt(),
                    anyInt())).thenReturn(-1f);
        assertThat(scorer.inverseScore(data, offset), is(-1f));
        verify(distance).distance(seed, 0, data, offset, seed.length);
    }

    @Test
    public void floatBuffer() {
        final Distance distance = mock(Distance.class);
        final float[] seed = new float[] {1f, 2f, 3f, 4f};
        final Scorer scorer = new SingleSeedScorer(distance, seed);
        final FloatBuffer data = FloatBuffer.wrap(
                new float[] {5f, 6f, 7f, 8f, 9f});
        final int offset = 1;
        when(distance.distance(
                    any(float[].class), anyInt(),
                    any(FloatBuffer.class), anyInt(),
                    anyInt())).thenReturn(-1f);
        assertThat(scorer.inverseScore(data, offset), is(-1f));
        verify(distance).distance(seed, 0, data, offset, seed.length);
    }
}
