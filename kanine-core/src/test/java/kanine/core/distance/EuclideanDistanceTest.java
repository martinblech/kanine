package kanine.core.distance;

import org.testng.annotations.*;
import static org.testng.AssertJUnit.*;

public class EuclideanDistanceTest extends AbstractDistanceTest {

    @Override
    protected Distance getDistance() {
        return d;
    }

    private Distance d = new SqrtDistance(new SquaredEuclideanDistance());

    /** euc([1,1], [1,1]) = 0 */
    @Test
    public void euc1111() {
		assertEquals(0f, d.distance(new float[] { 1f, 1f }, 0,
				new float[] { 1f, 1f }, 0, 2), ERROR);
    }

    /** euc([1,0], [0,1]) = sqrt(2) */
    @Test
    public void euc1001() {
		assertEquals(Math.sqrt(2), d.distance(new float[] { 0f, 1f }, 0,
				new float[] { 1f, 0f }, 0, 2), ERROR);
    }

    /** euc([1,0], [1,1]) = 1 */
	@Test
	public void euc1011() {
		assertEquals(1f, d.distance(new float[] { 0f, 1f }, 0,
				new float[] { 1f, 1f }, 0, 2), ERROR);
	}


}
