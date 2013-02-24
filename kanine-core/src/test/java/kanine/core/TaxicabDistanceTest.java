package kanine.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class TaxicabDistanceTest extends AbstractDistanceTest {

    @Override
    protected Distance getDistance() {
        return d;
    }

    private TaxicabDistance d = new TaxicabDistance();

    /** taxicab([1,1], [1,1]) = 0 */
    @Test
    public void taxicab1111() {
		assertEquals(0f, d.distance(new float[] { 1f, 1f }, 0,
				new float[] { 1f, 1f }, 0, 2), ERROR);
    }

    /** taxicab([1,0], [0,1]) = 2 */
    @Test
    public void taxicab1001() {
		assertEquals(2, d.distance(new float[] { 0f, 1f }, 0,
				new float[] { 1f, 0f }, 0, 2), ERROR);
    }

    /** taxicab([1,0], [1,1]) = 1 */
	@Test
	public void taxicab1011() {
		assertEquals(1f, d.distance(new float[] { 0f, 1f }, 0,
				new float[] { 1f, 1f }, 0, 2), ERROR);
	}

    @Test public void string() {
        assertEquals(d.toString(), "TaxicabDistance");
    }
}

