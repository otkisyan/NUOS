package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    private static final int TABLE_SIZE1 = 2048;
    private static final int TABLE_SIZE2 = 4096;
    private static final int NUM_TRIALS = 100;
    private static final double[] LOAD_FACTORS = {0.2, 0.4, 0.6, 0.8, 1.0};

    public static void main(String[] args) {

        Main main = new Main();
        main.run1();

        System.out.println();

        main.run2();
        main.run3();

    }


    public void run1() {

        System.out.println("Кількість елементів хеш-таблиці: " + TABLE_SIZE1);
        System.out.println();

        String loremIpsum = "Randomness can be found in many natural and artificial phenomenon. In physics, it appears in the microscopic world of quantum mechanics. In games of chance, randomness dictates the odds of each card that’s drawn. In the world of computing, random number generators are used to develop strong passwords and create engaging simulations. Randomness even influences the way people interact with each other, as we often rely on unpredictable forces like luck and circumstance to make decisions. Ultimately, randomness provides a degree of unpredictability that can be both unsettling and captivating.";
        //loremIpsum = loremIpsum.toLowerCase();

        String[] words = loremIpsum.split("\\s+");

        for (int i = 0; i < words.length; i++) {

            words[i] = words[i].replaceAll("[^\\w]", "");
        }

        for (double loadFactor : LOAD_FACTORS) {

            int numElements = (int) (TABLE_SIZE1 * loadFactor);

            HashTable hashTable = new HashTable(TABLE_SIZE1);
            hashTable = readFile(hashTable, numElements);

            long[] insertTimes = new long[NUM_TRIALS];
            long[] searchTimes = new long[NUM_TRIALS];

            for (int trial = 0; trial < NUM_TRIALS; trial++) {

                long startTime = System.nanoTime();

                for (int i = 0; i < words.length; i++) {

                    hashTable.put(words[i]);
                }

                insertTimes[trial] = System.nanoTime() - startTime;

                startTime = System.nanoTime();

                for (int i = 0; i < words.length; i++) {

                    hashTable.get(words[i]);
                }

                searchTimes[trial] = System.nanoTime() - startTime;
            }

            long avgInsertTime = computeAverage(insertTimes);
            long maxInsertTime = computeMax(insertTimes);
            long minInsertTime = computeMin(insertTimes);
            long avgSearchTime = computeAverage(searchTimes);
            long maxSearchTime = computeMax(searchTimes);
            long minSearchTime = computeMin(searchTimes);

            System.out.printf("Рівень заповнення хеш-таблиці: %.1f%%%n", loadFactor * 100);
            System.out.printf("Час вставки (ns) - Середній: %d, Максимальний: %d, Мінімальний: %d%n", avgInsertTime, maxInsertTime, minInsertTime);
            System.out.printf("Час пошуку (ns) - Середній: %d, Максимальний: %d, Мінімальний: %d%n", avgSearchTime, maxSearchTime, minSearchTime);
            System.out.println();
        }

    }

    public void run2() {

        System.out.println("Кількість елементів хеш-таблиці: " + TABLE_SIZE2);
        System.out.println();

        String loremIpsum = "Randomness can be found in many natural and artificial phenomenon. In physics, it appears in the microscopic world of quantum mechanics. In games of chance, randomness dictates the odds of each card that’s drawn. In the world of computing, random number generators are used to develop strong passwords and create engaging simulations. Randomness even influences the way people interact with each other, as we often rely on unpredictable forces like luck and circumstance to make decisions. Ultimately, randomness provides a degree of unpredictability that can be both unsettling and captivating.";

        String[] words = loremIpsum.split("\\s+");

        for (int i = 0; i < words.length; i++) {

            words[i] = words[i].replaceAll("[^\\w]", "");
        }

        for (double loadFactor : LOAD_FACTORS) {

            int numElements = (int) (TABLE_SIZE2 * loadFactor);

            HashTable hashTable = new HashTable(TABLE_SIZE2);
            hashTable = readFile(hashTable, numElements);

            long[] insertTimes = new long[NUM_TRIALS];
            long[] searchTimes = new long[NUM_TRIALS];

            for (int trial = 0; trial < NUM_TRIALS; trial++) {

                long startTime = System.nanoTime();

                for (int i = 0; i < words.length; i++) {

                    hashTable.put(words[i]);
                }

                insertTimes[trial] = System.nanoTime() - startTime;

                startTime = System.nanoTime();

                for (int i = 0; i < words.length; i++) {

                    hashTable.get(words[i]);
                }

                searchTimes[trial] = System.nanoTime() - startTime;
            }

            long avgInsertTime = computeAverage(insertTimes);
            long maxInsertTime = computeMax(insertTimes);
            long minInsertTime = computeMin(insertTimes);
            long avgSearchTime = computeAverage(searchTimes);
            long maxSearchTime = computeMax(searchTimes);
            long minSearchTime = computeMin(searchTimes);

            System.out.printf("Рівень заповнення хеш-таблиці: %.1f%%%n", loadFactor * 100);
            System.out.printf("Час вставки (ns) - Середній: %d, Максимальний: %d, Мінімальний: %d%n", avgInsertTime, maxInsertTime, minInsertTime);
            System.out.printf("Час пошуку (ns) - Середній: %d, Максимальний: %d, Мінімальний: %d%n", avgSearchTime, maxSearchTime, minSearchTime);
            System.out.println();
        }


    }

    public void run3() {

        System.out.println("20 слів, які найчастіше зустрічаються у вхідному тексті");
        HashTable hashTable = new HashTable(TABLE_SIZE1);
        hashTable = readFile(hashTable, 2000);

        List<HashTable.Entry> top20 = getTop20Words(hashTable);

        for (HashTable.Entry e : top20) {

            System.out.println(e.toString());
        }
    }


    public HashTable readFile(HashTable hashTable, int numElements) {
        try {

            File file = new File("userfiles/words.txt");

            Scanner scanner = new Scanner(file);
            int count = 0;

            while (scanner.hasNext() && count < numElements) {

                String word = scanner.next().replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

                if (!word.isEmpty()) {

                    hashTable.put(word);
                    count++;
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {

            System.out.println("File not found.");
            return null;
        }

        return hashTable;

    }

    public List<HashTable.Entry> getTop20Words(HashTable hashTable) {

        Set<HashTable.Entry> entries = hashTable.getAllEntries();

        return entries.stream()
                .sorted(Comparator.comparingInt(entry -> -entry.getCount()))
                .limit(20)
                .toList();

    }

    private long computeAverage(long[] times) {

        long sum = 0;

        for (long time : times) {

            sum += time;
        }

        return sum / times.length;
    }

    private long computeMax(long[] times) {

        long max = Long.MIN_VALUE;

        for (long time : times) {

            if (time > max) {

                max = time;
            }
        }

        return max;
    }

    private long computeMin(long[] times) {

        long min = Long.MAX_VALUE;

        for (long time : times) {

            if (time < min) {

                min = time;
            }
        }

        return min;
    }

}