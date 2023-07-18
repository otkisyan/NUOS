package org.example;

import java.math.BigInteger;
import java.util.ArrayList;

public class SieveOfEratosthenes {

    /**
     * Решето Эратосфена — алгоритм нахождения всех простых чисел до некоторого целого числа n
     *
     * @param n диапазон простых чисел (до)
     * @return ArrayList простых чисел от 3 до n
     * @see <a href="https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes">Sieve of Eratosthenes</a>
     */
    public static ArrayList<BigInteger> sieveOfEratosthenes(int n) {

        ArrayList<BigInteger> primes = new ArrayList<>();

        // Так как в массиве индексы начинаются с 0, то нам нужно выделить память для n + 1 элементов (от 0 до n включительно).
        // То есть, мы выделяем память на один элемент больше, чем заданный диапазон поиска простых чисел.
        // Если бы мы выделили память только для n элементов, то мы могли бы потерять информацию о простоте самого числа n
        boolean[] isPrime = new boolean[n + 1];

        for (int i = 2; i <= n; i++) {

            isPrime[i] = true;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {

            if (isPrime[i]) {

                for (int j = i * i; j <= n; j += i) {

                    isPrime[j] = false;
                }
            }
        }

        for (int i = 3; i <= n; i++) {

            if (isPrime[i]) {

                primes.add(BigInteger.valueOf(i));
            }
        }

        return primes;
    }
}
