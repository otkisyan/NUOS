package org.example;

/**
 * Алгоритм Прима. Представление графа - матрица смежности.
 *
 * @see <a href="https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/">GeeksforGeeks: Prim’s Algorithm for Minimum Spanning Tree</a>
 * @see <a href="https://habr.com/ru/post/569444">Habr: Алгоритм Краскала, Прима для нахождения минимального остовного дерева</a>
 */
public class PrimMST {

    // Необходимо построить такое дерево, которые бы с одной стороны включало все вершины графа,
    // а с другой стороны, чтобы ребра были такие, чтобы сумма стоимости (веса) была минимальная,
    // то есть сеть минимального веса, связывающую все вершины графа

    private final int V;

    // Массив для хранения MST
    int[] parent;

    // Массив для хранения ключей
    int[] key;

    // Массив для представления посещенных вершин
    boolean[] visited;

    public PrimMST(int v) {

        V = v;
        parent = new int[V];
        key = new int[V];
        visited = new boolean[V];
    }

    /**
     * Находит индекс вершины с минимальным значением ключа в массиве key,
     * при условии, что вершина еще не была посещена <code>(visited == false)</code>
     *
     * <p>
     * В контексте алгоритма Прима, ключ - это минимальный вес ребра,
     * которое соединяет вершину с минимальным остовным деревом (MST), построенным на данный момент.
     * Другими словами, если мы уже включили некоторые вершины в MST,
     * то значение ключа для вершины v - это вес ребра минимального веса,
     * которое соединяет v с одной из вершин, уже включенных в MST.
     * </p>
     *
     * @return индекс вершины с минимальным значением ключа, которая еще не была посещена
     */
    int minKey() {

        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < V; v++)

            // Если вершина не была посещена и значение ее ключа меньше текущего минимума
            if (visited[v] == false && key[v] < min) {

                min = key[v];
                minIndex = v;
            }

        return minIndex;
    }

    /**
     * Реализует алгоритм Прима для построения минимального остовного дерева
     * взвешенного неориентированного графа на основе матрицы смежности
     *
     * @param matrix матрица смежности графа
     */
    void primMST(int[][] matrix) {

        for (int i = 0; i < V; i++) {

            key[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        // Выбор начальной вершины для построения MST произвольный
        // Ключ для начальной вершины должен быть меньше ключа для всех остальных вершин в графе.
        key[0] = 0;

        // Устанавливаем значение parent для выбранной начальной вершины в -1,
        // чтобы отметить, что эта вершина является корневой вершиной MST.
        parent[0] = -1;

        // Для связного графа с V вершинами, существует V - 1 ребер,
        // которые могут образовать дерево, связывающее все V вершин.
        for (int i = 0; i < V - 1; i++) {

            int u = minKey();
            visited[u] = true;

            // Обновляем значения ключа и parent индекса смежных вершин выбранной вершины.
            // Берем только те вершины, которые не еще не включены в MST
            for (int v = 0; v < V; v++) {

                if (matrix[u][v] != 0 && visited[v] == false && matrix[u][v] < key[v]) {

                    parent[v] = u;
                    key[v] = matrix[u][v];
                }
            }
        }

    }

    /**
     * Выводит на экран MST графа с весами ребер и общей суммой
     */
    void printMST() {

        System.out.println("Ребро\tВага");
        int sum = 0;

        for (int i = 1; i < V; i++) {

            System.out.println(parent[i] + " - " + i + "\t" + key[i]);
            sum += key[i];

        }

        System.out.println("Вага мінімального остовного дерева: " + sum);
    }

}