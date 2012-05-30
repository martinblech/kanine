package kanine.core.accumulators;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Taken from http://www.algolist.net/Data_structures/Binary_heap
 */
class BinaryHeap {
	private float[] data;
	private int[] index;
	private int heapSize;
	private int[] leafs;

	public BinaryHeap(int size) {
		data = new float[size];
		index = new int[size];
		heapSize = 0;
		leafs = leafs(size);
	}

	public float getMinimum() {
		if (isEmpty())
			throw new IllegalStateException("Heap is empty");
		else
			return data[0];
	}

	public int getMinimumIndex() {
		if (isEmpty())
			throw new IllegalStateException("Heap is empty");
		else
			return index[0];
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
		if (heapSize == data.length) {
			// throw new IllegalStateException(
			// "Heap's underlying storage is overflow");
			for (int leaf : leafs) {
				if (data[leaf] > value) {
					data[leaf] = value;
					index[leaf] = i;
					siftUp(leaf);
					break;
				}
			}
		} else {
			heapSize++;
			data[heapSize - 1] = value;
			index[heapSize - 1] = i;
			siftUp(heapSize - 1);
		}
	}

	private void siftUp(int nodeIndex) {
		int parentIndex;
		float tmp;
		int tmpi;
		if (nodeIndex != 0) {
			parentIndex = getParentIndex(nodeIndex);
			if (data[parentIndex] > data[nodeIndex]) {
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

	public void removeMin() {
		if (isEmpty())
			throw new IllegalStateException("Heap is empty");
		else {
			data[0] = data[heapSize - 1];
			index[0] = index[heapSize - 1];
			heapSize--;
			if (heapSize > 0) {
				siftDown(0);
			}
		}
	}

	private void siftDown(int nodeIndex) {
		int leftChildIndex, rightChildIndex, minIndex;
		float tmp;
		int tmpi;
		leftChildIndex = getLeftChildIndex(nodeIndex);
		rightChildIndex = getRightChildIndex(nodeIndex);
		if (rightChildIndex >= heapSize) {
			if (leftChildIndex >= heapSize)
				return;
			else
				minIndex = leftChildIndex;
		} else {
			if (data[leftChildIndex] <= data[rightChildIndex])
				minIndex = leftChildIndex;
			else
				minIndex = rightChildIndex;
		}
		if (data[nodeIndex] > data[minIndex]) {
			tmp = data[minIndex];
			tmpi = index[minIndex];
			data[minIndex] = data[nodeIndex];
			index[minIndex] = index[nodeIndex];
			data[nodeIndex] = tmp;
			index[nodeIndex] = tmpi;
			siftDown(minIndex);
		}
	}

	static int[] leafs(int size) {
		LinkedList<Integer> leafs = new LinkedList<Integer>();
		Queue<Integer> nodes = new LinkedList<Integer>();
		nodes.offer(0);
		while (!nodes.isEmpty()) {
			int node = nodes.poll();
			int leftChild = getLeftChildIndex(node);
			if (leftChild >= size) {
				leafs.addFirst(node);
			} else {
				nodes.offer(leftChild);
				int rightChild = getRightChildIndex(node);
				if (rightChild < size) {
					nodes.offer(rightChild);
				}
			}
		}
		int[] leafsArray = new int[leafs.size()];
		for (int i = 0; i < leafsArray.length; i++) {
			leafsArray[i] = leafs.get(i);
		}
		return leafsArray;
	}
}
