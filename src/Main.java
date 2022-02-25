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
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                if (!handleTypeErr("Требуется ввести натуральное число от 1 до 20 (включительно).")) {
                    System.out.println("Программа завершает работу.");
                    System.exit(-1);
                }
            }
        }
    }

    private static boolean handleTypeErr(String msg) {
        System.out.println("Некорректный ввод!\n" + msg);
        System.out.print("Enter - повторить ввод, любая другая клавиша - выход: ");
        String check = reader.nextLine();
        return check.isEmpty();
    }
}
