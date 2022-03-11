import static utils.IOUnit.*;
import static math.SimpleIters.run;

public class Main {
    public static void main(String[] args) {
        System.out.println("Вас приветствует программа для демонстрации метода простых итераций!");
        int n;
        double[][] a;
        double[] b;
        double eps = inputEps();
        byte mode = inputMode();

        if (mode == 2) {
            n = inputN(true);
            a = new double[n][n];
            b = new double[n];

            // generation
        } else {
            if (mode == 1) {
                openFile(inputFilename());
            }
            boolean isInteractive = mode == 0;
            n = inputN(isInteractive);
            a = new double[n][n];
            b = new double[n];
            inputSystem(n, a, b, isInteractive);
            printSystem(n, a, b);
        }

        double[] x = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = b[i] / a[i][i];
        }
        System.out.println(run(n, a, b, eps, x));
        System.out.println("Программа завершает работу.");
    }
}
