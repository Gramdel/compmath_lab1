package math;

import static utils.Convertor.toLowerIndex;

public class ResultBean {
    private final double[] x;
    private final double[] delta;
    private final int count;
    private final long timeInNanos;

    public ResultBean(double[] x, double[] delta, int count, long timeInNanos) {
        this.x = x;
        this.delta = delta;
        this.count = count;
        this.timeInNanos = timeInNanos;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Время работы метода: " + (double) timeInNanos /1000000);
        s.append("мс\nКоличество итераций: ").append(count);
        for (int i = 0; i < x.length; i++) {
            s.append("\nx").append(toLowerIndex(i + 1)).append(" = ").append(x[i]).append(" delta").append(toLowerIndex(i + 1)).append(" = ").append(delta[i]);
        }
        return s.toString();
    }
}
