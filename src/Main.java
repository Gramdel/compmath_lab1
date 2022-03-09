import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Main {
    private static final Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {
        int n;
        while (true) {
            System.out.println("Введите число неизвестных в системе (натуральное число <=20): ");
            String tmp = reader.nextLine();
            try {
                n = Integer.parseInt(tmp);
                if (n < 1 || n > 20) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                if (!fixError("Требуется ввести натуральное число от 1 до 20 (включительно).")) {
                    System.out.println("Программа завершает работу.");
                    System.exit(0);
                }
            }
        }

        double[][] a = new double[n][n];
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                while (true) {
                    System.out.println("Введите значение элемента a" + toLowerIndex(i + 1) + (char) 0x202F + toLowerIndex(j + 1) + " матрицы коэффициентов\n(действительное число с точкой в качестве разделителя):");
                    String tmp = reader.nextLine();
                    try {
                        a[i][j] = Double.parseDouble(tmp);
                        break;
                    } catch (NumberFormatException e) {
                        if (!fixError("Требуется ввести действительное число с точкой (.) в качестве разделителя")) {
                            System.out.println("Программа завершает работу.");
                            System.exit(0);
                        }
                    }
                }
            }
            while (true) {
                System.out.println("Введите значение элемента b" + toLowerIndex(i + 1) + " столбца свободных членов\n(действительное число с точкой в качестве разделителя):");
                String tmp = reader.nextLine();
                try {
                    b[i] = Double.parseDouble(tmp);
                    break;
                } catch (NumberFormatException e) {
                    if (!fixError("Требуется ввести действительное число с точкой (.) в качестве разделителя")) {
                        System.out.println("Программа завершает работу.");
                        System.exit(-1);
                    }
                }
            }
        }

        System.out.println("Введена следующая система:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j != 0) {
                    System.out.print(" + ");
                }
                System.out.print("(" + a[i][j] + ")·x" + toLowerIndex(j + 1));
            }
            System.out.println(" = " + b[i]);
        }

        double[] x = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = b[i] / a[i][i];
        }

        System.out.println(simpleIters(n, a, b, 0.0001, x));
        System.out.println(Arrays.toString(x));
    }

    private static boolean fixError(String msg) {
        System.out.println("Некорректный ввод!\n" + msg);
        System.out.println("Enter = повторить ввод, любая другая клавиша + Enter = выход: ");
        String check = reader.nextLine();
        return check.isEmpty();
    }

    private static String toLowerIndex(int n) {
        StringBuilder result = new StringBuilder();
        while (n != 0) {
            result.insert(0, (char) (0x2080 + n % 10)); // 0x2080 - unicode номер 0 в нижнем индексе
            n /= 10;
        }
        return result.toString();
    }

    private static int simpleIters(int n, double[][] a, double[] b, double eps, double[] x) {
        double[] prevX = new double[n];
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

                double delta = abs(x[i] - prevX[i]);
                if (delta > maxDelta) {
                    maxDelta = delta;
                }
            }
        } while (maxDelta > eps);

        return count;
    }
}
