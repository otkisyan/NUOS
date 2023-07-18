package org.example;

/**
 * Из входной последовательности действительных чисел построить бинарное дерево поиска.
 * Определить уровень узла с минимальным элементом.
 *
 * @see <a href="https://github.com/TheAlgorithms/Java/blob/master/src/main/java/com/thealgorithms/datastructures/trees/BSTRecursive.java">GitHub: BSTRecursive - TheAlgorithms</a>
 * @see <a href="https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/">GeeksforGeeks: Binary Search Tree</a>
 * @see <a href="https://www.geeksforgeeks.org/find-the-minimum-element-in-a-binary-search-tree/">GeeksforGeeks: Find the node with minimum value in a Binary Search Tree</a>
 */

public class Tree {

    int value;
    Tree left;
    Tree right;

    public Tree() {

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

    public Tree(int value, Tree left, Tree right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public Tree(int value) {

        this.value = value;
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

    public Tree insert(Tree root, int value) {

        // Если текущий узел null тогда инициализируем его новым узлом
        if (root == null) {

            root = new Tree(value);
        }

        // Если значение текущего узла больше чем значение для вставки,
        // то вставляем в левое поддерево
        if (root.value > value) {

            root.left = insert(root.left, value);

        }

        // Если значение текущего узла меньше чем значение для вставки,
        // то вставляем в правое поддерево
        else if (root.value < value) {

            root.right = insert(root.right, value);

        }

        return root;

    }

    public int getLevelOfMinNode(Tree root) {

        if (root == null) {

            throw new IllegalArgumentException();
        }

        int level = 0;
        Tree current = root;

        while (current.left != null) {

            current = current.left;
            level++;

        }

        return level;

    }

    public boolean search(Tree root, int value) {

        if (root == null) {
            return false;
        } else if (root.value == value) {
            return true;
        } else if (root.value > value) {
            return search(root.left, value);
        } else {
            return search(root.right, value);
        }

    }


}
