package org.example;

public class IntegralCalculator {

    /**
     * @param a левый конец интервала
     * @param b правый конец интервала
     * @param n количество шагов
     * @return значение интеграла
     */
    public double calculate(double a, double b, int n) {

        // h представляет собой ширину каждого подинтервала
        double h = (b - a) / n;
        double sum = 0.0;

        double integral = 0.0;

        for (int i = 1; i < n; i++) {

            double x = a + i * h;

            sum += Func.calculate(x);

        }

        integral = (Func.calculate(a) + Func.calculate(b)) / 2.0 + sum;
        integral *= h;

        return integral;
    }


    /**
     * @param a          левый конец интервала
     * @param b          правый конец интервала
     * @param n          количество шагов
     * @param numThreads количество потоков исполнения
     * @return значение интеграла
     */
    public double parallelCalculate(double a, double b, int n, int numThreads) {

        double h = (b - a) / n;
        double sum = 0.0;
        double integral = 0.0;

        int chunk = n / numThreads;

        double[] partialSums = new double[numThreads];
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {

            final int threadIndex = i;

            threads[i] = new Thread(() -> {

                int start = threadIndex * chunk + 1;
                int end = start + chunk;

                if (threadIndex == numThreads - 1) {

                    // Последний поток берет на себя все оставшиеся интервалы
                    end = n;
                }

                double partialSum = 0.0;

                for (int j = start; j < end; j++) {

                    double x = a + j * h;
                    partialSum += Func.calculate(x);
                }

                partialSums[threadIndex] = partialSum;

            });

            threads[i].start();
        }

        for (Thread thread : threads) {

            try {

                thread.join();

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }

        for (double partialSum : partialSums) {

            sum += partialSum;
        }

        integral = (Func.calculate(a) + Func.calculate(b)) / 2.0 + sum;
        integral *= h;

        return integral;

    }
}
