package org.example;

public class Main {

    public static void main(String[] args) {

        Main main = new Main();
        main.run();
    }

    public void run() {

        double a = 1.2;
        double b = 2.8;

        System.out.print("Left rectangles method:");
        double integralLeft = integrateLeftRectangles(a, b);
        System.out.println();
        System.out.println("result = " + integralLeft);
        System.out.println();

        System.out.print("Right rectangles method:");
        double integralRight = integrateRightRectangles(a, b);
        System.out.println();
        System.out.println("result = " + integralRight);
        System.out.println();

        System.out.print("Trapezoidal method:");
        double trapezoidal = trapezoidIntegral(a, b);
        System.out.println();
        System.out.println("result = " + trapezoidal);
        System.out.println();

        System.out.print("Simpson's method:");
        double simpsons = simpsonsIntegrate(a, b);
        System.out.println();
        System.out.println("result = " + simpsons);
    }

    public static double f(double x) {

        return Math.log10(x + 3) * Math.sqrt(x + 1);
    }

    public static double integrateLeftRectangles(double a, double b) {

        double epsilon = 0.0001;
        int nLeft = 1;
        double dxLeft = (b - a) / nLeft;
        double prevSumLeft = 0.0;
        double sumLeft = f(a);
        double integralLeft = dxLeft * sumLeft;
        int iteration = 0;
        while (Math.abs(integralLeft - prevSumLeft) > epsilon) {
            nLeft *= 2;
            prevSumLeft = integralLeft;
            dxLeft = (b - a) / nLeft;
            sumLeft = 0.0;
            for (int i = 0; i < nLeft; i++) {
                double x = a + i * dxLeft;
                sumLeft += f(x);
            }
            integralLeft = dxLeft * sumLeft;
            iteration++;
        }
        System.out.print("\niterations: " + iteration);
        return integralLeft;
    }


    public static double integrateRightRectangles(double a, double b) {

        double epsilon = 0.0001;
        int nRight = 1;
        double dxRight = (b - a) / nRight;
        double prevSumRight = 0.0;
        double sumRight = f(b);
        double integralRight = dxRight * sumRight;
        int iteration = 0;
        while (Math.abs(integralRight - prevSumRight) > epsilon) {
            nRight *= 2;
            prevSumRight = integralRight;
            dxRight = (b - a) / nRight;
            sumRight = 0.0;
            for (int i = 1; i <= nRight; i++) {
                double x = a + i * dxRight;
                sumRight += f(x);
            }
            integralRight = dxRight * sumRight;
            iteration++;
        }
        System.out.print("\niterations: " + iteration);
        return integralRight;
    }

    public static double trapezoidIntegral(double a, double b) {

        double epsilon = 0.0001;
        int nTrap = 1;
        double dxTrap = (b - a) / nTrap;
        double prevSumTrap = 0.0;
        double sumTrap = 0.5 * (f(a) + f(b));
        double integralTrap = dxTrap * sumTrap;
        int iteration = 0;
        while (Math.abs(integralTrap - prevSumTrap) > epsilon) {
            nTrap *= 2;
            prevSumTrap = integralTrap;
            dxTrap = (b - a) / nTrap;
            sumTrap = 0.0;
            for (int i = 1; i < nTrap; i++) {
                double x = a + i * dxTrap;
                sumTrap += f(x);
            }
            integralTrap = dxTrap * (0.5 * f(a) + sumTrap + 0.5 * f(b));
            iteration++;
        }
        System.out.print("\niterations: " + iteration);
        return integralTrap;
    }

    public static double simpsonsIntegrate(double a, double b) {

        double epsilon = 0.0001;
        int nSimpson = 1;
        double dxSimpson = (b - a) / nSimpson;
        double prevSumSimpson = 0.0;
        double sumSimpson = f(a) + f(b);
        double integralSimpson = dxSimpson * sumSimpson / 2.0;
        int iteration = 0;
        while (Math.abs(integralSimpson - prevSumSimpson) > epsilon) {
            nSimpson *= 2;
            prevSumSimpson = integralSimpson;
            dxSimpson = (b - a) / nSimpson;
            sumSimpson = 0.0;
            for (int i = 1; i < nSimpson; i += 2) {
                double x = a + i * dxSimpson;
                sumSimpson += 4.0 * f(x);
            }
            for (int i = 2; i < nSimpson; i += 2) {
                double x = a + i * dxSimpson;
                sumSimpson += 2.0 * f(x);
            }
            integralSimpson = dxSimpson * (sumSimpson + f(a) + f(b)) / 3.0;
            iteration++;
        }
        System.out.print("\niterations: " + iteration);
        return integralSimpson;
    }
}