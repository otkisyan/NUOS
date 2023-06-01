package org.example;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

/**
 * Линейный подход к вычислению последовательности проб
 * на базе хеш-функции умножения при m=3083 и 4096; A=0.618.
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

    public int hash(String word) {

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

        int hashCode = Math.abs(word.hashCode()); // получаем хэш-код строки и берем его абсолютное значение

        // h(k) = [m⋅(k⋅A - [k⋅A])], где () - скобки приоритета, [] - скобки взятия целой части
        return (int) (tableSize * (hashCode * A - (int) (hashCode * A)));
    }

    public int linearProbing(String word, int i) {

        // h(k, i) = (h'(k) + i) mod m.
        // h' - обычная хэш функция
        return (hash(word) + i) % tableSize;
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
            index = linearProbing(word, i);
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
            index = linearProbing(word, i);

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
