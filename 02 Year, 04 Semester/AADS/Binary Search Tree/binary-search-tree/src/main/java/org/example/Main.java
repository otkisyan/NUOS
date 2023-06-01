package org.example;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();
        main.run();

    }

    public void run() {

        String filePath = "userfiles/tree.txt";
        FileProcessor fileProcessor = new FileProcessor();
        Tree tree = fileProcessor.readTree(filePath);

       /* tree.insert(tree,10);
       /* tree.insert(tree,7);
        tree.insert(tree,3);
        tree.insert(tree,8);
        tree.insert(tree,4);
        tree.insert(tree,13);
        tree.insert(tree,12);
        tree.insert(tree,15);*/

        System.out.println("Прямий обхід дерева: ");
        tree.printPreOrder(tree);

        System.out.println();
        System.out.println("Рівень вузла із мінімальним елементом: " + tree.getLevelOfMinNode(tree));
    }
}