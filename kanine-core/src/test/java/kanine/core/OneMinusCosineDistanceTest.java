package kanine.core;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

public class OneMinusCosineDistanceTest extends AbstractDistanceTest {

    @Override
    protected Distance getDistance() {
        return new OneMinusCosineDistance(new CosineSimilarity(false, 0f));
    }

    private Distance mockCosine;
    private OneMinusCosineDistance d;

    @Before
    public void setup() {
        mockCosine = mock(Distance.class);
		d = new OneMinusCosineDistance(mockCosine);
    }

    /** omc(1) = 0 */
    @Test
    public void omc1() {
        when(mockCosine.distance(EMPTY, 0, EMPTY, 0, 0)).thenReturn(1f);
        assertEquals(0f, d.distance(EMPTY, 0, EMPTY, 0, 0), ERROR);
    }

    /** omc(.5) = .25 */
    @Test
    public void omc05() {
        when(mockCosine.distance(EMPTY, 0, EMPTY, 0, 0)).thenReturn(.5f);
        assertEquals(.25f, d.distance(EMPTY, 0, EMPTY, 0, 0), ERROR);
    }

    /** omc(0) = .5 */
    @Test
    public void omc0() {
        when(mockCosine.distance(EMPTY, 0, EMPTY, 0, 0)).thenReturn(0f);
        assertEquals(.5f, d.distance(EMPTY, 0, EMPTY, 0, 0), ERROR);
    }

    /** omc(-.5) = .75 */
    @Test
    public void omcM05() {
        when(mockCosine.distance(EMPTY, 0, EMPTY, 0, 0)).thenReturn(-.5f);
        assertEquals(.75f, d.distance(EMPTY, 0, EMPTY, 0, 0), ERROR);
    }

	/** omc(-1) = 1 */
    @Test
    public void omcM1() {
        when(mockCosine.distance(EMPTY, 0, EMPTY, 0, 0)).thenReturn(-1f);
        assertEquals(1f, d.distance(EMPTY, 0, EMPTY, 0, 0), ERROR);
    }

}
