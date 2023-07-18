package org.example;

import java.math.BigInteger;
import java.util.Random;

/**
 * Генерирует простые числа с проверкой тестом Миллера-Рабина.
 * <br></br>
 * Реализует алгоритм сведения простого большого числа a в целую степень b по модулю n.
 */
public class MillerRabin {

    /**
     * Проверяет, является ли заданное число простым используя тест Миллера-Рабина.
     * <p>
     * Ввод: n > 2, нечётное натуральное число, которое необходимо проверить на простоту;
     * </p>
     *
     * @param n число, которое нужно проверить на простоту
     * @param k количество раундов
     * @return true, если число n является простым, false в противном случае
     * @see <a href="https://en.wikipedia.org/wiki/Miller%E2%80%93Rabin_primality_test">Miller–Rabin primality test</a>
     */
    public boolean isPrime(BigInteger n, int k) {

        if (n.compareTo(BigInteger.valueOf(2)) == 0 || n.compareTo(BigInteger.valueOf(3)) == 0) {

            return true;
        }

        // У нечетных чисел младший бит = 1, у четных = 0
        if (n.compareTo(BigInteger.valueOf(2)) < 0 || !n.testBit(0)) {

            return false;
        }

        BigInteger d = n.subtract(BigInteger.ONE);
        int s = 0;

        while (d.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {

            d = d.divide(BigInteger.valueOf(2));
            s++;

        }

        for (int i = 0; i < k; i++) {

            BigInteger a = randomInRange(BigInteger.valueOf(2), n.subtract(BigInteger.valueOf(2)));
            BigInteger x = modularPow(a, d, n);

            if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) {

                continue;
            }

            for (int j = 0; j < s - 1; j++) {

                x = modularPow(x, BigInteger.valueOf(2), n);

                if (x.equals(BigInteger.ONE)) {

                    return false;
                }

                if (x.equals(n.subtract(BigInteger.ONE))) {

                    break;
                }

            }

            if (!x.equals(n.subtract(BigInteger.ONE))) {

                return false;
            }

        }

        return true;
    }

    /**
     * Возводит число <code>a</code> в степени <code>b</code> по модулю <code>n</code>
     *
     * @param a основание
     * @param b показатель
     * @param n модуль
     * @return результат возведения в степень по модулю
     * @see <a href="https://en.wikipedia.org/wiki/Exponentiation_by_squaring">Exponentiation by squaring</a>
     * @see <a href="https://en.wikipedia.org/wiki/Modular_exponentiation">Modular exponentiation</a>
     * @see <a href="https://en.wikipedia.org/wiki/Sign_bit">Sign bit</a>
     * @see <a href="https://en.wikipedia.org/wiki/Bit_numbering">Bit numbering</a>
     */
    public BigInteger modularPow(BigInteger a, BigInteger b, BigInteger n) {

        BigInteger d = BigInteger.ONE;

        for (int i = b.bitLength() - 1; i >= 0; i--) {

            d = d.multiply(d).mod(n);

            if (b.testBit(i)) {

                d = d.multiply(a).mod(n);
            }
        }

        return d;
    }

    /**
     * Генерирует простое число типа BigInteger заданной битовой длины с использованием теста Миллера-Рабина.
     *
     * @param bitLength длина в битах генерируемого простого числа
     * @param k         количество раундов, используемых в тесте Миллера-Рабина
     * @return простое число типа BigInteger заданной длины
     */
    public BigInteger generatePrime(int bitLength, int k) {

        Random random = new Random();
        BigInteger prime = new BigInteger(bitLength, random);

        while (!isPrime(prime, k)) {

            prime = new BigInteger(bitLength, random);
        }

        return prime;
    }

    /**
     * Генерирует случайное число <code>BigInteger</code> в диапазоне
     * между заданными значениями <code>min</code> и <code>max</code> включительно.
     *
     * @param min минимальное значение диапазона, в котором должно находиться случайное число.
     * @param max максимальное значение диапазона, в котором должно находиться случайное число.
     * @return случайное число <code>BigInteger</code> в диапазоне между заданными значениями min и max включительно.
     * @throws IllegalArgumentException если min > max
     * @see <a href="https://stackoverflow.com/questions/2290057/how-to-generate-a-random-biginteger-value-in-java">How to generate a random BigInteger value in Java?</a>
     */
    public BigInteger randomInRange(BigInteger min, BigInteger max) {

        if (min.compareTo(max) > 0) {

            throw new IllegalArgumentException("min должно быть меньше или равно max");
        }

        Random random = new Random();

        /*

        // Например, допустим, min равен 100, а max - 105,
        // размер диапазона между ними равен 105 - 100 = 5.
        // Однако, поскольку мы хотим включить в диапазон и 100, и 105,
        // нам нужно добавить 1, чтобы получить размер диапазона 6.
        BigInteger range = max.subtract(min).add(BigInteger.ONE);
        BigInteger result;

        do {

            result = new BigInteger(range.bitLength(), random);

        } while (result.compareTo(range) >= 0);

        return result.add(min);
        */

        BigInteger result;

        do {

            result = new BigInteger(max.bitLength(), random);

        } while (result.compareTo(min) < 0 || result.compareTo(max) > 0);

        return result;
    }
}
