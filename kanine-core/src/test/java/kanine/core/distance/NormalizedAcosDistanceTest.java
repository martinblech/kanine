package kanine.core.distance;

import org.testng.annotations.*;
import static org.testng.AssertJUnit.*;

import static org.mockito.Mockito.*;

public class NormalizedAcosDistanceTest extends AbstractDistanceTest {

    @Override
    protected Distance getDistance() {
        return new NormalizedAcosDistance(new CosineSimilarity(false, 0f));
    }

    private Distance mockCosine;
    private NormalizedAcosDistance d;

    @BeforeMethod
    public void setup() {
        mockCosine = mock(Distance.class);
		d = new NormalizedAcosDistance(mockCosine);
    }

    /** acos(1)/PI = 0 */
    @Test
    public void acosOf1() {
        when(mockCosine.distance(EMPTY, 0, EMPTY, 0, 0)).thenReturn(1f);
		assertEquals(0f, d.distance(EMPTY, 0, EMPTY, 0, 0), ERROR);
    }

	/** acos(sqrt(.5))/PI = .25 */
    @Test
    public void acosOf05() {
        when(mockCosine.distance(EMPTY, 0, EMPTY, 0, 0))
            .thenReturn((float) Math.sqrt(.5f));
        assertEquals(.25f, d.distance(EMPTY, 0, EMPTY, 0, 0), ERROR);
    }

	/** acos(0)/PI = .5 */
    @Test
    public void acosOf0() {
        when(mockCosine.distance(EMPTY, 0, EMPTY, 0, 0)).thenReturn(0f);
        assertEquals(.5f, d.distance(EMPTY, 0, EMPTY, 0, 0), ERROR);
    }

	/** acos(-sqrt(.5))/PI = .75 */
    @Test
    public void acosOfM05() {
        when(mockCosine.distance(EMPTY, 0, EMPTY, 0, 0))
            .thenReturn((float) -Math.sqrt(.5f));
        assertEquals(.75f, d.distance(EMPTY, 0, EMPTY, 0, 0), ERROR);
    }

	/** acos(-1)/PI = 1 */
    @Test
    public void acosOfM1() {
        when(mockCosine.distance(EMPTY, 0, EMPTY, 0, 0)).thenReturn(-1f);
        assertEquals(1f, d.distance(EMPTY, 0, EMPTY, 0, 0), ERROR);
    }
}

