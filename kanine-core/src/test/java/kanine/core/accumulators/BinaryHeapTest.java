package kanine.core.accumulators;

import kanine.core.accumulators.BinaryHeap;

import org.junit.Assert;
import org.junit.Test;


public class BinaryHeapTest {

	@Test
	public void leafs() {
		Assert.assertArrayEquals(new int[] { 0 }, BinaryHeap.leafs(1));
		Assert.assertArrayEquals(new int[] { 1 }, BinaryHeap.leafs(2));
		Assert.assertArrayEquals(new int[] { 2, 1 }, BinaryHeap.leafs(3));
	}
}
