package org.example;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Main main = new Main();
        //main.run();
        main.run2();
    }

    public void run() {

        IntegralCalculator integralCalculator = new IntegralCalculator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть кількість інтервалів розбиття: ");
        int n = scanner.nextInt();

        System.out.println("Введіть кількість потоків виконання: ");
        int numThreads = scanner.nextInt();

        final double a = 0.0;
        final double b = Math.PI / 3.0;

        long start = System.currentTimeMillis();
        double result = integralCalculator.parallelCalculate(a, b, n, numThreads);
        long end = System.currentTimeMillis();

        long time = end - start;
        System.out.println("Час виконання: " + time + " ms");

        System.out.println("Обчислене значення інтегралу: " + result);

    }

    public void run2() {

        IntegralCalculator integralCalculator = new IntegralCalculator();

        int numThreads = 20;
        int n1 = 900;
        int n2 = 1100000;

        final double a = 0.0;
        final double b = Math.PI / 3.0;

        long start;
        long end;
        long time;
        double result;

        System.out.println("Кількість інтервалів розбиття відрізка: " + n1);
        for (int i = 1; i <= numThreads; i++) {

            System.out.println();

            start = System.nanoTime();
            result = integralCalculator.parallelCalculate(a, b, n1, i);
            end = System.nanoTime();

            time = end - start;
            System.out.println("Час виконання: " + time + " ns");

            System.out.println("Обчислене значення інтегралу: " + result);
            System.out.println("Кількість потоків виконання: " + i);

        }

        System.out.println();
        System.out.println();
        System.out.println("Кількість інтервалів розбиття відрізка: " + n2);
        for (int i = 1; i <= numThreads; i++) {

            System.out.println();

            start = System.nanoTime();
            result = integralCalculator.parallelCalculate(a, b, n2, i);
            end = System.nanoTime();

            time = end - start;
            System.out.println("Час виконання: " + time + " ns");

            System.out.println("Обчислене значення інтегралу: " + result);
            System.out.println("Кількість потоків виконання: " + i);

        }


    }
}