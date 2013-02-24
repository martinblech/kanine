package kanine.core;

import java.nio.FloatBuffer;

import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

public class SliceDistanceTest extends AbstractDistanceTest {

    @Override protected Distance getDistance() {
        return new SliceDistance(mock(Distance.class), 4, 3);
    }

    @Test public void array() {
        Distance mockDistance = mock(Distance.class);
        SliceDistance d = new SliceDistance(mockDistance, 4, 3);
        float[] a = new float[10];
        float[] b = new float[10];
        d.distance(a, 0, b, 1, 10);
        verify(mockDistance).distance(a, 4, b, 5, 3);
    }

    @Test public void arrayOverflow() {
        SliceDistance d = new SliceDistance(mock(Distance.class), 5, 6);
        try {
            d.distance(EMPTY, 0, EMPTY, 0, 10);
            fail("should fail because slice exceeds vector");
        } catch (AssertionError e) {
            // good
        }
    }

    @Test public void buffer() {
        Distance mockDistance = mock(Distance.class);
        SliceDistance d = new SliceDistance(mockDistance, 4, 3);
        float[] a = new float[10];
        FloatBuffer b = FloatBuffer.wrap(new float[10]);
        d.distance(a, 0, b, 1, 10);
        verify(mockDistance).distance(a, 4, b, 5, 3);
    }

    @Test public void bufferOverflow() {
        SliceDistance d = new SliceDistance(mock(Distance.class), 5, 6);
        try {
            d.distance(EMPTY, 0, FloatBuffer.wrap(EMPTY), 0, 10);
            fail("should fail because slice exceeds vector");
        } catch (AssertionError e) {
            // good
        }
    }

    @Test public void string() {
        final Distance d = mock(Distance.class);
        assertEquals(new SliceDistance(d, 1, 2).toString(),
                String.format(
                    "SliceDistance(distance=%s, sliceOffset=1, sliceLength=2)",
                    d));
    }

}
