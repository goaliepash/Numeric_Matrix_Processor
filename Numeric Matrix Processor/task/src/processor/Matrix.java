package processor;

import java.security.InvalidParameterException;
import java.util.Objects;

/**
 * Класс для операций с матрицами.
 *
 * @author Иванов Павел Александрович
 */
class Matrix {

    /**
     * Метод для сложения матриц.
     *
     * @param a матрица A
     * @param b матрица B
     * @return матрица С = A + B
     */
    public static double[][] addition(double[][] a, double[][] b) {
        if (!checkDimensionForAddition(a, b)) {
            return null;
        }
        double[][] c = new double[a.length][a[0].length];
        for (int row = 0; row < c.length; row++) {
            for (int col = 0; col < c[row].length; col++) {
                c[row][col] = a[row][col] + b[row][col];
            }
        }
        return c;
    }

    /**
     * Метод для умножения матрицы на число.
     *
     * @param a      матрица A
     * @param number число
     * @return матрица nA
     */
    public static double[][] multiplicationByNumber(double[][] a, double number) {
        double[][] result = new double[a.length][a[0].length];
        for (int row = 0; row < a.length; row++) {
            for (int col = 0; col < a[row].length; col++) {
                result[row][col] = number * a[row][col];
            }
        }
        return result;
    }

    /**
     * Метод для умножения матриц
     *
     * @param a матрица A
     * @param b матрица B
     * @return матрица AB
     */
    public static double[][] multiplication(double[][] a, double[][] b) {
        if (!checkDimensionForMultiplication(a, b)) {
            return null;
        }
        double[][] result = new double[a.length][b[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyMatricesCell(a, b, row, col);
            }
        }

        return result;
    }

    /**
     * Метод для транспонирования матриц
     *
     * @param a    матрица A
     * @param type тип транспонирования
     * @return матрица A(T)
     */
    public static double[][] transpose(double[][] a, TransposeType type) {
        double[][] result;
        switch (type) {
            case MAIN_DIAGONAL:
                result = new double[a[0].length][a.length];
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[i].length; j++) {
                        result[i][j] = a[j][i];
                    }
                }
                return result;
            case SIDE_DIAGONAL:
                result = new double[a[0].length][a.length];
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[i].length; j++) {
                        result[i][j] = a[result[i].length - j - 1][result.length - i - 1];
                    }
                }
                return result;
            case VERTICAL_LINE:
                result = new double[a.length][a[0].length];
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[i].length; j++) {
                        result[i][j] = a[i][result[i].length - j - 1];
                    }
                }
                return result;
            case HORIZONTAL_LINE:
                result = new double[a.length][a[0].length];
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[i].length; j++) {
                        result[i][j] = a[result.length - i - 1][j];
                    }
                }
                return result;
            default:
                return null;
        }
    }

    /**
     * Метод для нахождения определителя матрицы
     *
     * @param a квадратная матрциа
     * @return определитель матрицы (число с плавающей точкой)
     */
    public static double determinant(double[][] a) {
        if (a.length != a[0].length) {
            throw new InvalidParameterException();
        }
        if (a.length == 2) {
            return a[0][0] * a[1][1] - a[0][1] * a[1][0];
        }
        double result = 0;
        for (int i = 0; i < a.length; i++) {
            double[][] minor = getMinor(0, i, a);
            if (i % 2 == 0) {
                result += (a[0][i] * determinant(minor));
            } else {
                result -= (a[0][i] * determinant(minor));
            }
        }
        return result;
    }

    /**
     * Метод для нахождения обратной матрицы
     *
     * @param a квадратная матрица
     * @return обратная матрица
     */
    public static double[][] inverse(double[][] a) {
        if (a.length != a[0].length) {
            throw new InvalidParameterException();
        }
        double[][] result = null;
        double detA = determinant(a);
        try {
            result = new double[a.length][a[0].length];
            double[][] adjA = adjugateMatrix(a);
            result = multiplicationByNumber(adjA, 1 / detA);
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static boolean checkDimensionForAddition(double[][] a, double[][] b) {
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i].length != b[i].length) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkDimensionForMultiplication(double[][] a, double[][] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i].length != b.length) {
                return false;
            }
        }
        return true;
    }

    private static double multiplyMatricesCell(double[][] a, double[][] b, int row, int col) {
        double cell = 0;
        for (int i = 0; i < b.length; i++) {
            cell += a[row][i] * b[i][col];
        }
        return cell;
    }

    private static double[][] getMinor(int row, int col, double[][] a) {
        double[][] result = new double[a.length - 1][a[0].length - 1];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (row < i && col < j) {
                    result[i - 1][j - 1] = a[i][j];
                } else if (row < i && col > j) {
                    result[i - 1][j] = a[i][j];
                } else if (row > i && col < j) {
                    result[i][j - 1] = a[i][j];
                } else if (row > i && col > j) {
                    result[i][j] = a[i][j];
                }
            }
        }
        return result;
    }

    private static double[][] adjugateMatrix(double[][] a) {
        double[][] adj = new double[a.length][a[0].length];
        double[][] aT = transpose(a, TransposeType.MAIN_DIAGONAL);
        for (int i = 0; i < Objects.requireNonNull(aT).length; i++) {
            for (int j = 0; j < aT[i].length; j++) {
                adj[i][j] = Math.pow(-1, i + j + 2) * determinant(getMinor(i, j, aT));
            }
        }
        return adj;
    }
}