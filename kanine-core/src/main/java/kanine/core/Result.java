package kanine.core;

public class Result {
	public final int index;
	public final float distance;

    public Result(int index, float distance) {
        this.index = index;
        this.distance = distance;
    }

	@Override
	public String toString() {
		return String.format("%s(%d, %f)",
                getClass().getName(), index, distance);
	}
}
