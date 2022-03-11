package math;

import java.util.HashMap;

import static java.lang.Math.abs;
import static utils.IOUnit.printSystem;

public class Dominance {
    public static boolean transform(double[][] a, double[] b) {
        int n = a.length;
        boolean isDominant = true;
        HashMap<Integer, Integer> indexMap = new HashMap<>(); // храним для каждого индекса подходящего элемента стрчоку, в которой он

        for (int i = 0; i < n; i++) {
            double sumOfRow = 0;
            for (int j = 0; j < n; j++) {
                sumOfRow += abs(a[i][j]);
            }
            for (int j = 0; j < n; j++) {
                if (abs(a[i][j]) > sumOfRow - abs(a[i][j])) {
                    if (indexMap.get(j) != null) {
                        return false;
                    }
                    if (isDominant && i != j) {
                        isDominant = false;
                    }
                    indexMap.put(j, i);
                }
            }
        }

        if (indexMap.size() < n) {
            return false;
        }

        if (!isDominant) {
            double[][] tmpA = new double[n][n];
            double[] tmpB = new double[n];
            for (int i = 0; i < n; i++) {
                tmpA[i] = a[indexMap.get(i)];
                tmpB[i] = b[indexMap.get(i)];
            }
            System.arraycopy(tmpA, 0, a, 0, n);
            System.arraycopy(tmpB, 0, b, 0, n);
            System.out.println("Система после перестановки строк:");
            printSystem(a, b);
        } else {
            System.out.println("Переставлять строки не требуется.");
        }
        return true;
    }
}
