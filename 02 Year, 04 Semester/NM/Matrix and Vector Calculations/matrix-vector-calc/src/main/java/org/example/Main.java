package org.example;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("1. Multiply vector by scalar");
        System.out.println("2. Add two vectors");
        System.out.println("3. Scalar product of two vectors");
        System.out.println("4. Modulus of a vector");
        System.out.println("5. Transpose a matrix");
        System.out.println("6. Multiply matrix by scalar");
        System.out.println("7. Multiply matrix by vector");
        System.out.println("8. Add two matrices");
        System.out.println("9. Multiply two matrices");
        System.out.print("Choose an operation (1-9): ");

        int choice = sc.nextInt();

        switch (choice) {

            case 1: {

                // a) добуток вектора і скаляра;

                System.out.println("Enter size of the vector:");
                int n = sc.nextInt();
                double[] vector = new double[n];

                System.out.println("Enter vector: ");
                for (int i = 0; i < n; i++) {

                    vector[i] = sc.nextDouble();
                }

                System.out.println("Enter a scalar value:");
                double scalar = sc.nextDouble();

                double[] result = multiplyVectorByScalar(vector, scalar);
                System.out.println("The product of the vector and scalar is:");
                System.out.println(Arrays.toString(result));

                System.out.println();
                break;

            }

            case 2: {

                // b) сума двох векторів;

                System.out.println("Vectors must have the same size to be added");
                System.out.println("Enter size of the vectors:");
                int n = sc.nextInt();
                double[] vector1 = new double[n];

                System.out.println("Enter the first vector:");
                for (int i = 0; i < n; i++) {

                    vector1[i] = sc.nextDouble();
                }

                System.out.println("Enter the second vector:");
                double[] vector2 = new double[n];

                for (int i = 0; i < n; i++) {

                    vector2[i] = sc.nextDouble();
                }

                double[] result = addVectors(vector1, vector2);
                System.out.println("The sum of vectors is");
                System.out.println(Arrays.toString(result));

                break;
            }

            case 3: {

                // c) скалярний добуток векторів;

                System.out.println("Vectors must be the same size for a scalar product");
                System.out.println("Enter size of the vectors:");
                int n = sc.nextInt();
                double[] vector1 = new double[n];

                System.out.println("Enter the first vector:");
                for (int i = 0; i < n; i++) {

                    vector1[i] = sc.nextDouble();
                }

                System.out.println("Enter the second vector:");
                double[] vector2 = new double[n];

                for (int i = 0; i < n; i++) {

                    vector2[i] = sc.nextDouble();
                }

                double result = scalarProduct(vector1, vector2);
                System.out.println("The scalar product of vectors is: ");
                System.out.println(result);
                break;
            }

            case 4: {

                // d) модуль вектора

                System.out.println("Enter size of the vector:");
                int n = sc.nextInt();
                double[] vector1 = new double[n];

                System.out.println("Enter the vector:");
                for (int i = 0; i < n; i++) {

                    vector1[i] = sc.nextDouble();
                }

                double result = modulus(vector1);
                System.out.println("Vector modulus is: ");
                System.out.println(result);

                break;
            }
            case 5: {

                // e) транспонування матриці;

                System.out.println("Enter size of the matrix");
                System.out.println("Enter the number of matrix rows: ");
                int rows = sc.nextInt();

                System.out.println("Enter the number of matrix cols: ");
                int columns = sc.nextInt();

                double[][] matrix = new double[rows][columns];

                System.out.println("Enter matrix:");
                for (int i = 0; i < rows; i++) {

                    for (int j = 0; j < columns; j++) {

                        matrix[i][j] = sc.nextDouble();
                    }
                }

                double[][] transposedMatrix = transposeMatrix(matrix);
                System.out.println("The transpose of the matrix is:");

                for (int i = 0; i < columns; i++) {

                    for (int j = 0; j < rows; j++) {

                        System.out.print(transposedMatrix[i][j] + " ");
                    }

                    System.out.println();
                }

                break;
            }

            case 6: {

                // f) добуток матриці і скаляра;

                System.out.println("Enter size of the matrix");
                System.out.println("Enter the number of matrix rows: ");
                int rows = sc.nextInt();

                System.out.println("Enter the number of matrix cols: ");
                int columns = sc.nextInt();

                double[][] matrix = new double[rows][columns];

                System.out.println("Enter matrix: ");
                for (int i = 0; i < rows; i++) {

                    for (int j = 0; j < columns; j++) {

                        matrix[i][j] = sc.nextDouble();
                    }
                }

                System.out.println("Enter a scalar value:");
                double scalar = sc.nextDouble();

                double[][] result = multiplyMatrixByScalar(matrix, scalar);

                System.out.println("Product of matrix by scalar is: ");
                for (int i = 0; i < rows; i++) {

                    for (int j = 0; j < columns; j++) {

                        System.out.print(result[i][j] + " ");
                    }

                    System.out.println();
                }

                break;
            }

            case 7: {

                // g) добуток матриці й вектора;
                System.out.println("The number of columns in the matrix must equal the length of the vector.");

                System.out.println("Enter size of the matrix");
                System.out.println("Enter the number of matrix rows: ");
                int rows = sc.nextInt();

                System.out.println("Enter the number of matrix cols: ");
                int columns = sc.nextInt();

                double[][] matrix = new double[rows][columns];

                System.out.println("Enter matrix:");
                for (int i = 0; i < rows; i++) {

                    for (int j = 0; j < columns; j++) {

                        matrix[i][j] = sc.nextDouble();
                    }
                }

                double[] vector = new double[columns];

                System.out.println("Enter vector:");
                for (int i = 0; i < vector.length; i++) {

                    vector[i] = sc.nextDouble();
                }

                System.out.println("Product of a matrix by a vector");
                double[] result = multiplyMatrixByVector(matrix, vector);
                System.out.println(Arrays.toString(result));


                break;
            }

            case 8: {

                // h) сума двох матриць;
                System.out.println("To perform addition of matrices, they must be the same size");

                System.out.println("Enter size of the matrices");
                System.out.println("Enter the number of matrix rows: ");
                int rows = sc.nextInt();

                System.out.println("Enter the number of matrix cols: ");
                int columns = sc.nextInt();

                double[][] matrix1 = new double[rows][columns];

                System.out.println("Enter matrix1:");
                for (int i = 0; i < rows; i++) {

                    for (int j = 0; j < columns; j++) {

                        matrix1[i][j] = sc.nextDouble();
                    }
                }

                double[][] matrix2 = new double[rows][columns];

                System.out.println("Enter matrix2:");
                for (int i = 0; i < rows; i++) {

                    for (int j = 0; j < columns; j++) {

                        matrix2[i][j] = sc.nextDouble();
                    }
                }

                System.out.println("The sum of matrices is:");

                double[][] result = matrixSum(matrix1, matrix2);

                for (int i = 0; i < rows; i++) {

                    for (int j = 0; j < columns; j++) {

                        System.out.print(result[i][j] + " ");
                    }

                    System.out.println();
                }


                break;
            }
            case 9: {

                // i) добуток двох матриць;
                System.out.println("Matrices can be multiplied if the number of columns of the first matrix " +
                        "is the same as the number of rows of the second matrix");

                System.out.println("Enter size of the matrices");
                System.out.println("Enter the number of matrix1 rows: ");
                int rows = sc.nextInt();

                System.out.println("Enter the number of matrix1 cols: ");
                int columns = sc.nextInt();

                double[][] matrix1 = new double[rows][columns];

                System.out.println("Enter matrix1:");
                for (int i = 0; i < rows; i++) {

                    for (int j = 0; j < columns; j++) {

                        matrix1[i][j] = sc.nextDouble();
                    }
                }


                System.out.println("Enter the number of matrix2 cols: ");
                int columns2 = sc.nextInt();

                // У второй матрицы должно быть столько строк, сколько у первой матрицы столбцов
                double[][] matrix2 = new double[columns][columns2];

                System.out.println("Enter matrix2:");

                if (columns2 == 1) {

                    for (int i = 0; i < columns; i++) {

                        matrix2[i][0] = sc.nextDouble();
                    }
                } else {
                    for (int i = 0; i < columns; i++) {

                        for (int j = 0; j < columns2; j++) {

                            matrix2[i][j] = sc.nextDouble();
                        }
                    }
                }

                double[][] result = multiplyMatrixByMatrix(matrix1, matrix2);

                System.out.println("The product of matrices");
                for (int i = 0; i < result.length; i++) {

                    for (int j = 0; j < result[0].length; j++) {

                        System.out.print(result[i][j] + " ");
                    }

                    System.out.println();
                }

                break;
            }
            default:

                System.out.println("Invalid choice");
                break;
        }
    }


    // a) добуток вектора і скаляра;
    public static double[] multiplyVectorByScalar(double[] vector, double scalar) {

        int length = vector.length;
        double[] result = new double[length];

        for (int i = 0; i < length; i++) {

            result[i] = vector[i] * scalar;
        }

        return result;
    }

    // b) сума двох векторів;
    public static double[] addVectors(double[] vector1, double[] vector2) {

        // Векторы должны иметь одинаковый размер, чтобы их можно было сложить.
        if (vector1.length != vector2.length) {

            throw new IllegalArgumentException("Vectors must have the same size to be added.");
        }

        int length = vector1.length;
        double[] result = new double[length];

        for (int i = 0; i < length; i++) {

            result[i] = vector1[i] + vector2[i];
        }

        return result;
    }

    // c) скалярний добуток векторів;
    public static double scalarProduct(double[] vector1, double[] vector2) {

        // Скалярное произведение двух векторов требует, чтобы векторы имели одинаковый размер.
        if (vector1.length != vector2.length) {

            throw new IllegalArgumentException("Vectors must be the same size for a scalar product");
        }

        int length = vector1.length;
        double result = 0;

        for (int i = 0; i < length; i++) {

            result += vector1[i] * vector2[i];
        }

        return result;
    }

    // d) модуль вектора
    public static double modulus(double[] vector) {

        int length = vector.length;
        double result = 0;

        for (int i = 0; i < length; i++) {

            result += vector[i] * vector[i];
        }

        return Math.sqrt(result);
    }

    // e) транспонування матриці;
    public static double[][] transposeMatrix(double[][] matrix) {

        int rows = matrix.length;
        int columns = matrix[0].length;
        double[][] result = new double[columns][rows];

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {

                result[j][i] = matrix[i][j];
            }
        }

        return result;
    }

    // f) добуток матриці і скаляра;
    public static double[][] multiplyMatrixByScalar(double[][] matrix, double scalar) {

        int rows = matrix.length;
        int columns = matrix[0].length;
        double[][] result = new double[rows][columns];

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {

                result[i][j] = matrix[i][j] * scalar;
            }
        }
        return result;
    }

    // g) добуток матриці й вектора;
    public static double[] multiplyMatrixByVector(double[][] matrix, double[] vector) {

        // Количество столбцов в матрице должно быть равно длине вектора.
        // Другими словами, вектор должен иметь тот же размер, что и количество столбцов в матрице.
        if (vector.length != matrix[0].length) {

            throw new IllegalArgumentException("The number of columns in the matrix must equal the length of the vector.");
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        double[] result = new double[rows];

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                result[i] += matrix[i][j] * vector[j];
            }
        }

        return result;
    }


    // h) сума двох матриць;
    public static double[][] matrixSum(double[][] matrix1, double[][] matrix2) {

        // Для выполнения сложения (вычитания) матриц, необходимо, чтобы они были одинаковыми по размеру.
        // Например, если дана матрица «два на два», то ее можно складывать только с матрицей «два на два» и никакой другой!
        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {

            throw new IllegalArgumentException("To perform addition of matrices, they must be the same size.");
        }

        int rows = matrix1.length;
        int cols = matrix1[0].length;
        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    // i) добуток двох матриць;
    public static double[][] multiplyMatrixByMatrix(double[][] matrix1, double[][] matrix2) {

        // Матрицы можно умножить, в том случае если количество столбцов первой совпадает с количеством строк у второй
        if (matrix1[0].length != matrix2.length) {

            throw new IllegalArgumentException("Matrices can be multiplied if the number of columns of the first matrix " +
                    "is the same as the number of rows of the second matrix");
        }

        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int cols2 = matrix2[0].length;

        // В конечной матрице количество строк будет равно количеству строк первой матрицы,
        // а столбцов - количеству столбцов второй матрицы
        double[][] result = new double[rows1][cols2];

        if (cols2 == 1) {

            for (int i = 0; i < rows1; i++) {

                double sum = 0;
                for (int j = 0; j < cols1; j++) {

                    sum += matrix1[i][j] * matrix2[j][0];
                }

                result[i][0] = sum;
            }

        } else {

            // Первый цикл с переменной i выполняет итерации по строкам первой матрицы matrix1.
            // Следующий цикл с переменной j выполняет итерацию по столбцам второй матрицы matrix2.
            // Самый внутренний цикл с переменной k выполняет итерации по столбцам первой матрицы и строкам второй матрицы.
            for (int i = 0; i < rows1; i++) {

                for (int j = 0; j < cols2; j++) {

                    for (int k = 0; k < cols1; k++) {

                        // Для вычисления произведения строки первой матрицы и столбца второй матрицы
                        // необходимо взять произведение элементов, находящихся в одинаковых позициях в строке и столбце.
                        // При использовании matrix2[k][j] для вычисления берется произведение
                        // элемента в k-м столбце первой матрицы и соответствующего элемента в j-м столбце второй матрицы.
                        result[i][j] += matrix1[i][k] * matrix2[k][j];
                    }
                }
            }
        }

        return result;
    }

}
