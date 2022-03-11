package math;

import java.util.Locale;

import static utils.Convertor.calcPrecision;
import static utils.Convertor.toLowerIndex;

public class ResultBean {
    private final double[] x;
    private final double[] delta;
    private final int count;
    private final long timeInNanos;
    private final double eps;

    public ResultBean(double[] x, double[] delta, int count, long timeInNanos, double eps) {
        this.x = x;
        this.delta = delta;
        this.count = count;
        this.timeInNanos = timeInNanos;
        this.eps = eps;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Время работы метода: " + (double) timeInNanos / 1000000);
        s.append("мс\nКоличество итераций: ").append(count);
        for (int i = 0; i < x.length; i++) {
            s.append("\nx").append(toLowerIndex(i + 1)).append("\t= ").append(String.format(Locale.ENGLISH, "%." + calcPrecision(eps) + "f", x[i])).append("\t\tdelta").append(toLowerIndex(i + 1)).append("\t= ").append(delta[i]);
        }
        return s.toString();
    }
}
