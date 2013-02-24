package kanine.core;

final class MaxHeap {
    private final float[] data;
    private final int[] index;
    private int heapSize;

    MaxHeap(final int size) {
        data = new float[size];
        index = new int[size];
        heapSize = 0;
    }

    float getMax() {
        assert !isEmpty();
        return data[0];
    }

    int getMaxIndex() {
        assert !isEmpty();
        return index[0];
    }

    boolean isEmpty() {
        return heapSize == 0;
    }

    int size() {
        return heapSize;
    }

    private static int getLeftChildIndex(final int nodeIndex) {
        return 2 * nodeIndex + 1;
    }

    private static int getRightChildIndex(final int nodeIndex) {
        return 2 * nodeIndex + 2;
    }

    private static int getParentIndex(final int nodeIndex) {
        return (nodeIndex - 1) / 2;
    }

    void insert(final int i, final float value) {
        assert !isFull();
        heapSize++;
        data[heapSize - 1] = value;
        index[heapSize - 1] = i;
        siftUp(heapSize - 1);
    }

    boolean isFull() {
        return heapSize == data.length;
    }

    private void siftUp(final int nodeIndex) {
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

    void removeMax() {
        assert !isEmpty();
        data[0] = data[heapSize - 1];
        index[0] = index[heapSize - 1];
        heapSize--;
        if (heapSize > 0) {
            siftDown(0);
        }
    }

    void replaceMax(final int i, final float value) {
        data[0] = value;
        index[0] = i;
        siftDown(0);
    }

    private void siftDown(final int nodeIndex) {
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

    private boolean cmp(final float a, final float b) {
        return a < b;
    }
}
