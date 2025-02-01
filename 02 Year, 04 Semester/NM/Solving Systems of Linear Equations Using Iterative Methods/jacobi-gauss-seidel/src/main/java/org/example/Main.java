package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();
        main.run();
    }

    public void run() {

        Methods iterativeMethods = new Methods();

        double[][] a = {
                {33, 3.7, 4.2},
                {2.7, 23, 2.9},
                {4.1, 4.8, 50}
        };

        double[] b = {58, 61, 70};

        double prec = 1e-5;
        double[] xJacobi = iterativeMethods.solveJacobi(a, b, prec);
        double[] xGaussSeidel = iterativeMethods.solveGaussSeidel(a, b, prec);
        System.out.println("Розв'язок за методом простої ітерації (Якобі): " + Arrays.toString(xJacobi));
        System.out.println("Розв'язок за методом Гауса-Зейделя: " + Arrays.toString(xGaussSeidel));
    }
}
