package kanine.core;

import kanine.core.ArrayDataset;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import org.mockito.InOrder;
import org.mockito.stubbing.OngoingStubbing;
import static org.mockito.Mockito.*;

import kanine.core.BestResultsAccumulator;
import kanine.core.Scorer;

public abstract class AbstractDatasetTest<T> {

    protected abstract T getData(int size);
    protected abstract OngoingStubbing<Float> getScoreStub(Scorer scorer);
    protected abstract Dataset getDataset(T data, int vectorLength);
    protected abstract void verifyScore(
            InOrder inOrder, Scorer scorer, T data, int offset);

    @Test
    public void apply() {
        final int vectorLength = 10;
        final int size = 5;
        final T data = getData(vectorLength * size);
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

}
