package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Main main = new Main();
        main.run();
    }

    public void run() {

        Scanner sc = new Scanner(System.in);
        String filePath = "userfiles/matrix.txt";

        DFS dfs;

      /*  DFS dfs = new DFS(6);
        dfs.addEdge(0,1);
        dfs.addEdge(0,2);
        dfs.addEdge(0,5);
        dfs.addEdge(1,2);
        dfs.addEdge(1,3);
        dfs.addEdge(2,5);
        dfs.addEdge(5,3);
        dfs.addEdge(3,4);*/

        int[][] matrix = readMatrix(filePath);

        dfs = DFS.incidenceMatrixToAdjacencyList(matrix);

        System.out.print("Введіть номер початкової вершини: ");
        int initialVertex = sc.nextInt();

        System.out.println("Досяжні вершини:");
        dfs.DFS(initialVertex);

    }

    public int[][] readMatrix(String filePath) {

        try {

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();

            int numRows = 0;
            int numCols = 0;

            // Считаю количество строк и столбцов матрицы
            while (line != null) {

                numRows++;
                String[] rowValues = line.split("[, ]+");

                if (numCols == 0) {

                    numCols = rowValues.length;

                } else if (numCols != rowValues.length) {

                    throw new IllegalArgumentException("Неправильна матриця - рядки мають різну довжину.");
                }

                line = reader.readLine();
            }

            int[][] matrix = new int[numRows][numCols];

            reader = new BufferedReader(new FileReader(filePath));
            line = reader.readLine();
            int row = 0;

            while (line != null) {

                String[] rowValues = line.split("[, ]+");

                for (int col = 0; col < rowValues.length; col++) {

                    matrix[row][col] = Integer.parseInt(rowValues[col].trim());
                }

                row++;
                line = reader.readLine();
            }

            reader.close();
            return matrix;

        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
    }
}