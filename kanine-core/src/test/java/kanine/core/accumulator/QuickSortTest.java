package kanine.core.accumulator;

import java.util.Random;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuickSortTest {

    private static final float DELTA = 0f;

	@Test
	public void quickSort() {
		float[] a = new float[1000];
		float[] acopy = new float[1000];
		int[] indexes = new int[1000];
		Random random = new Random(0);
		for (int i = 0; i < a.length; i++) {
			float f = random.nextFloat();
			a[i] = f;
			acopy[i] = f;
			indexes[i] = i;
		}
		QuickSort.sort(a, indexes);
		for (int i = 0; i < a.length; i++) {
			if (i > 0) {
				assertTrue(a[i] >= a[i - 1]);
			}
			assertEquals(a[i], acopy[indexes[i]], DELTA);
		}
	}

}
