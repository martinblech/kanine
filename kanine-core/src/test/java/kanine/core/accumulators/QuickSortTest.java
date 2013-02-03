package kanine.core.accumulators;

import java.util.Random;

import kanine.core.accumulators.QuickSort;

import org.testng.annotations.*;
import static org.testng.AssertJUnit.*;

public class QuickSortTest {

	@Test
	public void test() {
		float[] a = new float[1000];
		float[] acopy = new float[1000];
		int[] indexes = new int[1000];
		Random random = new Random();
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
			assertEquals(a[i], acopy[indexes[i]]);
		}
	}

}
