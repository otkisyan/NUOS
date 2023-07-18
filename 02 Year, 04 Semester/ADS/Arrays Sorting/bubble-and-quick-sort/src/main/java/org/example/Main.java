package org.example;

import java.util.Arrays;
import java.util.Random;

/**
 * Пузырьковая и быстрая сортировка.
 * Элементы массива заполняются случайными числами.
 * Выполняется для массивов размером 100, 1000, 10000 элементов.
 * Измеряется время выполнения операции сортировки для каждого случая.
 */
public class Main {

    private static final int[] LOAD = {10, 1000, 10000};

    public static void main(String[] args) {

        Main main = new Main();
        main.run();
        main.example();
    }

    public void run() {

        System.out.println("Бульбашкове сортування");
        System.out.println();

        for (int num : LOAD) {

            int[] array = new int[num];
            array = fillArray(array);

            long startTime = System.nanoTime();
            array = bubbleSort(array);
            //System.out.println(Arrays.toString(array));
            long endTime = System.nanoTime();

            long elapsedTime = (endTime - startTime) / 1000;

            System.out.println("Кількість елементів масиву: " + num);
            System.out.println("Час виконання сортування (microseconds): " + elapsedTime);
            System.out.println();
        }


        System.out.println();
        System.out.println("Швидке сортування");
        System.out.println();

        for (int num : LOAD) {

            int[] array = new int[num];
            array = fillArray(array);

            long startTime = System.nanoTime();
            array = quickSort(array);
            //System.out.println(Arrays.toString(array));
            long endTime = System.nanoTime();

            long elapsedTime = (endTime - startTime) / 1000;


            System.out.println("Кількість елементів масиву: " + num);
            System.out.println("Час виконання сортування (microseconds): " + elapsedTime);
            System.out.println();
        }

    }

    public void example() {

        System.out.println("Приклад сортування: ");
        System.out.println();
        int n = 20;
        int[] array = new int[n];
        array = fillArray(array);

        System.out.println("Масив до сортування: ");
        System.out.println(Arrays.toString(array));

        System.out.println("Масив після бульбашкового сортування: ");
        int[] bubbleSorted = bubbleSort(array);
        System.out.println(Arrays.toString(bubbleSorted));

        System.out.println("Масив після швидкого сортування: ");
        int[] quickSorted = quickSort(array);
        System.out.println(Arrays.toString(bubbleSorted));


    }


    public int[] fillArray(int[] array) {

        Random random = new Random();

        for (int i = 0; i < array.length; i++) {

            array[i] = random.nextInt(0, 10000);
        }

        return array;
    }

    public int[] bubbleSort(int[] array) {

    /*    int n = array.length;

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {

                if (array[j] > array[j + 1]) {

                    // меняем местами arr[j] и arr[j + 1]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }*/

        boolean isSorted = false;

        while (!isSorted) {

            isSorted = true;
            for (int i = 0; i < array.length - 1; i++) {

                if (array[i] > array[i + 1]) {

                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;

                    isSorted = false;
                }
            }
        }

        return array;
    }

    public int[] quickSort(int[] array) {

        doSort(array, 0, array.length - 1);
        return array;
    }

    private void doSort(int[] array, int left, int right) {

        if (left < right) {

            int pivot = array[(left + right) / 2];
            int index = partition(array, left, right, pivot);

            doSort(array, left, index - 1);
            doSort(array, index, right);
        }
    }

    private int partition(int[] array, int left, int right, int pivot) {

        while (left <= right) {

            // Ищется пара элементов, один из которых в левой части больше опорного,
            // а другой в правой части меньше опорного и меняются местами между собой
            while (array[left] < pivot) {

                left++;
            }

            while (array[right] > pivot) {

                right--;
            }

            if (left <= right) {

                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;

                left++;
                right--;
            }
        }

        return left;
    }
}