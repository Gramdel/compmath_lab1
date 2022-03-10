import java.util.Scanner;

import static java.lang.Math.abs;

public class Main {
    private static final Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Вас приветствует программа для демонстрации метода простых итераций!");
        byte mode = chooseMode();

        boolean isInteractive = false;
        int n = inputN(isInteractive);
        double[][] a = new double[n][n];
        double[] b = new double[n];
        inputSystem(isInteractive, n, a, b);
        printSystem(n, a, b);

        double eps = inputEps(isInteractive);
        double[] x = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = b[i] / a[i][i];
        }
        System.out.println(simpleIters(n, a, b, eps, x));
    }

    private static boolean handleError(String msg, boolean isInteractive) {
        System.out.println("Некорректный ввод!\n" + msg);
        if (isInteractive) {
            System.out.println("Enter = повторить ввод, любая другая клавиша + Enter = выход: ");
            String check = reader.nextLine();
            return check.isEmpty();
        }
        return false;
    }

    private static void exit() {
        System.out.println("Программа завершает работу.");
        System.exit(-1);
    }

    private static byte chooseMode() {
        byte mode;
        while (true) {
            System.out.println("Выберите режим работы программы (0 - ручной ввод, 1 - ввод из файла, 2 - автоматическая генерация):");
            String tmp = reader.nextLine();
            try {
                mode = Byte.parseByte(tmp);
                if (mode < 0 || mode > 2) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                if (!handleError("Требуется ввести 0 для \"ручного ввода\", 1 - для \"ввода из файла\", 2 - для \"автоматической генерации\"", true)) {
                    exit();
                }
            }
        }
        return mode;
    }

    private static int inputN(boolean isInteractive) {
        int n;
        while (true) {
            if (isInteractive) {
                System.out.println("Введите число неизвестных в системе (натуральное число <=20): ");
            }

            String tmp = reader.nextLine();
            try {
                n = Integer.parseInt(tmp);
                if (n < 1 || n > 20) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                if (!handleError("Требуется ввести натуральное число от 1 до 20 (включительно).", isInteractive)) {
                    exit();
                }
            }
        }
        return n;
    }

    private static double inputEps(boolean isInteractive) {
        double eps;
        while (true) {
            if (isInteractive) {
                System.out.println("Введите точность (действительное число >0): ");
            }

            String tmp = reader.nextLine();
            try {
                eps = Double.parseDouble(tmp);
                if (eps <= 0) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                if (!handleError("Требуется ввести действительное число, большее 0.", isInteractive)) {
                    exit();
                }
            }
        }
        return eps;
    }

    private static void inputSystem(boolean isInteractive, int n, double[][] a, double[] b) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                while (true) {
                    if (isInteractive) {
                        System.out.println("Введите значение элемента a" + toLowerIndex(i + 1) + toLowerIndex(j + 1) + " матрицы коэффициентов\n(действительное число с точкой в качестве разделителя):");
                    }

                    String tmp = reader.nextLine();
                    try {
                        a[i][j] = Double.parseDouble(tmp);
                        break;
                    } catch (NumberFormatException e) {
                        if (!handleError("Требуется ввести действительное число с точкой (.) в качестве разделителя", isInteractive)) {
                            exit();
                        }
                    }
                }
            }
            while (true) {
                if (isInteractive) {
                    System.out.println("Введите значение элемента b" + toLowerIndex(i + 1) + " столбца свободных членов\n(действительное число с точкой в качестве разделителя):");
                }

                String tmp = reader.nextLine();
                try {
                    b[i] = Double.parseDouble(tmp);
                    break;
                } catch (NumberFormatException e) {
                    if (!handleError("Требуется ввести действительное число с точкой (.) в качестве разделителя", isInteractive)) {
                        exit();
                    }
                }
            }
        }
    }

    private static String toLowerIndex(int n) {
        StringBuilder result = new StringBuilder();
        while (n != 0) {
            result.insert(0, (char) (0x2080 + n % 10)); // 0x2080 - unicode номер 0 в нижнем индексе
            n /= 10;
        }
        return result.toString();
    }

    private static void printSystem(int n, double[][] a, double[] b) {
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
    }

    private static Result simpleIters(int n, double[][] a, double[] b, double eps, double[] x) {
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

        return new Result(x, delta, count);
    }
}
