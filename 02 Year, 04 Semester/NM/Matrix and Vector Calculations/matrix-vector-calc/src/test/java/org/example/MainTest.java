package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    Main main;

    @BeforeEach
    void setUp() {

        main = new Main();
    }


    @Test
    public void testMultiplyVectorByScalar() {

        double[] vector = new double[]{1, 2, 3};
        double scalar = 2;
        double[] expectedResult = new double[]{2, 4, 6};

        double[] result = Main.multiplyVectorByScalar(vector, scalar);
        assertArrayEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testAddVectors() {

        double[] vector1 = new double[]{1, 2, 3};
        double[] vector2 = new double[]{4, 5, 6};
        double[] expectedResult = new double[]{5, 7, 9};

        double[] result = Main.addVectors(vector1, vector2);
        assertArrayEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testScalarProduct() {

        double[] vector1 = new double[]{1, 2, 3};
        double[] vector2 = new double[]{4, 5, 6};
        double expectedResult = 32;

        double result = Main.scalarProduct(vector1, vector2);
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testModulus() {

        double[] vector = new double[]{3, 4};
        double expectedResult = 5;

        double result = Main.modulus(vector);
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testTransposeMatrix() {

        double[][] matrix = new double[][]{{1, 2, 3}, {4, 5, 6}};
        double[][] expectedResult = new double[][]{{1, 4}, {2, 5}, {3, 6}};

        double[][] result = Main.transposeMatrix(matrix);
        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void testMultiplyMatrixByScalar() {

        double[][] matrix = new double[][]{{1, 2, 3}, {4, 5, 6}};
        double scalar = 2;
        double[][] expectedResult = new double[][]{{2, 4, 6}, {8, 10, 12}};

        double[][] result = Main.multiplyMatrixByScalar(matrix, scalar);
        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void testMultiplyMatrixByVector() {

        double[][] matrix = new double[][]{{1, 2, 3}, {4, 5, 6}};
        double[] vector = new double[]{7, 8, 9};
        double[] expectedResult = new double[]{50, 122};

        double[] result = Main.multiplyMatrixByVector(matrix, vector);
        assertArrayEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testMatrixSum() {

        double[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] matrix2 = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};
        double[][] expected = {{10, 10, 10}, {10, 10, 10}, {10, 10, 10}};
        double[][] result = Main.matrixSum(matrix1, matrix2);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testMultiplyMatrixByMatrix() {

        double[][] matrix1 = {{1, 2, 3}, {4, 5, 6}};
        double[][] matrix2 = {{7, 8}, {9, 10}, {11, 12}};
        double[][] expectedResult = {{58, 64}, {139, 154}};
        double[][] actualResult = Main.multiplyMatrixByMatrix(matrix1, matrix2);
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void testMultiplyMatrixByMatrixException() {

        double[][] matrix1 = {{1, 2, 3}, {4, 5, 6}};
        double[][] matrix2 = {{7, 8}, {9, 10}};

        assertThrows(IllegalArgumentException.class, () -> {

            Main.multiplyMatrixByMatrix(matrix1, matrix2);

        });

    }
}