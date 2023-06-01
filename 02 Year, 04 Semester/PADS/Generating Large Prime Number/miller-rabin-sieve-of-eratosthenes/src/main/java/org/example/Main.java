package org.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Main main = new Main();
        main.run();
    }

    public void run() {

        MillerRabin millerRabin = new MillerRabin();

        BigInteger a = generatePrime(16);
        BigInteger b = generatePrime(8);
        BigInteger n = generatePrime(8);
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("n: " + n);
        System.out.println("Зведення простого великого числа a в цілу ступінь b по модулю n: " + millerRabin.modularPow(a, b, n));
    }

    /**
     * 1. Сгенерировать случайное n-битное число а.
     * <br>
     * 2. Установить его старший и младший биты равными 1.
     * <br>
     * Старший бит будет гарантировать необходимую длину искомого числа,
     * а младший бит обеспечивает его нечетность.
     * <br>
     * 3. Убедиться, что а не делится на небольшие простые числа: 3, 5, 7, 11 и т.д.
     * <br>
     * Наиболее эффективной является проверка на делимость для всех простых чисел, меньших 2000.
     * <br>
     * 4. Выполнить тест Миллера-Рабина минимум 3 раза.
     * <br>
     * 5. Если а не прошло хотя бы одну проверку из п. 3 или 4, оно не является простым.
     * <br>
     * В таком случае надо изменить исходное число на -2 или на +2.
     * <br>
     * Повторить п. 3, 4. Выполнить п. 5, пока не будет найдено простое число.
     *
     * @param numBits длина в битах генерируемого простого числа
     * @return простое число BigInteger заданной длины
     */
    public BigInteger generatePrime(int numBits) {

        MillerRabin millerRabin = new MillerRabin();
        Random random = new Random();
        int n = 3;
        BigInteger a = new BigInteger(numBits, random);
        a = a.setBit(0);
        a = a.setBit(numBits - 1);

        ArrayList<BigInteger> primes = SieveOfEratosthenes.sieveOfEratosthenes(2000);

        boolean divided = false;
        boolean prime = false;

        do {

            for (BigInteger v : primes) {

                if (a.mod(v).equals(BigInteger.ZERO)) {

                    divided = true;

                } else {

                    divided = false;
                }
            }

            if (!millerRabin.isPrime(a, n) || divided) {

                prime = false;
                a = a.add(BigInteger.TWO);

            } else {

                prime = true;
            }

        } while (!prime);

        return a;
    }

}