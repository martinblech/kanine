package kanine.core.distance;

import org.testng.annotations.*;
import static org.testng.AssertJUnit.*;

import static org.mockito.Mockito.*;

public class SqrtDistanceTest extends AbstractDistanceTest {

    @Override
    protected Distance getDistance() {
        return new SqrtDistance(new SquaredEuclideanDistance());
    }

    private Distance mockDistance = mock(Distance.class);
    private Distance d = new SqrtDistance(mockDistance);

    @DataProvider(name = "sqrt-values")
    public Object[][] sqrtValues() {
        return new Object[][] {
            {0f},
            {1f},
            {(float) Math.sqrt(2)},
            {1.5f},
            {4f}
        };
    }

    @Test(dataProvider = "sqrt-values")
    public void testSqrtArray(float value) {
        when(mockDistance.distance(EMPTY, 0, EMPTY, 0, 0)).thenReturn(value);
        assertEquals((float) Math.sqrt(value),
                d.distance(EMPTY, 0, EMPTY, 0, 0), ERROR);
    }

}
