package math;

import static utils.Convertor.toLowerIndex;

public class ResultBean {
    private final double[] x;
    private final double[] delta;
    private final int count;

    public ResultBean(double[] x, double[] delta, int count) {
        this.x = x;
        this.delta = delta;
        this.count = count;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Количество итераций: " + count);
        for (int i = 0; i < x.length; i++) {
            s.append("\nx").append(toLowerIndex(i + 1)).append(" = ").append(x[i]).append(";\tdelta").append(toLowerIndex(i + 1)).append(" = ").append(delta[i]);
        }
        return s.toString();
    }
}
