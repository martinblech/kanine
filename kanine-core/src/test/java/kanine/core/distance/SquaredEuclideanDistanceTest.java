package kanine.core.distance;

import org.testng.annotations.*;
import static org.testng.AssertJUnit.*;

public class SquaredEuclideanDistanceTest extends AbstractDistanceTest {

    @Override
    protected Distance getDistance() {
        return d;
    }

    private SquaredEuclideanDistance d = new SquaredEuclideanDistance();

    /** sqeuc([1,1], [1,1]) = 0 */
    @Test
    public void sqeuc1111() {
		assertEquals(0f, d.distance(new float[] { 1f, 1f }, 0,
				new float[] { 1f, 1f }, 0, 2), ERROR);
    }

    /** euc([1,0], [0,1]) = 2 */
    @Test
    public void sqeuc1001() {
		assertEquals(2, d.distance(new float[] { 0f, 1f }, 0,
				new float[] { 1f, 0f }, 0, 2), ERROR);
    }

    /** euc([1,0], [1,1]) = 1 */
	@Test
	public void sqeuc1011() {
		assertEquals(1f, d.distance(new float[] { 0f, 1f }, 0,
				new float[] { 1f, 1f }, 0, 2), ERROR);
	}
}

