package org.example;

import java.util.Random;

public class Main {

    private final static int[] LOAD = {128, 1024};

    public static void main(String[] args) {

        Main main = new Main();
        main.run();
        //main.example();
    }

    public void run() {

        System.out.println("Множення матриць за визначенням");
        System.out.println();

        for (int load : LOAD) {

            System.out.println("Розмір матриць: " + load + "x" + load);

            int[][] matrix1 = new int[load][load];
            matrix1 = fillMatrix(matrix1);
            int[][] matrix2 = new int[load][load];
            matrix2 = fillMatrix(matrix2);

            long startTime = System.currentTimeMillis();
            int[][] multipliedMatrix = multiplyMatrixDefault(matrix1, matrix2);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            System.out.println("Час виконання множення (мілісекунди): " + elapsedTime);
            System.out.println();
        }


        System.out.println();
        System.out.println("Множення матриць з транспонуванням");
        System.out.println();

        for (int load : LOAD) {

            System.out.println("Розмір матриць: " + load + "x" + load);

            int[][] matrix1 = new int[load][load];
            matrix1 = fillMatrix(matrix1);
            int[][] matrix2 = new int[load][load];
            matrix2 = fillMatrix(matrix2);

            long startTime = System.currentTimeMillis();
            int[][] multipliedMatrix = multiplyMatrixWithTranspose(matrix1, matrix2);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            System.out.println("Час виконання множення (мілісекунди): " + elapsedTime);
            System.out.println();
        }

        System.out.println();
        System.out.println("Множення матриць за алгоритмом Штрассена");
        System.out.println();

        for (int load : LOAD) {

            System.out.println("Розмір матриць: " + load + "x" + load);

            int[][] matrix1 = new int[load][load];
            matrix1 = fillMatrix(matrix1);
            int[][] matrix2 = new int[load][load];
            matrix2 = fillMatrix(matrix2);

            long startTime = System.currentTimeMillis();
            int[][] multipliedMatrix = strassen(matrix1, matrix2);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            System.out.println("Час виконання множення (мілісекунди): " + elapsedTime);
            System.out.println();
        }
    }


    public void example() {

        int load = 128;
        int[][] matrix1 = new int[load][load];
        matrix1 = fillMatrix(matrix1);

        int[][] matrix2 = new int[load][load];
        matrix2 = fillMatrix(matrix2);

        int[][] multipliedMatrix = multiplyMatrixDefault(matrix1, matrix2);
        int[][] transposeMatrix = multiplyMatrixWithTranspose(matrix1, matrix2);
        int[][] strassenMatrix = strassen(matrix1, matrix2);

        System.out.println("Матриця помножена за визначенням:");
        printMatrix(multipliedMatrix);
        System.out.println();
        System.out.println("Матриця помножена з транспонуванням:");
        printMatrix(transposeMatrix);
        System.out.println();
        System.out.println("Матриця помножена за алгоритмом Штрассена:");
        printMatrix(strassenMatrix);
    }

