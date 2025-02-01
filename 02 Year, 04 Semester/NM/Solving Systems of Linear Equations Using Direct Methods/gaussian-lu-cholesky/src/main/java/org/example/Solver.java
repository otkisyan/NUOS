package org.example;

public class Solver {

    public void solveGauss(double[][] A, double[] b) {
        System.out.println("Рішення методом Гауса:\n");
        int n = b.length;
        double[] x = new double[n];
        for (int k = 0; k < n - 1; k++) {
            for (int i = k + 1; i < n; i++) {
                double coef = A[i][k] / A[k][k];
                b[i] -= coef * b[k];
                for (int j = k; j < n; j++) {
                    A[i][j] -= coef * A[k][j];
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        for (int i = 0; i < n; i++)
            System.out.println("x[" + i + "] = " +
                    x[i]);
        System.out.println("Похибка: " + estimateError(A, b, x) + "\n");
    }

    public void solveLU(double[][] A, double[] b) {
        System.out.println("Рішення методом LU-розкладу:\n");
        int n = b.length;
        double[][] L = new double[n][n];
        double[][] U = new double[n][n];
        for (int i = 0; i < n; i++) {
            L[i][i] = 1.0;
        }
        for (int k = 0; k < n; k++) {
            U[k][k] = A[k][k];
            for (int i = k + 1; i < n; i++) {
                L[i][k] = A[i][k] / U[k][k];
                U[k][i] = A[k][i];
            }
            for (int i = k + 1; i < n; i++) {
                for (int j = k + 1; j < n; j++) {
                    A[i][j] = A[i][j] - L[i][k] * U[k][j];
                }
            }
        }
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            y[i] = b[i];
            for (int j = 0; j < i; j++) {
                y[i] = y[i] - L[i][j] * y[j];
            }
        }
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            x[i] = y[i];
            for (int j = i + 1; j < n; j++) {
                x[i] = x[i] - U[i][j] * x[j];
            }
            x[i] = x[i] / U[i][i];
        }
        for (int i = 0; i < n; i++)
            System.out.println("x[" + i + "] = " +
                    x[i]);
        System.out.println("Похибка: " + estimateError(A, b, x) + "\n");
    }

    public void solve(double[][] coefficients, double[] constants) {
        System.out.println("Рішення методом квадратного кореню:\n");
        int n = coefficients.length;
        double[][] augmentedMatrix = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            System.arraycopy(coefficients[i], 0, augmentedMatrix[i], 0, n);
            augmentedMatrix[i][n] = constants[i];
        }
        for (int k = 0; k < n; k++) {
            int pivotRow = k;
            double pivotValue = Math.abs(augmentedMatrix[k][k]);
            for (int i = k + 1; i < n; i++) {
                double absValue = Math.abs(augmentedMatrix[i][k]);
                if (absValue > pivotValue) {
                    pivotRow = i;
                    pivotValue = absValue;
                }
            }
            if (pivotRow != k) {
                double[] tempRow = augmentedMatrix[k];
                augmentedMatrix[k] = augmentedMatrix[pivotRow];
                augmentedMatrix[pivotRow] = tempRow;
            }
            for (int i = k + 1; i < n; i++) {
                double factor = augmentedMatrix[i][k] / augmentedMatrix[k][k];
                for (int j = k + 1; j <= n; j++) {
                    augmentedMatrix[i][j] -= factor * augmentedMatrix[k][j];
                }
            }
        }

        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += augmentedMatrix[i][j] * solution[j];
            }
            solution[i] = (augmentedMatrix[i][n] - sum) / augmentedMatrix[i][i];
        }
        for (int i = 0; i < n; i++) {
            System.out.println("x[" + i + "] = " + solution[i]);
        }

        System.out.println("Похибка: " +
                estimateError(coefficients, constants, solution) + "\n");
    }

    private double estimateError(double[][] A, double[] b, double[] x) {
        int n = b.length;
        double[] residual = new double[n];
        double[] Ax = new double[n];
        for (int i = 0; i < n; i++) {
            Ax[i] = 0.0;
            for (int j = 0; j < n; j++) {
                Ax[i] += A[i][j] * x[j];
            }
            residual[i] = b[i] - Ax[i];
        }
        double norm = 0.0;
        for (int i = 0; i < n; i++) {
            norm += residual[i] * residual[i];
        }
        norm = Math.sqrt(norm);
        return norm;
    }

    public void print(double[][] A) {
        for (double[] doubles : A) {
            for (int j = 0; j < A.length; j++) {
                System.out.print(doubles[j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}



