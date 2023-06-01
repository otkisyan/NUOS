package org.example;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

/**
 * Двойное хеширование для вычисления последовательности проб на базе
 * хеш-функции деления с остатком при m=2048 и 4096; в качестве
 * h1 использовать хеш-функцию умножения (A=0.618), в качестве h2 использовать функцию h2(k)=((k mod 3083) div 2)-2+1, где
 * (x div y) - целая часть от деления x на y.
 */
public class HashTable {

    public static class Entry {

        String word;
        int count;

        public String getWord() {

            return word;
        }

        public void setWord(String word) {

            this.word = word;
        }

        public int getCount() {

            return count;
        }

        public void setCount(int count) {

            this.count = count;
        }

        @Override
        public boolean equals(Object o) {

            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return count == entry.count && Objects.equals(word, entry.word);
        }

        @Override
        public int hashCode() {

            return Objects.hash(word, count);
        }

        @Override
        public String toString() {

            return "Entry{" +
                    "word='" + word + '\'' +
                    ", count=" + count +
                    '}';
        }

        public Entry(String word, int count) {

            this.word = word;
            this.count = count;
        }

    }

    Entry[] table;
    int tableSize;

    public HashTable(int size) {

        table = new Entry[size];
        tableSize = size;
    }

    public int h1(String word) {

        double A = 0.618;

        // Для строк метод hashCode() реализован таким образом,
        // что он генерирует одинаковый хэш-код для двух строковых объектов,
        // если они содержат одинаковую последовательность символов.
        // Это означает, что если у вас есть два объекта String с одинаковым содержимым, их хэш-коды будут одинаковыми.
        //
        // Возвращает хэш-код для данной строки. Хэш-код для объекта String вычисляется как:
        // s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
        // используя арифметику int, где s[i] - это второй символ строки,
        // n - длина строки, а ^ означает экспоненцию. (Хэш-значение пустой строки равно нулю).

        return (int) (tableSize * (Math.abs(word.hashCode()) * A - (int) (Math.abs(word.hashCode()) * A)));
    }

    public int h2(String word) {

        return ((Math.abs(word.hashCode()) % 3083) / 2) - 2 + 1;
    }

    public int doubleHash(int h1, int h2, int i) {

        return (h1 + i * h2) % tableSize;

    }

    public int hash(String word) {

        return Math.abs(word.hashCode() % tableSize);
    }

    public void put(String word) {

        int i = 0;
        int index = hash(word);

        while (table[index] != null) {

            if (table[index].word.equals(word)) {

                table[index].count++;
                return;

            }

            i++;
            index = doubleHash(h1(word), h2(word), i);
        }

        Entry newEntry = new Entry(word, 0);

        table[index] = newEntry;

    }

    public Entry get(String word) {

        int index = hash(word);
        int i = 0;

        while (table[index] != null) {

            if (table[index].word.equals(word)) {

                return table[index];
            }

            i++;
            index = doubleHash(h1(word), h2(word), i);

        }

        throw new NoSuchElementException();

    }

    public Set<Entry> getAllEntries() {

        Set<Entry> entries = new HashSet<>();

        for (int i = 0; i < tableSize; i++) {

            Entry entry = table[i];

            if (entry != null) {

                entries.add(entry);

            }
        }

        return entries;
    }


}
