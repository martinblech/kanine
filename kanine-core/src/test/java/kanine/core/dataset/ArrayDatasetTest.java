package kanine.core.dataset;

import java.nio.FloatBuffer;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import org.mockito.InOrder;
import org.mockito.stubbing.OngoingStubbing;
import static org.mockito.Mockito.*;

import kanine.core.accumulator.BestResultsAccumulator;
import kanine.core.scorer.Scorer;

public class ArrayDatasetTest {

    @Test
    public void applyArrayAllElements() {
        final float[] data = new float[0];
        final int vectorLength = 10;
        final int size = 5;
        final ArrayDataset dataset = new ArrayDataset(data, vectorLength, size);
        final Scorer scorer = mock(Scorer.class);
        final BestResultsAccumulator accum = mock(BestResultsAccumulator.class);
        OngoingStubbing<Float> scoreStub =
            when(scorer.inverseScore(any(float[].class), anyInt()));
        for (int i = 0; i < size; i++) {
            scoreStub = scoreStub.thenReturn((float) i);
        }
        scoreStub.thenThrow(new AssertionError("no more elements"));
        dataset.apply(scorer, accum);
        final InOrder inOrder = inOrder(scorer, accum);
        for (int i = 0; i < size; i++) {
            inOrder.verify(scorer).inverseScore(data, i * vectorLength);
            inOrder.verify(accum).accumulate(i, (float) i);
        }
    }

}
