package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();
        main.run();
    }

    public void run() {

        LinkedPriorityQueue<Integer> queue = new LinkedPriorityQueue<>();

        List<Integer> list = new ArrayList<>(Arrays.asList(0, 2, 4, 1, 2, 2, 1));
        System.out.println("Вхідні данні: " + list);

        for (Integer i : list) {

            queue.enqueue(i);
        }

        System.out.println("Черга після додавання елементів за мінімальним пріорітетом:");
        System.out.println(queue.toString());

        Scanner input = new Scanner(System.in);
        int option = 0;

        System.out.println();
        while (option != 11) {

            System.out.println("Оберіть пункт меню");
            System.out.println("1. Додати елемент в чергу");
            System.out.println("2. Зняти елемент з черги");
            System.out.println("3. Вивести всі елементи черги");

            option = input.nextInt();
            input.nextLine();

            switch (option) {

                case 1 -> {

                    System.out.println("Введіть число для додавання в чергу: ");
                    Integer value = input.nextInt();
                    queue.enqueue(value);


                }
                case 2 -> {

                    queue.dequeue();

                }
                case 3 -> {

                    System.out.println(queue.toString());

                }
            }
        }


    }
}