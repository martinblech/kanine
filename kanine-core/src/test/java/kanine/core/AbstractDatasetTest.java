package kanine.core;

import java.util.Arrays;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.mockito.InOrder;
import org.mockito.stubbing.OngoingStubbing;
import static org.mockito.Mockito.*;

import kanine.core.ArrayDataset;
import kanine.core.BestResultsAccumulator;
import kanine.core.Scorer;

public abstract class AbstractDatasetTest<T> {

    protected abstract T getData(float[] data);
    protected abstract OngoingStubbing<Float> getScoreStub(Scorer scorer);
    protected abstract Dataset getDataset(T data, int vectorLength);
    protected abstract void verifyScore(
            InOrder inOrder, Scorer scorer, T data, int offset);

    @Test public void apply() {
        final int vectorLength = 10;
        final int size = 5;
        final T data = getData(new float[vectorLength * size]);
        final Dataset dataset = getDataset(data, vectorLength);
        final Scorer scorer = mock(Scorer.class);
        final BestResultsAccumulator accum = mock(BestResultsAccumulator.class);
        OngoingStubbing<Float> scoreStub = getScoreStub(scorer);
        for (int i = 0; i < size; i++) {
            scoreStub = scoreStub.thenReturn((float) i);
        }
        scoreStub.thenThrow(new AssertionError("no more elements"));
        dataset.apply(scorer, accum);
        final InOrder inOrder = inOrder(scorer, accum);
        for (int i = 0; i < size; i++) {
            verifyScore(inOrder, scorer, data, i * vectorLength);
            inOrder.verify(accum).accumulate(i, (float) i);
        }
    }

    @Test public void getVector() {
        final T data = getData(new float[] {1f, 2f, 3f, 4f});
        final int vectorLength = 2;
        final int size = 2;
        final Dataset dataset = getDataset(data, vectorLength);
        float[] valueBuffer = new float[4];
        Arrays.fill(valueBuffer, -1f);
        dataset.get(0, valueBuffer, 0);
        assertArrayEquals(new float[] {1f, 2f, -1f, -1f}, valueBuffer, 0);
        Arrays.fill(valueBuffer, -1f);
        dataset.get(1, valueBuffer, 2);
        assertArrayEquals(new float[] {-1f, -1f, 3f, 4f}, valueBuffer, 0);
    }

}
