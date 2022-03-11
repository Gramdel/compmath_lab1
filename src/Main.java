import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Main {
    private static Scanner reader = new Scanner(System.in);

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
        System.out.println(simpleIters(n, a, b, eps, x));
        exit();
    }

    private static void handleError(String msg, boolean isInteractive) {
        System.out.println(msg);
        if (isInteractive) {
            System.out.println("Enter = повторить ввод, любая другая клавиша + Enter = выход:");
            if (reader.nextLine().isEmpty()) {
                return;
            }
        }
        exit();
    }

    private static void exit() {
        System.out.println("Программа завершает работу.");
        System.exit(-1);
    }

    private static double inputEps() {
        double eps;
        while (true) {
            System.out.println("Введите eps (точность; действительное число >0 c точкой (.) в качестве разделителя):");
            String tmp = reader.nextLine();
            try {
                eps = Double.parseDouble(tmp);
                if (eps <= 0) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                handleError("Ошибка ввода eps! Требуется ввести действительное число, большее 0.", true);
            }
        }
        return eps;
    }

    private static byte inputMode() {
        byte mode;
        while (true) {
            System.out.println("Выберите дальнейший режим работы программы (0 - ручной ввод, 1 - ввод из файла, 2 - автоматическая генерация):");
            String tmp = reader.nextLine();
            try {
                mode = Byte.parseByte(tmp);
                if (mode < 0 || mode > 2) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                handleError("Ошибка ввода! Требуется ввести 0 для \"ручного ввода\", 1 - для \"ввода из файла\", 2 - для \"автоматической генерации\".", true);
            }
        }
        return mode;
    }

    private static String inputFilename() {
        while (true) {
            System.out.println("Введите имя файла, из которого следует выполнить ввод:");
            String tmp = reader.nextLine();
            if (!tmp.matches("\\s*")) {
                return tmp;
            } else {
                handleError("Ошибка ввода! Требуется ввести НЕ пустую строку.", true);
            }
        }
    }

    private static void openFile(String filename) {
        try {
            System.out.println("Пытаемся открыть файл \"" + filename + "\"...");
            reader = new Scanner(new FileInputStream(filename));
            System.out.println("Файл успешно открыт! Приступаем к вводу данных...");
        } catch (FileNotFoundException e) {
            handleError("Попытка открыть файл \"" + filename + "\" завершилась неудачей!", false);
        }
    }

    private static int inputN(boolean isInteractive) {
        int n;
        while (true) {
            if (isInteractive) {
                System.out.println("Введите n (натуральное число <=20):");
            }
            try {
                String tmp = reader.nextLine();
                n = Integer.parseInt(tmp);
                if (n < 1 || n > 20) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                handleError("Ошибка ввода n! Требуется ввести натуральное число от 1 до 20 (включительно).", isInteractive);
            } catch (NoSuchElementException e) {
                handleError("Ошибка ввода n! В файле кончились строки, исправьте файл.", false);
            }
        }
        return n;
    }

    private static void inputSystem(int n, double[][] a, double[] b, boolean isInteractive) {
        String tmp;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                while (true) {
                    try {
                        if (isInteractive) {
                            System.out.println("Введите a" + toLowerIndex(i + 1) + toLowerIndex(j + 1) + " (действительное число с точкой в качестве разделителя):");
                            tmp = reader.nextLine();
                        } else {
                            tmp = reader.next();
                        }
                        a[i][j] = Double.parseDouble(tmp);
                        break;
                    } catch (NumberFormatException e) {
                        handleError("Ошибка ввода a" + toLowerIndex(i + 1) + toLowerIndex(j + 1) + "! Требуется ввести действительное число с точкой (.) в качестве разделителя.", isInteractive);
                    } catch (NoSuchElementException e) {
                        handleError("Ошибка ввода a" + toLowerIndex(i + 1) + toLowerIndex(j + 1) + "! В файле кончились строки, исправьте файл.", false);
                    }
                }
            }
            while (true) {
                try {
                    if (isInteractive) {
                        System.out.println("Введите b" + toLowerIndex(i + 1) + " (действительное число с точкой в качестве разделителя):");
                        tmp = reader.nextLine();
                    } else {
                        tmp = reader.next();
                    }
                    b[i] = Double.parseDouble(tmp);
                    break;
                } catch (NumberFormatException e) {
                    handleError("Ошибка ввода b" + toLowerIndex(i + 1) + "! Требуется ввести действительное число с точкой (.) в качестве разделителя.", isInteractive);
                } catch (NoSuchElementException e) {
                    handleError("Ошибка ввода b" + toLowerIndex(i + 1) + "! В файле кончились строки, исправьте файл.", false);
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
