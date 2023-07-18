package org.example;

import java.math.BigInteger;
import java.util.Random;

/**
 * Создает цифровую подпись на основе алгоритма RSA
 *
 * @see <a href="https://en.wikipedia.org/wiki/RSA_(cryptosystem)">RSA (cryptosystem)</a>
 */
public class RSA {

    final int k = 20;
    final private BigInteger e;
    final private BigInteger d;
    final private BigInteger modulus;

    public BigInteger getE() {

        return e;
    }

    public BigInteger getD() {

        return d;
    }

    public BigInteger getModulus() {

        return modulus;
    }

    public RSA(int bitLength) {

        BigInteger p = generatePrime(bitLength, k);
        BigInteger q = generatePrime(bitLength, k);
        modulus = p.multiply(q);

        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        e = generatePublicExponent(phi);
        d = generateSecretExponent(e, phi);

    }

    public RSA() {

        BigInteger p = new BigInteger("7102828704081046828645100358215692750561494978798475797556032088623921227202137626904917791518806717915144909319564739340076013055388961497464645338000881");
        BigInteger q = new BigInteger("8285642429112398170311063728614985380262846360592485406881543530340434543732717986113887501065718248563072475857358318211745917282454269718074657575147113");
        modulus = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        e = new BigInteger("65537");
        d = new BigInteger("43471642898633412434699571514251038111070744408885417213005140002334455157326895415883314031115772337813219742926927585281268469407515519061651746960752097190400246476429051890905372954794379054806190163203595038579238028351217590131146650723960118814535265328212288327761588011990001107741536419964716054273");

    }

    /**
     * Генерирует открытую экспоненту для открытого ключа
     *
     * @param phi значение функции Эйлера
     * @return открытую экспоненту
     * @see <a href="https://en.wikipedia.org/wiki/Coprime_integers">Coprime integers</a>
     * @see <a href="https://en.wikipedia.org/wiki/Greatest_common_divisor">Greatest common divisor</a>
     */
    public BigInteger generatePublicExponent(BigInteger phi) {

        BigInteger e;

        do {

            e = generatePrime(phi.bitLength(), k);

            // Выбирается целое число e (1 < e < phi(n)), взаимно простое со значением phi(n)
        } while (e.compareTo(BigInteger.ONE) <= 0 || e.compareTo(phi) >= 0 || !gcd(e, phi).equals(BigInteger.ONE));

        return e;
    }

    /**
     * Генерирует секретную экспоненту для закрытого ключа
     *
     * @param e   открытая экспонента
     * @param phi значение функции Эйлера
     * @return секретную экспоненту
     * @see <a href="https://en.wikipedia.org/wiki/Modular_multiplicative_inverse">Modular multiplicative inverse</a>
     * @see <a href="https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm">Extended Euclidean algorithm</a>
     */
    public BigInteger generateSecretExponent(BigInteger e, BigInteger phi) {

        BigInteger mcd = BigInteger.ZERO, x1 = BigInteger.ONE, x2 = BigInteger.ZERO, x3 = phi,
                y1 = BigInteger.ZERO, y2 = BigInteger.ONE, y3 = e, q = BigInteger.ZERO;
        boolean isCorrect = true;

        while (!y3.equals(BigInteger.ONE)) {

            if (y3.equals(BigInteger.ZERO)) {

                mcd = x3;
                isCorrect = false;
                break;
            }

            q = x3.divide(y3);
            BigInteger t1 = x1.subtract(q.multiply(y1));
            BigInteger t2 = x2.subtract(q.multiply(y2));
            BigInteger t3 = x3.subtract(q.multiply(y3));
            x1 = y1;
            x2 = y2;
            x3 = y3;
            y1 = t1;
            y2 = t2;
            y3 = t3;
        }

        mcd = y3;
        BigInteger d = y2;

        if (d.compareTo(BigInteger.ZERO) < 0) {

            d = d.add(phi);
        }

        return d;
    }

    /**
     * Шифрует сообщение используя ранее вычисленные открытую экспоненту и модуль
     *
     * @param message сообщение для шифрования
     * @return зашифрованное сообщение
     */
    public BigInteger encrypt(BigInteger message) {

        return modularPow(message, e, modulus);
    }

    /**
     * Расшифровывает сообщение используя ранее вычисленные секретную экспоненту и модуль
     *
     * @param encryptedMessage зашифрованное сообщение
     * @return расшифрованное сообщение
     */
    public BigInteger decrypt(BigInteger encryptedMessage) {

        return modularPow(encryptedMessage, d, modulus);
    }

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

    /**
     * Находит наибольший общий делитель для двух чисел
     *
     * @param a первое число
     * @param b второе число
     * @return наибольший общий делитель
     * @see <a href="https://en.wikipedia.org/wiki/Greatest_common_divisor">Greatest common divisor</a>
     * @see <a href="https://en.wikipedia.org/wiki/Euclidean_algorithm">Euclidean algorithm</a>
     * @see <a href="https://stackoverflow.com/questions/4009198/java-get-greatest-common-divisor">Stackoverflow: Get greatest common divisor</a>
     */
    public BigInteger gcd(BigInteger a, BigInteger b) {

        if (b.equals(BigInteger.ZERO)) {

            return a;
        }

        return gcd(b, a.mod(b));
    }

}
