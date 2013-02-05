package kanine.core.distance;

import org.testng.annotations.*;
import static org.testng.AssertJUnit.*;

import static org.mockito.Mockito.*;

public class WeightedSumDistanceTest extends AbstractDistanceTest {

    @Override
    protected Distance getDistance() {
        Distance d1 = mock(Distance.class);
        Distance d2 = mock(Distance.class);
        return new WeightedSumDistance(
                new Distance[] {d1, d2}, new float[] {.5f, .5f});
    }

    @DataProvider(name = "weighted-sum-data")
    public Object[][] generateData() {
        return new Object[][] {
            {1f, 1f, 1f, 1f},
            {1f, 1f, .5f, .5f},
            {4f, 1f, .25f, .75f},
            {400f, .3f, 20f, 75f}
        };
    }

    @Test(dataProvider = "weighted-sum-data")
    public void testWeightedSum(float v1, float v2, float w1, float w2) {
        Distance d1 = mock(Distance.class);
        when(d1.distance(EMPTY, 0, EMPTY, 0, 0)).thenReturn(v1);
        Distance d2 = mock(Distance.class);
        when(d2.distance(EMPTY, 0, EMPTY, 0, 0)).thenReturn(v2);
        Distance d = new WeightedSumDistance(
                new Distance[] {d1, d2}, new float[] {w1, w2});
        assertEquals(
                v1 * w1 + v2 * w2,
                d.distance(EMPTY, 0, EMPTY, 0, 0),
                ERROR);
    }
}

