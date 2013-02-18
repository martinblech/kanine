package kanine.core;

public final class Result {

	private final int index;
	private final float inverseScore;

    public Result(int index, float inverseScore) {
        this.index = index;
        this.inverseScore = inverseScore;
    }

    public int getIndex() { return index; }
    public float getInverseScore() { return inverseScore; }

	@Override public String toString() {
		return String.format("%s(%d, %f)",
                getClass().getName(), index, inverseScore);
	}
}
