import java.util.Scanner;

public class Main {
    private static final Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {
        int n;
        while (true) {
            System.out.print("Введите число неизвестных в системе (натуральное число <=20): ");
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
                    System.exit(-1);
                }
            }
        }

        double[][] a = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                while (true) {
                    System.out.println("Введите значение элемента a_" + (i+1) + "_" + (j+1) + " матрицы коэффициентов\n(действительное число с точкой в качестве разделителя):");
                    String tmp = reader.nextLine();
                    try {
                        a[i][j] = Double.parseDouble(tmp);
                        break;
                    } catch (NumberFormatException e) {
                        if (!fixError("Требуется ввести действительное число с точкой (.) в качестве разделителя")) {
                            System.out.println("Программа завершает работу.");
                            System.exit(-1);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean fixError(String msg) {
        System.out.println("Некорректный ввод!\n" + msg);
        System.out.print("Enter = повторить ввод, любая другая клавиша + Enter = выход: ");
        String check = reader.nextLine();
        return check.isEmpty();
    }
}
