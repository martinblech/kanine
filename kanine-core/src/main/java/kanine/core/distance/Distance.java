/**
 * 
 */
package kanine.core.distance;

/**
 * @author mblech
 * 
 */
public abstract class Distance {
	public abstract float distance(float[] a, int aOffset, float[] b,
			int bOffset, int length);

	@Override
	public String toString() {
		return String.format("<%s>", getClass().getSimpleName());
	}

}
