package math;

public class Generator {
    public static void generate(double[][] a, double[] b) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    a[i][j] = (Math.random() * (999 - 10 * (n - 1) - 1) + 10 * (n - 1) + 1) * (Math.random() > 0.5 ? 1 : -1);
                } else {
                    a[i][j] = Math.random() * (10 - (-10)) + (-10);
                }
            }
            b[i] = Math.random() * (10 - (-10)) + (-10);
        }
    }
}
