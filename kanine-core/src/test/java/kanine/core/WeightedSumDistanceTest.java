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
}

