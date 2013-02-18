package kanine.core;

import java.nio.FloatBuffer;

import kanine.core.BufferDataset;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import org.mockito.InOrder;
import org.mockito.stubbing.OngoingStubbing;
import static org.mockito.Mockito.*;

import kanine.core.BestResultsAccumulator;
import kanine.core.Scorer;

public class BufferDatasetTest extends AbstractDatasetTest<FloatBuffer> {

    protected FloatBuffer getData(float[] data) {
        return FloatBuffer.wrap(data);
    }

    protected OngoingStubbing<Float> getScoreStub(Scorer scorer) {
        return when(scorer.inverseScore(any(FloatBuffer.class), anyInt()));
    }

    protected Dataset getDataset(FloatBuffer data, int vectorLength) {
        return new BufferDataset(data, vectorLength);
    }

    protected void verifyScore(
            InOrder inOrder, Scorer scorer, FloatBuffer data, int offset) {
        inOrder.verify(scorer).inverseScore(data, offset);
    }

}
