package math;

import static java.lang.Math.abs;

public class SimpleIters {
    public static ResultBean run(int n, double[][] a, double[] b, double eps, double[] x) {
        double[] prevX = new double[n];
        double[] delta = new double[n];
        double maxDelta;
        int count = 0;

        do {
            count++;
            maxDelta = -1;
            System.arraycopy(x, 0, prevX, 0, n);
            for (int i = 0; i < n; i++) {
                x[i] = b[i] / a[i][i];
                for (int k = 0; k < n; k++) {
                    if (i != k) {
                        x[i] -= prevX[k] * a[i][k] / a[i][i];
                    }
                }

                delta[i] = abs(x[i] - prevX[i]);
                if (delta[i] > maxDelta) {
                    maxDelta = delta[i];
                }
            }
        } while (maxDelta > eps);

        return new ResultBean(x, delta, count);
    }
}
