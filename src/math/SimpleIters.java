package math;

public class SimpleIters {
    public static ResultBean run(double[][] a, double[] b, double eps, double[] x) {
        int n = a.length;
        double[] prevX = new double[n];
        double[] delta = new double[n];
        double maxDelta;
        int count = 0;

        long startTime = System.nanoTime();
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

                delta[i] = Math.abs(x[i] - prevX[i]);
                if (delta[i] > maxDelta) {
                    maxDelta = delta[i];
                }
            }
        } while (maxDelta > eps);
        long endTime = System.nanoTime();

        return new ResultBean(x, delta, count, endTime-startTime, eps);
    }
}
