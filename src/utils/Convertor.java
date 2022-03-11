package utils;

public class Convertor {
    public static String toLowerIndex(int n) {
        StringBuilder result = new StringBuilder();
        while (n != 0) {
            result.insert(0, (char) (0x2080 + n % 10)); // 0x2080 - unicode номер 0 в нижнем индексе
            n /= 10;
        }
        return result.toString();
    }
}
