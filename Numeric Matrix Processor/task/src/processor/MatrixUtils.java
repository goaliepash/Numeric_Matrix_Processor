package processor;

import java.util.Locale;
import java.util.Scanner;

/**
 * Полезная утилита для работы с матрицами.
 * Здесь в основном находится ввод/вывод матриц.
 *
 * @author Иванов Павел Александрович
 */
public class MatrixUtils {

    /**
     * Метод для печати меню матричного процессора
     */
    public static void printMenu() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate a determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");
    }

    /**
     * Метод для печати вариантов транспонирования матрицы
     */
    public static void printTransposeTypes() {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
    }

    /**
     * Метод для ввода матрицы с клавиатуры
     *
     * @return матрица (двумерный массив)
     */
    public static double[][] inputMatrix() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter size of matrix: ");
        String[] dimension = scanner.nextLine().split(" ");
        int nA = Integer.parseInt(dimension[0]);
        int mA = Integer.parseInt(dimension[1]);

        System.out.println("Enter matrix:");
        double[][] a = new double[nA][mA];
        for (int i = 0; i < a.length; i++) {
            String[] row = scanner.nextLine().split(" ");
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = Double.parseDouble(row[j]);
            }
        }
        return a;
    }

    /**
     * Метод для печати матрицы
     *
     * @param a матрица для печати (двумерный массив)
     */
    public static void printMatrix(double[][] a) {
        System.out.println("The result is:");
        if (a == null) {
            System.out.println("ERROR");
        } else {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[i].length; j++) {
                    System.out.printf(Locale.US, "%.2f ", a[i][j]);
                }
                System.out.println();
            }
        }
    }
}
