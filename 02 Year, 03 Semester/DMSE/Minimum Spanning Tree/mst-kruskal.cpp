// https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-using-stl-in-c/
// https://habr.com/ru/post/104772/
// https://habr.com/ru/post/569444/
// https://www.youtube.com/watch?v=5XEjNWHc_NI&ab_channel=%D0%9C%D0%B0%D1%82%D0%B5%D0%BC%D0%B0%D1%82%D0%B8%D0%BA%D0%B0%D0%B4%D0%BB%D1%8F%D0%B2%D1%81%D0%B5%D1%85

#include <iostream>
#include <vector>

using namespace std;

 /*
 * Необходимо построить такое дерево, которые бы с одной стороны включало все вершины графа,
 * а с другой стороны, чтобы ребра были такие, чтобы сумма стоимости (веса) была минимальная,
 * то есть сеть минимального веса, связывающую все вершины графа
 */

// Структура для представления графа
struct Graph {

    // V - вершины; E - ребра графа
    int V, E;

    /*
     * Делаю вектор ребер, который состоит из всех ребер в графе,
     * каждый элемент вектора содержит 3 параметра:
     * источник, место назначения, вес ребра между источником и местом назначения
     *
     * Первый элемент соответствует весу ребра,
     * второй элемент является парой и содержит две вершины ребра
     */

    vector<pair<int, pair<int, int>>> edges;

    // Конструктор
    Graph(int V, int E) {

        this->V = V;
        this->E = E;
    }

    // Функция для добавления ребер в вектор
    // w - вес, u - источник, v - место назначения

    void addEdge(int u, int v, int w) {

        edges.push_back({w, {u, v}});
    }

    // Функция для нахождения минимального остовного дерева графа используя алгоритм Крускала
    int kruskalMST();
};

// Структура для представления непересекающихся множеств
struct DisjointSets {

    int *parent, *rnk;
    int n;

    // Конструктор
    DisjointSets(int n) {

        this->n = n;
        parent = new int[n + 1];
        rnk = new int[n + 1];

        // Изначально, все вершины находятся в разных множествах, и имеют ранг 0
        for (int i = 0; i <= n; i++) {

            rnk[i] = 0;

            // Каждый элемент является родителем самого себя
            parent[i] = i;
        }
    }


    // Представителем дерева будем считать его корень.
    // Тогда для нахождения этого представителя достаточно
    // подняться вверх по родительским ссылкам до тех пор, пока не наткнемся на корень
    // Фактически переподвесим все эти вершины вместо длинной ветви непосредственно к корню.
    int find(int u) {

        // Если u не родитель самого себя
        if (u != parent[u]) {

            parent[u] = find(parent[u]);
        }

        return parent[u];
    }

    // Слияние
    void merge(int x, int y) {

        x = find(x);
        y = find(y);

        if (rnk[x] > rnk[y]) {

            parent[y] = x;

        } else { // Если rnk[x] <= rnk[y]

            parent[x] = y;
        }

        if (rnk[x] == rnk[y])

            rnk[y]++;
    }
};


// Функция возвращает вес минимально остовного дерева
int Graph::kruskalMST() {

    int mstWeight = 0;

    // Использую встроенную функцию для сортировки ребер в порядке возрастания (не убывания)
    sort(edges.begin(), edges.end());

    // Создаю объект непересекающихся множеств
    DisjointSets ds(V);

    // Обьявляю итератор it
    // Итерируюсь по всем уже отсортированным ребрам
    vector<pair<int, pair<int, int>>>::iterator it;

    for (it = edges.begin(); it != edges.end(); it++) {

        // Обращаюсь ко второй паре, первому и второму элементу
        int u = it->second.first;
        int v = it->second.second;

        // DisjointSets
        int set_u = ds.find(u);
        int set_v = ds.find(v);

        // Проверяю если текущее ребро создает цикл или нет
        // Цикл создается если u и v принадлежит одному и тому же множеству

        if (set_u != set_v) {

            // Текущее ребро будет в МОД, поэтому вывожу его на экран
            cout << u << " - " << v << endl;

            // Обновление веса МОД
            mstWeight += it->first;

            // Объединяю два множества
            ds.merge(set_u, set_v);
        }
    }

    return mstWeight;
}

int main() {

    // Вершины считаются с нуля, ребра с единицы
    int V = 8, E = 9;
    Graph g(V, E);

    // Первый аргумент - источник, второй - место назначения, третий - вес
    g.addEdge(7, 8, 1);
    g.addEdge(0, 1, 2);
    g.addEdge(0, 6, 3);
    g.addEdge(1, 3, 3);
    g.addEdge(1, 5, 4);
    g.addEdge(5, 8, 4);
    g.addEdge(3, 4, 5);
    g.addEdge(3, 5, 5); // образует цикл, вершины 3 и 5 находятся в одном множестве
    g.addEdge(0, 2, 6);
    g.addEdge(5, 7, 6);

    // Конец, все вершины сливаются в одно множество
    g.addEdge(2, 3, 7);
    g.addEdge(0, 3, 8);
    g.addEdge(4, 6, 8);
    g.addEdge(1, 2, 9);
    g.addEdge(4, 7, 9);


    cout << "Ребра минимального остовного дерева " << endl;
    int mstWeight = g.kruskalMST();

    cout << "Вес минимального остовного дерева " << mstWeight << endl;

    return 0;
}