    /**
     * Заполняет матрицу значениями от 1 до 100 включительно
     *
     * @param matrix матрица для заполнения
     * @return заполненная матрица
     * @see <a href="https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range">Stackoverflow: Generate a random double in a range</a>
     */
    public int[][] fillMatrix(int[][] matrix) {

        Random rand = new Random();

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[0].length; j++) {

                matrix[i][j] = rand.nextInt(1, 100);
            }
        }

        return matrix;
    }

    /**
     * Печатает матрицу на экран
     *
     * @param matrix матрица для печати
     */
    public void printMatrix(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[0].length; j++) {

                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Умножает матрицы по определению
     *
     * @param A первая матрица
     * @param B вторая матрица
     * @return умноженная матрица
     * @throws IllegalArgumentException если количество столбцов первой матрицы не совпадает с количеством строк у второй
     * @see <a href="https://en.wikipedia.org/wiki/Matrix_multiplication">Wikipedia: Matrix multiplication</a>
     */
    public int[][] multiplyMatrixDefault(int[][] A, int[][] B) {

        // Матрицы можно умножить, в том случае если количество столбцов первой совпадает с количеством строк у второй
        if (A[0].length != B.length) {

            throw new IllegalArgumentException("Matrices can be multiplied if the number of columns of the first matrix " +
                    "is the same as the number of rows of the second matrix");
        }

        int rows1 = A.length;
        int cols1 = A[0].length; // columns A && rows B
        int cols2 = B[0].length;

        // В конечной матрице количество строк будет равно количеству строк первой матрицы,
        // а столбцов - количеству столбцов второй матрицы
        int[][] result = new int[rows1][cols2];

        // Умножение матрицы на вектор
        if (cols2 == 1) {

            for (int i = 0; i < rows1; i++) {

                // Умножаем каждую строку матрицы на соответствующий элемент вектора,
                // а затем слаживаем полученные произведения.
                int sum = 0;
                for (int j = 0; j < cols1; j++) {

                    sum += A[i][j] * B[j][0];
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
                        result[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }
        return result;
    }

    /**
     * Умножает матрицы с транспонированием одной из матриц
     *
     * @param A первая матрица
     * @param B вторая матрица
     * @return умноженная матрица
     * @throws IllegalArgumentException если количество столбцов первой матрицы не совпадает с количеством строк у второй
     * @see <a href="https://www.youtube.com/watch?v=b8naWI80-r8&ab_channel=JamesElliott">YouTube: Matrix Multiplication with a Transpose </a>
     */
    public int[][] multiplyMatrixWithTranspose(int[][] A, int[][] B) {

        // Матрицы можно умножить, в том случае если количество столбцов первой совпадает с количеством строк у второй
        if (A[0].length != B.length) {

            throw new IllegalArgumentException("Matrices can be multiplied if the number of columns of the first matrix " +
                    "is the same as the number of rows of the second matrix");
        }

        int rowsA = A.length;
        int colsA = A[0].length; // columns A && rows B
        int colsB = B[0].length;

        // Транспонируем матрицу B
        int[][] BT = transposeMatrix(B);

        // Размер матрицы C = количество строк матрицы A x количество столбцов матрицы B
        int[][] C = new int[rowsA][colsB];

        // Необходимо перемножить каждую строку матрицы A на каждый столбец матрицы B^T.
        for (int i = 0; i < rowsA; i++) {

            for (int j = 0; j < colsB; j++) {

                for (int k = 0; k < colsA; k++) {

                    C[i][j] += A[i][k] * BT[j][k];
                }
            }
        }

        return C;
    }

    /**
     * Умножает матрицы используя алгоритм Штрассена
     *
     * @param A первая матрица
     * @param B вторая матрица
     * @return умноженная матрица
     * @see <a href="https://en.wikipedia.org/wiki/Strassen_algorithm">Wikipedia: Strassen algorithm</a>
     * @see <a href="https://habr.com/ru/articles/313258/">Habr: Divide&Conquer над алгоритмом Штрассена</a>
     */
    public int[][] strassen(int[][] A, int[][] B) {

        if (!isPowerOfTwo(A.length) || !isPowerOfTwo(B.length)) {

            throw new IllegalArgumentException();
        }

        int n = A.length;
        int m = n / 2;

        // Использование гибридного подхода, когда алгоритм Штрассена используется рекурсивно до определенного
        // порогового размера матриц, а затем для меньших матриц используется обычный алгоритм умножения матриц.
        // Это позволяет уменьшить глубину рекурсии и избежать ошибок переполнения стека.
        if (n <= 64) {

            return multiplyMatrixWithTranspose(A, B);
        }

        // Разделяем матрицы на 4 блока
        int[][] A11 = new int[m][m];
        int[][] A12 = new int[m][m];
        int[][] A21 = new int[m][m];
        int[][] A22 = new int[m][m];

        int[][] B11 = new int[m][m];
        int[][] B12 = new int[m][m];
        int[][] B21 = new int[m][m];
        int[][] B22 = new int[m][m];

        for (int i = 0; i < m; i++) {

            System.arraycopy(A[i], 0, A11[i], 0, m);
            System.arraycopy(A[i], m, A12[i], 0, m);
            System.arraycopy(A[m + i], 0, A21[i], 0, m);
            System.arraycopy(A[m + i], m, A22[i], 0, m);

            System.arraycopy(B[i], 0, B11[i], 0, m);
            System.arraycopy(B[i], m, B12[i], 0, m);
            System.arraycopy(B[m + i], 0, B21[i], 0, m);
            System.arraycopy(B[m + i], m, B22[i], 0, m);
        }

        // Вычисляем промежуточные матрицы P1 - P7
        int[][] P1 = strassen(add(A11, A22), add(B11, B22));
        int[][] P2 = strassen(add(A21, A22), B11);
        int[][] P3 = strassen(A11, subtract(B12, B22));
        int[][] P4 = strassen(A22, subtract(B21, B11));
        int[][] P5 = strassen(add(A11, A12), B22);
        int[][] P6 = strassen(subtract(A21, A11), add(B11, B12));
        int[][] P7 = strassen(subtract(A12, A22), add(B21, B22));

        // Используя промежуточные матрицы, вычисляем блоки результата матрицы C
        int[][] C11 = add(add(P1, P4), subtract(P7, P5));
        int[][] C12 = add(P3, P5);
        int[][] C21 = add(P2, P4);
        int[][] C22 = add(subtract(P1, P2), add(P3, P6));

        // Объединяем блоки результата C11, C12, C21, C22 в одну матрицу размером n x n
        int[][] C = new int[n][n];

        for (int i = 0; i < m; i++) {

            System.arraycopy(C11[i], 0, C[i], 0, m);
            System.arraycopy(C12[i], 0, C[i], m, m);
            System.arraycopy(C21[i], 0, C[m + i], 0, m);
            System.arraycopy(C22[i], 0, C[m + i], m, m);
        }

        return C;
    }

    /**
     * Выполняет поэлементное сложение матриц A и B
     *
     * @param A первая матрица
     * @param B вторая матрица
     * @return сумма двух матриц
     * @throws IllegalArgumentException если матрицы A и B имеют разные размерности
     */
    public int[][] add(int[][] A, int[][] B) {

        int rows1 = A.length;
        int cols1 = A[0].length;

        int rows2 = B.length;
        int cols2 = B[0].length;

        if (rows1 != rows2 || cols1 != cols2) {

            throw new IllegalArgumentException();
        }

        int[][] C = new int[rows1][cols1];

        for (int i = 0; i < rows1; i++) {

            for (int j = 0; j < cols1; j++) {

                C[i][j] = A[i][j] + B[i][j];
            }
        }

        return C;
    }

    /**
     * Выполняет поэлементное вычитание матриц A и B
     *
     * @param A первая матрица
     * @param B вторая матрица
     * @return разность двух матриц
     * @throws IllegalArgumentException если матрицы A и B имеют разные размерности
     */
    public int[][] subtract(int[][] A, int[][] B) {

        int rows1 = A.length;
        int cols1 = A[0].length;

        int rows2 = B.length;
        int cols2 = B[0].length;

        if (rows1 != rows2 || cols1 != cols2) {

            throw new IllegalArgumentException();
        }

        int[][] C = new int[rows1][cols1];

        for (int i = 0; i < rows1; i++) {

            for (int j = 0; j < cols1; j++) {

                C[i][j] = A[i][j] - B[i][j];
            }
        }

        return C;
    }

    /**
     * Проверяет, является ли число степенью двойки
     *
     * @param n число для проверки
     * @return <code>true</code> если число является степенью двойки, <code>false</code> в противном случае
     * @see <a href="https://stackoverflow.com/questions/19383248/find-if-a-number-is-a-power-of-two-without-math-function-or-log-function">Stackoverflow: Find if a number is a power of two</a>
     */
    public boolean isPowerOfTwo(int n) {

        return (n & (n - 1)) == 0;
    }

    /**
     * Транспонирует матрицу
     *
     * @param matrix матрица для транспонирования
     * @return транспонированная матрица
     */
    public int[][] transposeMatrix(int[][] matrix) {

        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] transposed = new int[columns][rows];

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {

                transposed[j][i] = matrix[i][j];
            }
        }

        return transposed;
    }
}
