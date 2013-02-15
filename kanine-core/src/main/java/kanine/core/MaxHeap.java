package kanine.core;

import java.util.Arrays;

/**
 * Adapted from http://www.algolist.net/Data_structures/Binary_heap
 */
final class MaxHeap {
	private final float[] data;
	private final int[] index;
	private int heapSize;

	public MaxHeap(int size) {
		data = new float[size];
		index = new int[size];
		heapSize = 0;
	}

	public float getMax() {
		if (isEmpty()) {
			throw new IllegalStateException("Heap is empty");
		} else {
			return data[0];
		}
	}

	public int getMaxIndex() {
		if (isEmpty()) {
			throw new IllegalStateException("Heap is empty");
		} else {
			return index[0];
		}
	}

	public boolean isEmpty() {
		return (heapSize == 0);
	}

	public int size() {
		return heapSize;
	}

	private static int getLeftChildIndex(int nodeIndex) {
		return 2 * nodeIndex + 1;
	}

	private static int getRightChildIndex(int nodeIndex) {
		return 2 * nodeIndex + 2;
	}

	private static int getParentIndex(int nodeIndex) {
		return (nodeIndex - 1) / 2;
	}

	public void insert(int i, float value) {
		if (isFull()) {
			throw new IllegalStateException("heap overflow");
		} else {
			heapSize++;
			data[heapSize - 1] = value;
			index[heapSize - 1] = i;
			siftUp(heapSize - 1);
		}
	}

	public boolean isFull() {
		return heapSize == data.length;
	}

	private void siftUp(int nodeIndex) {
		int parentIndex;
		float tmp;
		int tmpi;
		if (nodeIndex != 0) {
			parentIndex = getParentIndex(nodeIndex);
			if (cmp(data[parentIndex], data[nodeIndex])) {
				tmp = data[parentIndex];
				tmpi = index[parentIndex];
				data[parentIndex] = data[nodeIndex];
				index[parentIndex] = index[nodeIndex];
				data[nodeIndex] = tmp;
				index[nodeIndex] = tmpi;
				siftUp(parentIndex);
			}
		}
	}

	public void removeMax() {
		if (isEmpty()) {
			throw new IllegalStateException("Heap is empty");
		} else {
			data[0] = data[heapSize - 1];
			index[0] = index[heapSize - 1];
			heapSize--;
			if (heapSize > 0) {
				siftDown(0);
			}
		}
	}

	public void replaceMax(int i, float value) {
		data[0] = value;
		index[0] = i;
		siftDown(0);
	}

	private void siftDown(int nodeIndex) {
		int leftChildIndex, rightChildIndex, minIndex;
		float tmp;
		int tmpi;
		leftChildIndex = getLeftChildIndex(nodeIndex);
		rightChildIndex = getRightChildIndex(nodeIndex);
		if (rightChildIndex >= heapSize) {
			if (leftChildIndex >= heapSize) {
				return;
			} else {
				minIndex = leftChildIndex;
			}
		} else {
			if (!cmp(data[leftChildIndex], data[rightChildIndex])) {
				minIndex = leftChildIndex;
			} else {
				minIndex = rightChildIndex;
			}
		}
		if (cmp(data[nodeIndex], data[minIndex])) {
			tmp = data[minIndex];
			tmpi = index[minIndex];
			data[minIndex] = data[nodeIndex];
			index[minIndex] = index[nodeIndex];
			data[nodeIndex] = tmp;
			index[nodeIndex] = tmpi;
			siftDown(minIndex);
		}
	}

	private boolean cmp(float a, float b) {
		return a < b;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + Arrays.toString(data);
	}
}
