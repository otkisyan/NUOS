package org.example;

import java.util.Scanner;

/**
 * С клавиатуры вводится последовательность символов, создать две очереди.
 * В первую очередь заносить только числа, во вторую - только символы алфавита.
 * Вывести сумму чисел и количество согласных символов.
 */

public class Main {
    public static void main(String[] args) {

        Main main = new Main();
        main.run();

    }

    public void run() {

        Scanner input = new Scanner(System.in);

        System.out.print("Введіть послідовність символів: ");
        String sequence = input.nextLine();

        ArrayQueue<Integer> numberQueue = new ArrayQueue<>(Integer.class, sequence.length());
        ArrayQueue<Character> alphaQueue = new ArrayQueue<>(Character.class, sequence.length());

        processQueues(sequence, numberQueue, alphaQueue);
        System.out.println("Черга чисел: " + numberQueue.toString());
        System.out.println("Черга символів: " + alphaQueue.toString());

        System.out.println("Сума чисел: " + getSumOfNumbers(numberQueue));
        System.out.println("Кількість приголосних символів: " + getNumberOfConsonants(alphaQueue));
    }

    public void processQueues(String sequence, ArrayQueue<Integer> numberQueue, ArrayQueue<Character> alphaQueue) {

        for (int i = 0; i < sequence.length(); i++) {

            char c = sequence.charAt(i);
            if (Character.isDigit(c)) {

                int value = Character.getNumericValue(c);
                numberQueue.enqueue(value);

            } else if (Character.isAlphabetic(c)) {

                alphaQueue.enqueue(c);

            }
        }

    }

    public int getSumOfNumbers(ArrayQueue<Integer> numberQueue) {

        int sum = 0;

        while (!numberQueue.isEmpty()) {

            sum += numberQueue.dequeue();
        }

        return sum;

    }

    public int getNumberOfConsonants(ArrayQueue<Character> alphaQueue) {

        int consonants = 0;

        while (!alphaQueue.isEmpty()) {

            char c = alphaQueue.dequeue();

            if (isConsonant(c)) {

                consonants++;
            }
        }

        return consonants;
    }

    public boolean isConsonant(char c) {

        return switch (Character.toLowerCase(c)) {

            case 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm',
                    'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z' -> true;

            default -> false;
        };
    }


}