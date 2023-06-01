package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Main main = new Main();
        main.run();
    }

    public void run() {

        String filePath = "userfiles/matrix.txt";
        int[][] matrix = readMatrix(filePath);

        PrimMST mst = new PrimMST(matrix.length);
        mst.primMST(matrix);
        mst.printMST();

    }

    public int[][] readMatrix(String filePath) {

        try {

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();

            int numRows = 0;
            int numCols = 0;

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