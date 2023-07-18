/*
 * 1. В главном классе описать метод, который вычисляет значение функции, которая задана в
 * таблице и в тестовом классе - тестовые методы для него.
 *
 * 2. Разработать метод, который по указанным значениям шага, начала и конца интервала
 * вычисляет количество шагов для табулирования и тестовые методы для него.
 *
 * 3. Создать методы, которые создают массивы значений функции (y) и ее аргумента (x) в
 * всех точках указанного интервала с заданным шагом (размер массивов вычислить программно с помощью метода из п.2).
 *
 * 4. Создать методы, которые после формирования массивов, находят номера наибольшего и наименьшего элементов массива значений функции,
 * и методы, которые вычисляют и сумму и среднее арифметическое элементов массива значений функции.
 *
 * 5. Создать методы вывода наибольшего и наименьшего элементов массива значений функции, указав их номера и соответствующие значения аргумента.

 */

import static java.lang.Math.*;

public class Main {

    public static void main(String[] args) {

        Main prog = new Main();
        prog.run();
    }

    public double tabulation(double x, double a) {

        double eps = 0.0001;

        if (x > 1.2 - eps) {

            return log10(x + 1);

        } else {

            //y = pow(sin(sqrt(a * x)), 2);
            return (sin(sqrt(a * x))) * (sin(sqrt(a * x)));

        }

    }

    public int calculateSteps(double x1, int x2, double step) {

        return (int) ((x2 - x1) / step) + 1;
    }

    public double[] yValuesArrayCreate(double x1, int x2, double step) {

        return new double[calculateSteps(x1, x2, step)];
    }

    public double[] xValuesArrayCreate(double x1, int x2, double step) {

        return new double[calculateSteps(x1, x2, step)];
    }


    public double[] yValuesArrayFill(double[] xValues, double a) {

        double[] yValues = new double[xValues.length];

        for (int i = 0; i < yValues.length; i++) {

            yValues[i] = tabulation(xValues[i], a);

        }

        return yValues;

    }

    public double[] xValuesArrayFill(double x1, int x2, double step) {

        double[] xValues = xValuesArrayCreate(x1, x2, step);

        for (int i = 0; i < xValues.length; i++) {

            xValues[i] = x1 + i * step;
        }

        return xValues;
    }

    public int getMinIndex(double[] yValues) {

        int minIndex = 0;

        for (int i = 0; i < yValues.length; i++) {

            if (yValues[i] < yValues[minIndex]) {

                minIndex = i;
            }

        }

        return minIndex;
    }

    public double getMinElement(double[] yValues) {

        int minNumber = getMinIndex(yValues);

        return yValues[minNumber];
    }

    public double getMinElementArgument(double[] yValues, double[] xValues) {

        int minNumber = getMinIndex(yValues);

        return xValues[minNumber];
    }

    public int getMaxIndex(double[] yValues) {

        int maxIndex = 0;

        for (int i = 0; i < yValues.length; i++) {

            if (yValues[i] > yValues[maxIndex]) {

                maxIndex = i;
            }
        }

        return maxIndex;
    }

    public double getMaxElement(double[] yValues) {

        int maxNumber = getMaxIndex(yValues);

        return yValues[maxNumber];
    }

    public double getMaxElementArgument(double[] yValues, double[] xValues) {

        int maxNumber = getMaxIndex(yValues);

        return xValues[maxNumber];
    }

    public double getSum(double[] yValues) {

        double sum = 0;
        for (int i = 0; i < yValues.length; i++) {
            sum = sum + yValues[i];
        }

        return sum;
    }

    public double getAverage(double[] yValues) {

        double sum = getSum(yValues);
        double average = 0;

        average = sum / yValues.length;

        return average;

    }

    public void run() {

        double a = 20.3;
        double x1 = 0.5;
        int x2 = 2;
        double step = 0.005;

        double[] xValues = xValuesArrayCreate(x1, x2, step);
        double[] yValues = yValuesArrayCreate(x1, x2, step);

        xValues = xValuesArrayFill(x1, x2, step);
        yValues = yValuesArrayFill(xValues, a);

        System.out.println("Найбільший елемент масиву значень функції: " + getMaxElement(yValues));
        System.out.println("Аргумент найбільшого елементу масиву значень функції: " + getMaxElementArgument(yValues, xValues));
        System.out.println("Індекс найбільшого елементу масиву значень функції: " + getMaxIndex(yValues));
        System.out.println("\n");

        System.out.println("Найменший елемент масиву значень функції: " + getMinElement(yValues));
        System.out.println("Аргумент найменшого елементу масиву значень функції: " + getMinElementArgument(yValues, xValues));
        System.out.println("Індекс найменшого елементу масиву значень функції: " + getMinIndex(yValues));
        System.out.println("\n");

        System.out.println("Сумма елементів массиву значень функції: " + getSum(yValues));
        System.out.println("Середнє арифметичне елементів значень функції: " + getAverage(yValues));
        System.out.println("\n");

    }
}


