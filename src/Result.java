import java.util.Arrays;

public class Result {
    private final double[] x;
    private final double[] delta;
    private final int count;

    public Result(double[] x, double[] delta, int count) {
        this.x = x;
        this.delta = delta;
        this.count = count;
    }

    @Override
    public String toString() {
        return Arrays.toString(x) + Arrays.toString(delta) + count;
    }
}
