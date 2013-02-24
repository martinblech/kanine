package kanine.core;

import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

public class SqrtDistanceTest extends AbstractDistanceTest {

    @Override
    protected Distance getDistance() {
        return new SqrtDistance(new SquaredEuclideanDistance());
    }

    private Distance mockDistance = mock(Distance.class);
    private Distance d = new SqrtDistance(mockDistance);

    @Test
    public void sqrtValues() {
        for (float f = 0; f < 10; f++) {
            checkSqrt(f/2);
        }
    }

    public void checkSqrt(float value) {
        when(mockDistance.distance(EMPTY, 0, EMPTY, 0, 0)).thenReturn(value);
        assertEquals((float) Math.sqrt(value),
                d.distance(EMPTY, 0, EMPTY, 0, 0),
                ERROR);
    }

    @Test public void string() {
        final Distance d = mock(Distance.class);
        assertEquals(new SqrtDistance(d).toString(),
                String.format("SqrtDistance(%s)", d));
    }

}
