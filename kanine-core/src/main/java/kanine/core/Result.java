package kanine.core;

public class Result {
	public int index;
	public float distance;

	@Override
	public String toString() {
		return String.format("(%d, %f)", index, distance);
	}
}
