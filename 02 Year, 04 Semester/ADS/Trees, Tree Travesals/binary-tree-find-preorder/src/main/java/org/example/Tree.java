package org.example;

/**
 * В заданном непустом бинарном дереве найти первое вхождение
 * заданного элемента и напечатать пройденные при поиске узлы дерева
 * при прямом обходе дерева (рекурсивно).
 */

/*
 *                                                  1
 *                                                 / \
 *                                                2   3
 *                                               / \  / \
 *                                               4  5 6   7
 *                                               \   / \
 *                                                8  9  10
 */

public class Tree {

    int value;
    Tree left;
    Tree right;

    public Tree(int value, Tree left, Tree right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Tree(int value) {

        this.value = value;
    }

    public Tree getLeft() {
        return left;
    }

    public void setLeft(Tree left) {
        this.left = left;
    }

    public Tree getRight() {
        return right;
    }

    public void setRight(Tree right) {
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    /*
     * Обход дерева сверху вниз (в прямом порядке - Pre Order): A, B, C — префиксная форма.
     * Прямой порядок обхода заключается в том, что корень дерева посещается раньше, чем его поддеревья.
     */

    void printPreOrder(Tree tree) {

        if (tree == null) {

            return;
        }

        System.out.print(tree.value + " ");

        printPreOrder(tree.left);

        printPreOrder(tree.right);
    }

    public Tree printPreOrder(Tree tree, int value) {

        if (tree == null) {

            return null;
        }

        if (tree.value == value) {

            System.out.print(tree.value + " ");
            return tree;
        }

        System.out.print(tree.value + " ");

        Tree left = printPreOrder(tree.left, value);

        if (left != null) {

            return left;
        }

        Tree right = printPreOrder(tree.right, value);

        if (right != null) {

            return right;
        }

        return null;
    }

  /*  public void findAndPrintPath(Tree root, int value) {

        List<Integer> path = new ArrayList<>();

        boolean found = find(root, value, path);

        if (found) {

            System.out.print("Пройдені під час пошуку вузла зі значенням " + value + " вузли дерева: ");

            for (int i = 0; i < path.size(); i++) {

                System.out.print(path.get(i) + " ");
            }


        } else {

            System.out.println("Вузел зі значенням " + value + " не знайдений у дереві");
        }
    }

    private boolean find(Tree node, int value, List<Integer> path) {

        if (node == null) {

            return false;
        }

        path.add(node.value);

        if (node.value == value) {

            return true;
        }

        boolean foundInLeft = find(node.left, value, path);

        if (foundInLeft) {

            return true;
        }

        boolean foundInRight = find(node.right, value, path);

        if (foundInRight) {

            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }
*/
}
