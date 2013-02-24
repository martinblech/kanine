package kanine.core;

/**
 * An {@code (index, inverseScore)} pair.
 */
public final class Result {

    private final int index;
    private final float inverseScore;

    /**
     * Create a {@link Result} with the given {@code index} and
     * {@code inverseScore}.
     *
     * @param index the index
     * @param inverseScore the inverse score
     */
    public Result(final int index, final float inverseScore) {
        this.index = index;
        this.inverseScore = inverseScore;
    }

    /** Get the {@code index}. */
    public int getIndex() { return index; }

    /** Get the {@code inverseScore}. */
    public float getInverseScore() { return inverseScore; }

    @Override public String toString() {
        return String.format("%s(%d, %s)",
                getClass().getSimpleName(), getIndex(), getInverseScore());
    }
}
