package kanine.core.distance;

import org.testng.annotations.*;
import static org.testng.AssertJUnit.*;

import static org.mockito.Mockito.*;

public class WeightedSumDistanceTest extends AbstractDistanceTest {

    @Override
    protected Distance getDistance() {
        final Distance d1 = new SquaredEuclideanDistance();
        final Distance d2 = new SquaredEuclideanDistance();
        return new WeightedSumDistance(
                new Distance[] {d1, d2}, new float[] {.75f, .25f});
    }

    @DataProvider(name = "weighted-sum-data")
    public Object[][] generateData() {
        return new Object[][] {
            {0}, {1}, {2}, {3}, {4}
        };
    }

    @Test(dataProvider = "weighted-sum-data")
    public void testWeightedSum(int n) {
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

