package org.example;

import java.util.Arrays;

public class Methods {
    public double[] solveJacobi(double[][] A, double[] b, double prec) {

        int n = b.length;
        double[] x0 = new double[n];
        double[] x = new double[n];
        double[] d = new double[n];
        double[] r = new double[n];
        for (int i = 0; i < n; i++) {
            d[i] = A[i][i];
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    r[i] += A[i][j];
                }
            }
        }
        int iter = 0;
        double error = Double.MAX_VALUE;

        while (error > prec) {
            for (int i = 0; i < n; i++) {
                double sum = b[i];
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        sum -= A[i][j] * x0[j];
                    }
                }
                x[i] = 1.0 / d[i] * sum;
            }
            error = 0;
            for (int i = 0; i < n; i++) {
                error += Math.abs(x[i] - x0[i]);
            }
            iter++;
            x0 = Arrays.copyOf(x, n);
        }
        System.out.println("Проста ітерація (Якобі): " + iter + " ітерацій. Похибка = " + error);
        return x;
    }

    public double[] solveGaussSeidel(double[][] A, double[] b, double prec) {

        int n = b.length;
        double[] x0 = new double[n];
        double[] x = new double[n];
        int iter = 0;
        double error = Double.MAX_VALUE;
        while (error > prec) {
            for (int i = 0; i < n; i++) {
                double sum1 = 0;
                double sum2 = 0;
                for (int j = 0; j < i; j++) {
                    sum1 += A[i][j] * x[j];
                }
                for (int j = i + 1; j < n; j++) {
                    sum2 += A[i][j] * x0[j];
                }
                x[i] = 1.0 / A[i][i] * (b[i] - sum1 - sum2);
            }
            error = 0;
            for (int i = 0; i < n; i++) {
                error += Math.abs(x[i] - x0[i]);
            }
            iter++;
            x0 = Arrays.copyOf(x, n);

        }
        System.out.println("Метод Гауса-Зейделя: " + iter + " ітерацій. Похибка = " + error);
        return x;
    }

}

