import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessorTest {

    Processor processor;

    @BeforeEach
    void setUp() {

        processor = new Processor();
    }


    @Test
    void calculateTest1() {

        double[] arr = {0.2, 4.5, 2.1, 2.8};
        double result = processor.calculate(arr);

        assertEquals(32.54, result, 0.01);

    }

    @Test
    void calculateTest2() {

        double[][] arr = {
                {-6.2, 4.1, 2.0, 3.2, 2.1, 2.3},
                {2.1, 2.3, 4.2, 3.2, -2.1, 1.6},
                {2.1, 4.2, 1.2, 0.5, 0.1, -4.1},
                {0.2, -0.2, 4.5, 2.0, 4.1, 6.2},
                {2.1, 4.2, 1.2, 0.2, 4.6, 1.2},
                {-2.8, 4.2, 1.2, 0.2, 4.6, -4.2},

        };

        // (-2.1) + (-4.1) + (-2.8) = -9


      /*  for (int i = 0; i < arr.length; i++) {

            for (int j = 0; j < arr.length; j++) {

                arr[i][j] = 0;
            }
        }

        for (int i = 0; i < arr.length; i++) {

            for (int j = 0; j < arr.length; j++) {

                //if ((i + j + 1 >= arr.length) && !(i >= arr.length / 2 && j >= arr.length / 2)) {
                if ((i + j + 1 >= arr.length) && ((i < arr.length / 2) || (j < arr.length / 2))){

                    arr[i][j] = 1;
                }

            }
        }
*/

        for (int i = 0; i < arr.length; i++) {

            for (int j = 0; j < arr.length; j++) {

                System.out.print(arr[i][j] + " ");
            }

            System.out.println("\n");
        }

        double result = processor.calculate(arr);
        assertEquals(-9, result, 0.01);


    }
}