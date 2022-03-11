package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static utils.Convertor.toLowerIndex;

public class IOUnit {
    private static Scanner reader = new Scanner(System.in);

    private static void handleError(String msg, boolean isInteractive) {
        System.out.println(msg);
        if (isInteractive) {
            System.out.println("Enter = повторить ввод, любая другая клавиша + Enter = выход:");
            if (reader.nextLine().isEmpty()) {
                return;
            }
        }
        System.out.println("Программа завершает работу.");
        System.exit(-1);
    }

    public static byte inputMode() {
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

    public static String inputFilename() {
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

    public static void openFile(String filename) {
        try {
            System.out.println("Пытаемся открыть файл \"" + filename + "\"...");
            reader = new Scanner(new FileInputStream(filename));
            System.out.println("Файл успешно открыт! Приступаем к вводу данных...");
        } catch (FileNotFoundException e) {
            handleError("Попытка открыть файл \"" + filename + "\" завершилась неудачей!", false);
        }
    }

    public static int inputN(boolean isInteractive) {
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

    public static void inputSystem(double[][] a, double[] b, boolean isInteractive) {
        int n = a.length;
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

    public static double inputEps(boolean isInteractive) {
        double eps;
        String tmp;
        while (true) {
            try {
                if (isInteractive) {
                    System.out.println("Введите eps (точность; действительное число больше 0 меньше 1 c точкой (.) в качестве разделителя):");
                    tmp = reader.nextLine();
                } else {
                    tmp = reader.next();
                }
                eps = Double.parseDouble(tmp);
                if (eps <= 0 || eps >= 1) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                handleError("Ошибка ввода eps! Требуется ввести действительное число, большее 0 и меньшее 1.", true);
            } catch (NoSuchElementException e) {
                handleError("Ошибка ввода eps! В файле кончились строки, исправьте файл.", false);
            }
        }
        return eps;
    }

    public static void printSystem(double[][] a, double[] b) {
        int n = a.length;
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
}
