package kanine.core;

import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

public class WeightedSumDistanceTest extends AbstractDistanceTest {

    @Override
    protected Distance getDistance() {
        final Distance d1 = new SquaredEuclideanDistance();
        final Distance d2 = new SquaredEuclideanDistance();
        return new WeightedSumDistance(
                new Distance[] {d1, d2}, new float[] {.75f, .25f});
    }

    @Test
    public void weightedSum() {
        for (int i = 0; i < 10; i++) {
            checkWeightedSum(i);
        }
    }

    public void checkWeightedSum(int n) {
        final Distance[] distances = new Distance[n];
        final float[] weights = new float[n];
        float total = 0;
        for (int i = 0; i < n; i++) {
            float v = (float) i + 1;
            final Distance d = mock(Distance.class);
            when(d.distance(EMPTY, 0, EMPTY, 0, 0)).thenReturn(v);
            distances[i] = d;
            weights[i] = v;
            total += v * v;
        }
        final Distance d = new WeightedSumDistance(distances, weights);
        assertEquals(total, d.distance(EMPTY, 0, EMPTY, 0, 0), ERROR);
        for (Distance d2: distances) {
            verify(d2).distance(EMPTY, 0, EMPTY, 0, 0);
        }
    }

    @Test public void lengthMismatch() {
        try {
            new WeightedSumDistance(new Distance[3], new float[4]);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test public void string() {
        final Distance d1 = mock(Distance.class);
        final Distance d2 = mock(Distance.class);
        final Distance[] ds = new Distance[] {d1, d2};
        final float w1 = .5f;
        final float w2 = .3f;
        final float[] ws = new float[] {w1, w2};
        assertEquals(new WeightedSumDistance(ds, ws).toString(),
                String.format("WeightedSumDistance([%s, %s], [%s, %s])",
                    d1, d2, w1, w2));
    }
}

