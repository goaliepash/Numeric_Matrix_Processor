package processor;

import java.util.Locale;
import java.util.Scanner;

/**
 * Матричный процессор
 *
 * @author Иванов Павел Александрович
 */
public class MatrixProcessor {

    /**
     * Метод для запуска матричного процессора
     */
    public static void launch() {
        while (true) {
            boolean exitFlag = false;

            MatrixUtils.printMenu();

            System.out.print("Your choice: ");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();

            double[][] a;
            double[][] b;
            double[][] result;
            switch (option) {
                case 1:
                    a = MatrixUtils.inputMatrix();
                    b = MatrixUtils.inputMatrix();
                    result = Matrix.addition(a, b);
                    MatrixUtils.printMatrix(result);
                    System.out.println();
                    break;
                case 2:
                    a = MatrixUtils.inputMatrix();
                    System.out.print("Enter constant: ");
                    String numStr = new Scanner(System.in).next();
                    double n = Double.parseDouble(numStr);
                    result = Matrix.multiplicationByNumber(a, n);
                    MatrixUtils.printMatrix(result);
                    System.out.println();
                    break;
                case 3:
                    a = MatrixUtils.inputMatrix();
                    b = MatrixUtils.inputMatrix();
                    result = Matrix.multiplication(a, b);
                    MatrixUtils.printMatrix(result);
                    System.out.println();
                    break;
                case 4:
                    MatrixUtils.printTransposeTypes();
                    System.out.print("Your choice: ");
                    int choice = new Scanner(System.in).nextInt();
                    TransposeType type = TransposeType.values()[choice - 1];
                    a = MatrixUtils.inputMatrix();
                    result = Matrix.transpose(a, type);
                    MatrixUtils.printMatrix(result);
                    break;
                case 5:
                    a = MatrixUtils.inputMatrix();
                    double detA = Matrix.determinant(a);
                    System.out.printf(Locale.US, "The result is:\n%f\n\n", detA);
                    break;
                case 6:
                    a = MatrixUtils.inputMatrix();
                    result = Matrix.inverse(a);
                    if (result != null) {
                        MatrixUtils.printMatrix(result);
                    } else {
                        System.out.println("This matrix doesn't have an inverse.");
                    }
                    System.out.println();
                case 0:
                    exitFlag = true;
                    break;
                default:
                    break;
            }

            if (exitFlag) {
                break;
            }
        }
    }
}
