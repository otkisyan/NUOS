package org.example;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Main main = new Main();
        main.run();
    }

    public void run() {

        Scanner sc = new Scanner(System.in);

        Tree root = new Tree(1,

                new Tree(2,

                        new Tree(4,

                                null,
                                new Tree(8)),

                        new Tree(5)),

                new Tree(3,

                        new Tree(6,

                                new Tree(9),

                                new Tree(10)),

                        new Tree(7)));


        int value = 0;

        System.out.println("Прямий обхід дерева:");
        root.printPreOrder(root);
        System.out.println();
        System.out.println("Введіть значення для пошуку: ");
        value = sc.nextInt();

        Tree node = root.printPreOrder(root, value);

        System.out.println();

        if (node == null) {

            System.out.println("Вузла зі значенням " + value + " не існує!");
        }
    }
}