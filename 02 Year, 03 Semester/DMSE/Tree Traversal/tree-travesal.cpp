// https://acm.bsu.by/wiki/%D0%91%D0%B8%D0%BD%D0%B0%D1%80%D0%BD%D1%8B%D0%B5_%D0%BF%D0%BE%D0%B8%D1%81%D0%BA%D0%BE%D0%B2%D1%8B%D0%B5_%D0%B4%D0%B5%D1%80%D0%B5%D0%B2%D1%8C%D1%8F#:~:text=%D0%9F%D1%80%D1%8F%D0%BC%D0%BE%D0%B9%20%D0%BF%D0%BE%D1%80%D1%8F%D0%B4%D0%BE%D0%BA%20%D0%BE%D0%B1%D1%85%D0%BE%D0%B4%D0%B0%20(%D1%81%D0%B2%D0%B5%D1%80%D1%85%D1%83%20%D0%B2%D0%BD%D0%B8%D0%B7,%D0%BF%D1%80%D1%8F%D0%BC%D1%8B%D0%BC%20%D0%BB%D0%B5%D0%B2%D1%8B%D0%BC%20(%D0%BF%D1%80%D0%B0%D0%B2%D1%8B%D0%BC)%20%D0%BE%D0%B1%D1%85%D0%BE%D0%B4%D0%BE%D0%BC.
// https://prog-cpp.ru/data-tree/
// http://hci.fenster.name/304y/lab3/
// https://learnc.info/adt/binary_tree_traversal.html
// https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/

/*
 * Разработать программы обхода вершин дерева:
 * - в прямом порядке;
 * - внутреннем порядке;
 * - обратном порядке.
 */

#include <iostream>

using namespace std;

/*
 * Структура для представления узла
 * Узел дерева имеет значение, указатель на левый дочерний узел и правый дочерний узел
 *
 * Для объявления структуры компилятор должен заранее знать размер всех полей структуры.
 * В таких случаях члены left и right объявляются указателями,
 * так как размер указателя известен заранее, и не зависит от указуемого типа.
 * Мы не можем добавить в структуру член типа, который еще полностью не определен
*/

struct Node {

    int value;
    struct Node *left;
    struct Node *right;
};

// Функция для создания нового узла дерева
Node *newNode(int value) {

    Node *temp = new Node;
    temp->value = value;
    temp->left = nullptr;
    temp->right = nullptr;

    return temp;
}

/*
 * Обход дерева сверху вниз (в прямом порядке - Pre Order): A, B, C — префиксная форма.
 * Прямой порядок обхода заключается в том, что корень дерева посещается раньше, чем его поддеревья.
*/

void printPreOrder(struct Node *node) {

    if (node == nullptr) { // Пока не встретится пустой узел

        return;
    }

    // Вывожу значение узла (корень дерева)
    cout << node->value << " ";

    // Рекурсия для левого поддерева
    printPreOrder(node->left);

    // Рекурсия для правого поддерева
    printPreOrder(node->right);
}

/*
 * Обход дерева слева направо (во внутреннем порядке - In Order): B, A, C — инфиксная форма.
 * Внутренний порядок обхода заключается в том, что корень посещается после посещения одного из его поддеревьев.
*/

void printInOrder(struct Node *node) {

    if (node == nullptr) {

        return;
    }

    // Рекурсия для левого поддерева
    printInOrder(node->left);

    // Вывожу значение узла (корень дерева)
    cout << node->value << " ";

    // Рекурсия для правого поддерева
    printInOrder(node->right);
}

/*
 * Обход дерева в снизу вверх (в обратном порядке - Post Order): B, C, A — постфиксная форма.
 * Обратный порядок обхода заключается в том, что корень дерева посещается после его поддеревьев.
 */

void printPostOrder(struct Node *node) {

    if (node == nullptr) {

        return;
    }

    // Рекурсия для левого поддерева
    printPostOrder(node->left);

    // Рекурсия для правого поддерева
    printPostOrder(node->right);

    // Вывожу значение узла (корень дерева)
    cout << node->value << " ";
}


int main() {

    struct Node *root = newNode(1);

    root->left = newNode(2);
    root->left->right = newNode(5);
    root->left->left = newNode(4);
    root->left->left->right = newNode(8);
    root->right = newNode(3);
    root->right->right = newNode(7);
    root->right->left = newNode(6);
    root->right->left->left = newNode(9);
    root->right->left->right = newNode(10);

    cout << "Обход в прямом порядке:" << endl;
    printPreOrder(root);
    cout << endl;

    cout << "Обход во внутреннем порядке:" << endl;
    printInOrder(root);
    cout << endl;

    cout << "Обход в обратном порядке" << endl;
    printPostOrder(root);
    cout << endl;

    return 0;
}
