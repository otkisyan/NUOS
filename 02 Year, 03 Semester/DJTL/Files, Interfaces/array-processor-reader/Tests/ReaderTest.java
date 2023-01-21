import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {

    Reader reader;

    @BeforeEach
    void setUp() {

        reader = new Reader();
    }

    // Тест для метода принимающего в себя file, на сканере использующий nextDouble()
    // В файле целые числа, без запятых между числами
    @Test
    void readOneDimensionalArrayFileIntegerTest() {

        File file = new File("userfiles/1dimensionalArray/1dimensional-Integers");

        double[] arrExpected = {25, 30, 40, 10, 20};

        double[] arrActual = reader.readOneDimensionalArray(file);

        assertArrayEquals(arrExpected, arrActual);
    }

    // Тест для метода принимающего в себя путь к файлу, на сканере использующий nextLine(),
    // В файле целые числа, без запятых между числами
    @Test
    void readOneDimensionalArrayFilePathIntegerTest() {

        double[] arrExpected = {25, 30, 40, 10, 20};

        double[] arrActual = reader.readOneDimensionalArray("userfiles/1dimensionalArray/1dimensional-Integers");

        assertArrayEquals(arrExpected, arrActual);
    }


    // Тест для метода принимающего в себя file, на сканере использующий nextDouble()
    // В файле целые числа, с запятыми между числами
    @Test
    void readOneDimensionalArrayFileIntegerCommaTest() {

        File file = new File("userfiles/1dimensionalArray/1dimensional-Integers-Comma");

        double[] arrExpected = {25, 30, 40, 10, 20};

        double[] arrActual = reader.readOneDimensionalArray(file);

        assertArrayEquals(arrExpected, arrActual);
    }

    // Тест для метода принимающего в себя путь к файлу, на сканере использующий nextLine()
    // В файле целые числа, с запятыми между числами
    @Test
    void readOneDimensionalArrayFilePathIntegerCommaTest() {

        double[] arrExpected = {25, 30, 40, 10, 20};

        double[] arrActual = reader.readOneDimensionalArray("userfiles/1dimensionalArray/1dimensional-Integers-Comma");

        assertArrayEquals(arrExpected, arrActual);
    }


    // Тест для метода принимающего в себя file, на сканере использующий nextDouble()
    // В файле не целые числа, без запятых между числами
    @Test
    void readOneDimensionalArrayFileDoubleTest() {

        File file = new File("userfiles/1dimensionalArray/1dimensional-Doubles");

        double[] arrExpected = {20.3, 10.4, 12.40, 10.0, 5.2};

        double[] arrActual = reader.readOneDimensionalArray(file);

        assertArrayEquals(arrExpected, arrActual);
    }

    // Тест для метода принимающего в себя путь к файлу, на сканере использующий nextLine()
    // В файле не целые числа, без запятых между числами
    @Test
    void readOneDimensionalArrayFilePathDoubleTest() {

        double[] arrExpected = {20.3, 10.4, 12.40, 10.0, 5.2};

        double[] arrActual = reader.readOneDimensionalArray("userfiles/1dimensionalArray/1dimensional-Doubles-Comma");

        assertArrayEquals(arrExpected, arrActual);

    }

    // Тест для метода принимающего в себя file, на сканере использующий nextDouble()
    // В файле не целые числа, с запятыми между числами
    @Test
    void readOneDimensionalArrayFileDoubleCommaTest() {

        File file = new File("userfiles/1dimensionalArray/1dimensional-Doubles-Comma");

        double[] arrExpected = {20.3, 10.4, 12.40, 10.0, 5.2};

        double[] arrActual = reader.readOneDimensionalArray(file);

        assertArrayEquals(arrExpected, arrActual);
    }

    // Тест для метода принимающего в себя путь к файлу, на сканере использующий nextLine()
    // В файле не целые числа, с запятыми между числами
    @Test
    void readOneDimensionalArrayFilePathDoubleCommaTest() {

        double[] arrExpected = {20.3, 10.4, 12.40, 10.0, 5.2};

        double[] arrActual = reader.readOneDimensionalArray("userfiles/1dimensionalArray/1dimensional-Doubles-Comma");

        assertArrayEquals(arrExpected, arrActual);
    }


    // Тест для метода принимающего в себя file, на BufferedReader
    // В файле целые числа, без запятых между числами
    @Test
    void readTwoDimensionalArrayFileIntegerTest() {

        File file = new File("userfiles/2dimensionalArray/2dimensional-Integers");

        double[][] arrExpected = {

                {2, 3, 1, 4, 5},
                {3, 25, 1, 3, 4,},
                {1, 5, 8, 91, 0},
                {1, 24, 5, 1, 2},
                {2, 3, 1, 2, 3}
        };

        double[][] arrActual = reader.readTwoDimensionalArray(file);

        assertArrayEquals(arrExpected, arrActual);
    }

    // Тест для метода принимающего в себя путь к файлу, на сканере использующий nextDouble()
    // В файле целые числа, без запятых между числами
    @Test
    void readTwoDimensionalArrayFilePathIntegerTest() {

        double[][] arrExpected = {

                {2, 3, 1, 4, 5},
                {3, 25, 1, 3, 4,},
                {1, 5, 8, 91, 0},
                {1, 24, 5, 1, 2},
                {2, 3, 1, 2, 3}
        };

        double[][] arrActual = reader.readTwoDimensionalArray("userfiles/2dimensionalArray/2dimensional-Integers");

        assertArrayEquals(arrExpected, arrActual);
    }

    // Тест для метода принимающего в себя file, на BufferedReader
    // В файле целые числа, с запятыми между числами
    @Test
    void readTwoDimensionalArrayFileIntegerCommaTest() {

        File file = new File("userfiles/2dimensionalArray/2dimensional-Integers-Comma");

        double[][] arrExpected = {

                {2, 3, 1, 4, 5},
                {3, 25, 1, 3, 4,},
                {1, 5, 8, 91, 0},
                {1, 24, 5, 1, 2},
                {2, 3, 1, 2, 3}
        };

        double[][] arrActual = reader.readTwoDimensionalArray(file);

        assertArrayEquals(arrExpected, arrActual);
    }

    // Тест для метода принимающего в себя путь к файлу, на сканере использующий nextDouble()
    // В файле целые числа, с запятыми между числами
    @Test
    void readTwoDimensionalArrayFilePathIntegerCommaTest() {

        double[][] arrExpected = {

                {2, 3, 1, 4, 5},
                {3, 25, 1, 3, 4,},
                {1, 5, 8, 91, 0},
                {1, 24, 5, 1, 2},
                {2, 3, 1, 2, 3}
        };

        double[][] arrActual = reader.readTwoDimensionalArray("userfiles/2dimensionalArray/2dimensional-Integers-Comma");

        assertArrayEquals(arrExpected, arrActual);
    }

    // Тест для метода принимающего в себя file, на BufferedReader
    // В файле не целые числа, без запятых между числами
    @Test
    void readTwoDimensionalArrayFileDoubleTest() {

        File file = new File("userfiles/2dimensionalArray/2dimensional-Doubles");

        double[][] arrExpected = {
                {2.2, 3.1, 1.4, 4.2, 5.5},
                {3.0, 2.2, 1.1, 3.2, 4.4},
                {1.1, 45.7, 8.2, 9.1, 0},
                {1.2, 4.7, 5.1, 1.1, 2.7},
                {12.2, 3.1, 1.2, 2.4, 3.2}
        };

        double[][] arrActual = reader.readTwoDimensionalArray(file);

        assertArrayEquals(arrExpected, arrActual);
    }

    // Тест для метода принимающего в себя путь к файлу, на сканере использующий nextDouble()
    // В файле не целые числа, без запятых между числами
    @Test
    void readTwoDimensionalArrayFilePathDoubleTest() {

        double[][] arrExpected = {
                {2.2, 3.1, 1.4, 4.2, 5.5},
                {3.0, 2.2, 1.1, 3.2, 4.4},
                {1.1, 45.7, 8.2, 9.1, 0},
                {1.2, 4.7, 5.1, 1.1, 2.7},
                {12.2, 3.1, 1.2, 2.4, 3.2}
        };

        double[][] arrActual = reader.readTwoDimensionalArray("userfiles/2dimensionalArray/2dimensional-Doubles");

        assertArrayEquals(arrExpected, arrActual);
    }

    // Тест для метода принимающего file, на BufferedReader
    // В файле не целые числа, с запятыми между числами
    @Test
    void readTwoDimensionalArrayFileDoubleCommaTest() {

        File file = new File("userfiles/2dimensionalArray/2dimensional-Doubles-Comma");

        double[][] arrExpected = {
                {2.2, 3.1, 1.4, 4.2, 5.5},
                {3.0, 2.2, 1.1, 3.2, 4.4},
                {1.1, 45.7, 8.2, 9.1, 0},
                {1.2, 4.7, 5.1, 1.1, 2.7},
                {12.2, 3.1, 1.2, 2.4, 3.2}
        };

        double[][] arrActual = reader.readTwoDimensionalArray(file);

        assertArrayEquals(arrExpected, arrActual);
    }

    @Test
    void readTwoDimensionalArrayFilePathDoubleCommaTest(){

        double[][] arrExpected = {
                {2.2, 3.1, 1.4, 4.2, 5.5},
                {3.0, 2.2, 1.1, 3.2, 4.4},
                {1.1, 45.7, 8.2, 9.1, 0},
                {1.2, 4.7, 5.1, 1.1, 2.7},
                {12.2, 3.1, 1.2, 2.4, 3.2}
        };

        double[][] arrActual = reader.readTwoDimensionalArray("userfiles/2dimensionalArray/2dimensional-Doubles-Comma");

        assertArrayEquals(arrExpected, arrActual);
    }
}