/*
 * 1. Разработать структуры данных для представления неориентированного и ориентированного графов по разным методам:
 * - матрицей инцидентности;
 * - матрицей смежности.
 *
 * 2. Разработать функции ввода и вывода данных, определяющих граф, в эти структуры.
*/

#include <iostream>

using namespace std;

int **createAdjacencyMatrix(int vertex);

int **createIncidenceMatrix(int vertex, int edges);

void fillAdjacencyMatrix(int **&adjacencyMatrix, int vertex);

void fillIncidenceMatrix(int **&incidenceMatrix, int vertex, int edges);

void outputAdjacencyMatrix(int **adjacencyMatrix, int vertex);

void outputIncidenceMatrix(int **incidenceMatrix, int vertex, int edges);

void enterData(int **&adjacencyMatrix, int **&incidenceMatrix, int edges, int vertex1, int vertex2);


int main() {

    int vertex, edges;

    cout << "Enter vertex: ";
    cin >> vertex;
    cout << "Enter edges: ";
    cin >> edges;

    int vertex1;
    int vertex2;

    int **adjacencyMatrix = createAdjacencyMatrix(vertex);
    int **incidenceMatrix = createIncidenceMatrix(vertex, edges);

    fillAdjacencyMatrix(adjacencyMatrix, vertex);
    fillIncidenceMatrix(incidenceMatrix, vertex, edges);


    cout << "Please enter data: " << endl;
    enterData(adjacencyMatrix, incidenceMatrix, edges, vertex1, vertex2);

    cout << "Adjacency Matrix: " << endl;
    outputAdjacencyMatrix(adjacencyMatrix, vertex);

    cout << "Incidence Matrix: " << endl;
    outputIncidenceMatrix(incidenceMatrix, vertex, edges);

    return 0;
}

int **createAdjacencyMatrix(int vertex) {
    int **adjacencyMatrix = new int *[vertex];

    for (int i = 0; i < vertex; i++) {
        adjacencyMatrix[i] = new int[vertex];

    }
    return adjacencyMatrix;
}

int **createIncidenceMatrix(int vertex, int edges) {

    int **incidenceMatrix = new int *[vertex];

    for (int i = 0; i < edges; i++) {
        incidenceMatrix[i] = new int[edges];
    }

    return incidenceMatrix;
}


void fillAdjacencyMatrix(int **&adjacencyMatrix, int vertex) {

    for (int i = 0; i < vertex; i++) {
        for (int j = 0; j < vertex; j++) {
            adjacencyMatrix[i][j] = 0;
        }
    }
}

void fillIncidenceMatrix(int **&incidenceMatrix, int vertex, int edges) {
    for (int i = 0; i < vertex; i++) {
        for (int j = 0; j < edges; j++) {
            incidenceMatrix[i][j] = 0;
        }
    }
}


void outputAdjacencyMatrix(int **adjacencyMatrix, int vertex) {
    for (int i = 0; i < vertex; i++) {
        for (int j = 0; j < vertex; j++) {
            cout << adjacencyMatrix[i][j] << " ";
        }
        cout << endl;
    }
}

void outputIncidenceMatrix(int **incidenceMatrix, int vertex, int edges) {

    for (int i = 0; i < vertex; i++) {
        for (int j = 0; j < edges; j++) {
            cout << incidenceMatrix[i][j] << " ";
        }
        cout << endl;
    }
}

void enterData(int **&adjacencyMatrix, int **&incidenceMatrix, int edges, int vertex1, int vertex2) {
    int iterator = 1;
    for (int i = 0; i < edges; i++) {
        cout << "E" << (iterator++) << ":";

        cin >> vertex1;
        cin >> vertex2;

        incidenceMatrix[vertex1 - 1][i] = 1;
        incidenceMatrix[vertex2 - 1][i] = 1;
        adjacencyMatrix[vertex1 - 1][vertex2 - 1] = 1;
        adjacencyMatrix[vertex2 - 1][vertex1 - 1] = 1;
    }
}
