package org.example;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * <p>
 * Программа для аутентификации пользователя по характеристикам клавиатурного почерка.
 * </p>
 * Программа должна рассчитывать параметры ввода текста по степени связности набора
 * (вычисляется после исключения грубых ошибок)
 * и сравнивать полученные значения с вашими параметрами (допустимое отклонение составляет 10%).
 * <p>
 * S = \frac{\sqrt(t[j] - M)^{2}}{n - 1}
 * </p>
 * <p>
 * M =\sum_{i = 1}^{i = L}\frac{T[i]}{n}
 * </p>
 * <p>
 * где t[j] - время между набором j и j + 1 символов в слове
 * текстового текста (пробелы исключаются); n - число символов; L - количество слов текста
 * </p>
 * @see <a href="https://rapidtyping.com/ru/appendix.html">RapidType: Приложение</a>
 */
public class Main {

    public static void main(String[] args) {

        Main main = new Main();
        main.run();
    }

    public void run() {

        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.####");

        //String sampleText = "lorem ipsum";
        //String sampleText = "Praesent rutrum dignissim ipsum, eget placerat dolor pulvinar quis. Praesent egestas eleifend ante, quis lobortis leo interdum malesuada";
        String sampleText = "Praesent rutrum dignissim ipsum, eget placerat dolor pulvinar quis quis lobortis leo interdum";
        double baseStandardDeviation = 0.1512;
        double baseErrorRate = 2.2058;

        System.out.println("Базова ступінь зв'язності набору: " + baseStandardDeviation);
        System.out.println("Базовий відсоток помилок: " + baseErrorRate + " %");
        System.out.println("Тестовий текст: " + sampleText);

        System.out.println();
        System.out.print("Почніть вводити тестовий текст: ");

        long startTime = System.currentTimeMillis();
        String typedText = sc.nextLine();
        long endTime = System.currentTimeMillis();

        while (typedText.length() < sampleText.length()) {

            System.out.println("Неправильне введення тексту");
            System.out.print("Почніть вводити тестовий текст: ");
            startTime = System.currentTimeMillis();
            typedText = sc.nextLine();
            endTime = System.currentTimeMillis();
        }

        // Конверт в секунды
        double elapsedTime = (endTime - startTime) / 1000.0;
        System.out.println("Час витрачений на набір тексту: " + elapsedTime + " секунд");

        System.out.println();
        int cpm = calculateCPM(typedText, elapsedTime);
        System.out.println("CPM (Кількість знаків за хвилину): " + cpm);

        double userErrorRate = detectErrors(sampleText, typedText);

        System.out.println("Відсоток помилок: " + df.format(userErrorRate) + " %");

        int n = typedText.length();
        int L = typedText.split("\\s+").length;

        double[] T = calculateT(typedText, cpm);
        System.out.println("Інтервали часу між набором j та j + 1 символів тестового тексту у секундах: ");

        for (double i : T) {

            System.out.print(df.format(i) + " ");
        }
        System.out.println();
        double M = calculateM(T, L, n);

        double userStandardDeviation = calculateS(T, M, n);
        System.out.println("Ступінь зв'язності набору: " + df.format(userStandardDeviation));

        boolean success = auth(userStandardDeviation, baseStandardDeviation, userErrorRate, baseErrorRate);

        System.out.println();

        if (!success) {

            System.out.println("У доступі відмовлено");

        } else {
            System.out.println("Аутентифікація успішна");
        }

    }

    public double detectErrors(String sampleText, String inputText) {

        int errors = 0;

        for (int i = 0; i < sampleText.length(); i++) {

            if (sampleText.charAt(i) != inputText.charAt(i)) {

                errors++;
            }
        }

        // Коэффициент ошибок = количество ошибок * 100 / общее количество символов
        // Например, если у нас есть текст, содержащий 1000 символов, и в нем 10 ошибок,
        // мы можем рассчитать коэффициент ошибок следующим образом:
        // коэффициент ошибок = 10 * 100 / 1000 = 1%
        return (double) errors * 100 / sampleText.length();
    }


    public double calculateTime(int cpm) {

        return (60.0 / cpm);
    }

    public int calculateCPM(String text, double time) {

        // Мы делим на 60,0, потому что хотим перевести время из секунд в минуты,
        // так как CPM (символов в минуту) - это общепринятая единица измерения скорости набора текста.
        // Разделив количество символов на время в минутах, мы получим количество символов,
        // которое пользователь набирал в минуту, что и является определением CPM.
        int chars = text.replaceAll("\\s", "").length();
        return (int) Math.round(chars / (time / 60.0));
    }

    public double[] calculateT(String typedText, int cpm) {

        String cleanedText = typedText.replaceAll("\\s+", "");
        double[] T = new double[cleanedText.length() - 1];

        for (int i = 0; i < cleanedText.length() - 1; i++) {

            T[i] = calculateTime(cpm);

        }

        return T;
    }

    /**
     * M =\sum_{i = 1}^{i = L}\frac{T[i]}{n}
     */
    public double calculateM(double[] T, int L, int n) {

        double M = 0;

        for (int i = 0; i < L; i++) {

            M += T[i];
        }

        M /= n;

        return M;
    }


    /**
     * S = \frac{\sqrt(t[j] - M)^{2}}{n - 1}
     */
    public double calculateS(double[] T, double M, int n) {

        double S = 0;

        for (int i = 0; i < T.length; i++) {

            S += Math.pow(T[i] - M, 2);
        }

        S /= n - 1;

        return Math.sqrt(S);
    }


    public boolean auth(double standardDeviation, double baseStandardDeviation, double errorRate, double baseErrorRate) {

        final double permissiveDeviation = 0.1;

        double deviationUpperBound = baseStandardDeviation + (baseStandardDeviation * permissiveDeviation);
        double deviationLowerBound = baseStandardDeviation - (baseStandardDeviation * permissiveDeviation);

        double errorUpperBound = baseErrorRate + (baseErrorRate * permissiveDeviation);
        double errorLowerBound = baseErrorRate - (baseErrorRate * permissiveDeviation);

        if ((standardDeviation >= deviationLowerBound && standardDeviation <= deviationUpperBound) &&
                (errorRate >= errorLowerBound && errorRate <= errorUpperBound)) {

            return true;

        } else {

            return false;
        }

    }

}

