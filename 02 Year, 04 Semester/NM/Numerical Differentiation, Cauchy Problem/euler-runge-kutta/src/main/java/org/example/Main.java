package org.example;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();
        main.run();

    }

    public void run() {

        double x0 = 1.4;
        double y0 = 2.5;
        double xFinal = 2.4;
        double h = 0.1;

        System.out.println("Euler Method:");
        eulerMethod(x0, y0, xFinal, h);
        System.out.println();
        System.out.println("Runge-Kutta Method:");
        rungeKuttaMethod(x0, y0, xFinal, h);
    }

    private static double f(double x, double y) {

        return x + Math.sin(y / Math.E);
    }

    public static void eulerMethod(double x0, double y0, double xFinal, double h) {

        double eps = 0.0001;
        double x = x0;
        double y = y0;
        System.out.printf("y(%.2f) = %f\n", x, y);
        for (x += h; x <= xFinal + eps; x += h) {
            y += h * f(x, y);
            System.out.printf("y(%.2f) = %f\n", x, y);
        }
    }

    public static void rungeKuttaMethod(double x0, double y0, double xFinal, double h) {

        double eps = 0.0001;
        double x = x0;
        double y = y0;
        System.out.printf("y(%.2f) = %f\n", x, y);

        for (x += h; x <= xFinal + eps; x += h) {
            double k1 = h * f(x, y);
            double k2 = h * f(x + h / 2, y + k1 / 2);
            double k3 = h * f(x + h / 2, y + k2 / 2);
            double k4 = h * f(x + h, y + k3);

            y += (k1 + 2 * k2 + 2 * k3 + k4) / 6;
            System.out.printf("y(%.2f) = %f\n", x, y);
        }
    }
}