package utils;

public class Convertor {
    public static String toLowerIndex(int num) {
        StringBuilder result = new StringBuilder();
        while (num != 0) {
            result.insert(0, (char) (0x2080 + num % 10)); // 0x2080 - unicode номер 0 в нижнем индексе
            num /= 10;
        }
        return result.toString();
    }
}
