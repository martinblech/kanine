package kanine.core;

import kanine.core.ArrayDataset;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import org.mockito.InOrder;
import org.mockito.stubbing.OngoingStubbing;
import static org.mockito.Mockito.*;

import kanine.core.BestResultsAccumulator;
import kanine.core.Scorer;

public class ArrayDatasetTest extends AbstractDatasetTest<float[]> {

    protected float[] getData(float[] data) {
        return data;
    }

    protected OngoingStubbing<Float> getScoreStub(Scorer scorer) {
        return when(scorer.inverseScore(any(float[].class), anyInt()));
    }

    protected Dataset getDataset(float[] data, int vectorLength) {
        return new ArrayDataset(data, vectorLength);
    }

    protected void verifyScore(
            InOrder inOrder, Scorer scorer, float[] data, int offset) {
        inOrder.verify(scorer).inverseScore(data, offset);
    }

}
