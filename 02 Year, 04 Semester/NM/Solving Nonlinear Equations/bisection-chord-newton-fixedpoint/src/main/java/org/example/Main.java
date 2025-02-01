package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();
        main.run();
        //main.run2();

    }

    public void run() {

        double eps = 0.00001;

        double a1 = -0.625;
        double b1 = 0;

        double a2 = 0.625;
        double b2 = 0.9375;

        System.out.println("Метод бісекцій:  ");
        System.out.println("Корінь на інтервалі [-0.625, 0]: " + bisection(a1, b1, eps));
        System.out.println("Корінь на інтервалі [0.625, 0.9375]: " + bisection(a2, b2, eps));

        System.out.println();
        System.out.println("Метод простої ітерації:  ");
        System.out.println("Корінь на інтервалі [0.625, 0.9375] з початковим наближенням -0.625: " + simpleIteration(a1, b1, -0.625, eps));

        System.out.println();
        System.out.println("Метод хорд:  ");
        System.out.println("Корінь на інтервалі [-0.625, 0]: " + chord(a1, b1, eps));
        System.out.println("Корінь на інтервалі [0.625, 0.9375]: " + chord(a2, b2, eps));


        System.out.println();
        System.out.println("Метод Ньютона: ");
        System.out.println("Корінь на інтервалі [-0.625, 0] з початковим наближенням -0.625: " + newton(a1, b1, -0.625, eps));
        System.out.println("Корінь на інтервалі [0.625, 0.9375] з початковим наближенням 0.625: " + newton(a2, b2, 0.625, eps));
    }

    public void run2(){

        Scanner sc = new Scanner(System.in);
        double eps = 0.00001;

        double a1 = 0;
        double b1 = 0;
        double approx = 0;

        System.out.println("Введіть першу границю інтервалу ізоляції");
        a1 = sc.nextDouble();
        System.out.println("Введіть другу границю інтервалу ізоляції");
        b1 = sc.nextDouble();
        System.out.println("Введіть початкове наближення");
        approx = sc.nextDouble();
        System.out.println();

        System.out.println("Метод бісекцій:  ");
        System.out.println("Корінь на інтервалі: " + "[" + a1 + ", " + b1 + "]: " + bisection(a1, b1, eps));

        System.out.println();
        System.out.println("Метод простої ітерації:  ");
        System.out.println("Корінь на інтервалі: " + "[" + a1 + ", " + b1 + "]" + " з початковим наближенням " + approx + " " + simpleIteration(a1, b1, approx, eps));


        System.out.println();
        System.out.println("Метод хорд:  ");
        System.out.println("Корінь на інтервалі: " + "[" + a1 + ", " + b1 + "]: " + chord(a1, b1, eps));

        System.out.println();
        System.out.println("Метод Ньютона: ");
        System.out.println("Корінь на інтервалі: " + "[" + a1 + ", " + b1 + "]" + " з початковим наближенням " + approx + " " + newton(a1, b1, approx, eps));

    }

    public double bisection(double a, double b, double eps) {

        /*double j = f(a) * f(b);
        System.out.println(j);*/
        if (f(a) * f(b) > 0) {

            throw new IllegalArgumentException("Інтервал не містить корню");
        }

        double c = (a + b) / 2;
        int count = 0;

        while (Math.abs(f(c)) > eps) {

            if ((f(c) * f(b)) < 0) {

                a = c;

            } else {

                b = c;
            }

            c = (a + b) / 2;
            count++;
        }

        System.out.println("Кількість ітерацій: " + count);
        return c;
    }

    public double simpleIteration(double a, double b, double x0, double eps) {

        if (f(a) * f(b) > 0) {

            throw new IllegalArgumentException("Інтервал не містить корню");
        }

        if (x0 < a || x0 > b) {

            throw new IllegalArgumentException("Початкове наближення не знаходиться в заданому інтервалі");
        }

        double x = x0;
        double gx = g(x);
        int count = 0;

        while (Math.abs(gx - x) > eps) {

            x = gx;
            gx = g(x);

            count++;

        }

        System.out.println("Кількість ітерацій: " + count);

        return gx;
    }


    public double chord(double a, double b, double eps) {

        if (f(a) * f(b) > 0) {

            throw new IllegalArgumentException("Інтервал не містить корню");
        }

        int count = 0;

        while (Math.abs(f(b)) > eps) {

            a = b - (b - a) * f(b) / (f(b) - f(a));
            b = a - (a - b) * f(a) / (f(a) - f(b));

            count++;
        }

        System.out.println("Кількість ітерацій: " + count);
        return b;

    }

    public double newton(double a, double b, double x0, double eps) {

        if (f(a) * f(b) > 0) {

            throw new IllegalArgumentException("Інтервал не містить корню");
        }

        if (x0 < a || x0 > b) {

            throw new IllegalArgumentException("Початкове наближення не знаходиться в заданому інтервалі");
        }

        double fx0 = f(x0);
        double dfx0 = df(x0);
        double x = x0 - fx0 / dfx0;
        double fx = f(x);

        int count = 0;

        while (Math.abs(fx) > eps && x > a && x < b) {

            x0 = x;
            fx0 = fx;
            dfx0 = df(x0);
            x = x0 - fx0 / dfx0;
            fx = f(x);

            count++;
        }

        System.out.println("Кількість ітерацій: " + count);
        return x;
    }


    public double g(double x) {

        // Метод простой итерации заключается в том, что мы преобразуем исходное уравнение f(x) = 0
        // к виду x = g(x), где функция g(x) является итерационной формулой.
        // Затем мы начинаем с некоторого начального значения x(0) и вычисляем последовательность x(1), x(2), x(3),...
        // по формуле x(i+1) = g(x(i)) до тех пор, пока не достигнем заданной точности.
        return Math.tan(0.47 * x + 0.2) / x;
    }

    public double f(double x) {

        return Math.tan(0.47 * x + 0.2) - x * x;
    }

    public double df(double x) {

        // Производная
        return -2 * x + 0.47 / Math.cos(Math.pow(0.47 * x + 0.2, 2));
    }
}