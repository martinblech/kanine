package kanine.core;

public class Result {
	public final int index;
	public final float inverseScore;

    public Result(int index, float inverseScore) {
        this.index = index;
        this.inverseScore = inverseScore;
    }

	@Override
	public String toString() {
		return String.format("%s(%d, %f)",
                getClass().getName(), index, inverseScore);
	}
}
