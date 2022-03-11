import static math.Dominance.transform;
import static math.Generator.generate;
import static math.SimpleIters.run;
import static utils.IOUnit.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Вас приветствует программа для демонстрации метода простых итераций!");
        int n;
        double[][] a;
        double[] b;
        double eps;
        byte mode = inputMode();

        if (mode == 2) {
            n = inputN(true);
            a = new double[n][n];
            b = new double[n];
            generate(a, b);

            System.out.println("Сгенерирована следующая система с диагональным преобладанием:");
            printSystem(a, b);
            eps = inputEps(true);
        } else {
            if (mode == 1) {
                openFile(inputFilename());
            }
            boolean isInteractive = mode == 0;
            n = inputN(isInteractive);
            a = new double[n][n];
            b = new double[n];
            inputSystem(a, b, isInteractive);

            System.out.println("Введена следующая система:");
            printSystem(a, b);
            System.out.println("Пытаемся получить диагональное преобладание, если его нет...");
            if (!transform(a, b)) {
                System.out.println("Метод не применим, так как в системе невозможно получить диагональное преобладание!");
                System.out.println("Программа завершает работу.");
                System.exit(-2);
            }
            eps = inputEps(isInteractive);
        }

        double[] x = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = b[i] / a[i][i];
        }

        System.out.println("Приступаем к поиску неизвестных при eps = "+eps+"...");
        System.out.println(run(a, b, eps, x));
        System.out.println("Программа завершает работу.");
    }
}
