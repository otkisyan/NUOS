package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();
        main.run();
    }


    public void run() {

        Solver solver = new Solver();
        double[][] A = {
                {0.62, 0.56, -0.43},
                {1.32, -0.88, 1.76},
                {0.73, 1.42, -0.34}
        };
        double[] b = {1.16, 2.07, 2.18};
        solver.print(A);
        double[][] Acopy = new double[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            Acopy[i] = Arrays.copyOf(A[i],
                    A[i].length);
        solver.solveGauss(Acopy, b);
        solver.solveLU(Acopy, b);
        solver.solve(Acopy, b);
    }

}