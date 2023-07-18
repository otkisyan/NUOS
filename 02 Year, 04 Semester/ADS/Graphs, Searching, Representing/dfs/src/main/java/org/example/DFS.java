package org.example;

import java.util.*;

/**
 * Методом поиска в глубину (Depth-first search) в неориентированном графе найти и вывести все вершины,
 * которые можно добиться из заданной.
 * Ввести номер начальной вершины с клавиатуры.
 * Граф задан в текстовом файле матрицей инцидентности.
 * Достижимые вершины выводить в порядке их посещения.
 *
 * @see <a href="https://foxford.ru/wiki/informatika/hranenie-grafa-spiski-smezhnyh-vershin">Foxford: Хранение графа: списки смежных вершин </a>
 * @see <a href="https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/">GeeksforGeeks: Depth First Search for a Graph</a>
 * @see <a href="https://habr.com/ru/company/otus/blog/660725/">Habr: Реализуем алгоритм поиска в глубину</a>
 * @see <a href="https://habr.com/ru/post/504374/">Habr: Обход графа: поиск в глубину и поиск в ширину простыми словами</a>
 */

public class DFS {

    // Количество вершин
    private int V;

    // Список смежности — один из способов представления графа в виде коллекции списков вершин.
    // Каждой вершине графа соответствует список, состоящий из соседей этой вершины.
    private LinkedList<Integer> adj[];

    DFS(int v) {

        V = v;
        adj = new LinkedList[v];

        for (int i = 0; i < v; ++i) {

            adj[i] = new LinkedList();
        }

    }

    void addEdge(int v, int w) {

        // Добавляю ребро в обоих направлениях
        adj[v].add(w);
        adj[w].add(v);
    }

    void DFSUtil(int v, boolean[] visited) {

        // Помечаю текущий узел как посещенный и печатаю на экран
        visited[v] = true;
        System.out.print(v + " ");

        // Общая идея алгоритма состоит в следующем: для каждой не пройденной вершины необходимо
        // найти все не пройденные смежные вершины и повторить поиск для них.
        //
        // Пошаговое представление:
        // Выбираем любую вершину из еще не пройденных, обозначим ее как v
        // Запускаем процедуру dfs(v)
        // Помечаем вершину v как пройденную
        // Для каждой не пройденной смежной с v вершиной (назовем ее n) запускаем dfs(n)
        // Повторяем шаги 1, 2, пока все вершины не окажутся пройденными.

        Iterator<Integer> i = adj[v].listIterator();

        // Обход всех вершин, смежных к текущей
        while (i.hasNext()) {

            int n = i.next();

            if (!visited[n]) {

                DFSUtil(n, visited);
            }
        }
    }

    void DFS(int v) {

        boolean[] visited = new boolean[V];
        DFSUtil(v, visited);
    }

    public static DFS incidenceMatrixToAdjacencyList(int[][] matrix) {

        // Количество вершин
        int n = matrix.length;

        // Количество ребер
        int m = matrix[0].length;

        DFS dfs = new DFS(n);

        // Перебираю столбцы (ребра) матрицы инцидентности
        for (int j = 0; j < m; j++) {

            int w = -1, v = -1;

            // Нахожу две вершины, которые соединяет ребро
            for (int i = 0; i < n; i++) {

                // Проверяем, равно ли w -1.
                // Если да, то это означает, что мы еще не нашли вершину, соединенную с этим ребром.
                // Тогда устанавливаем w в i, так как вершина i соединена с этим ребром.
                if (matrix[i][j] == 1) {

                    if (w == -1) {

                        w = i;

                    }

                    // Если w не равно -1, то это означает, что мы уже нашли одну вершину, соединенную с этим ребром.
                    // Устанавливаем v в i, так как вершина i является другой вершиной, соединенной с этим ребром.
                    else {

                        v = i;
                    }
                }
            }

            dfs.addEdge(w, v);

        }

        return dfs;
    }

}
