import logic.ArrayProcessor;

import java.util.Arrays;

public class Processor implements ArrayProcessor {

    // Сума квадратів всех елементів масиву
    @Override
    public double calculate(double[] array) {

        double temp = 0;
        double result = 0;

        for (int i = 0; i < array.length; i++) {

            temp = array[i] * array[i];
            result += temp;
        }

        return result;
    }

    // Знайти суму від'ємних елементів заштрихованої частини
    @Override
    public double calculate(double[][] array) {

        double sum = 0;

        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array.length; j++) {

                /*
                 * Если номер строки элемента совпадает с номером столбца (i = j), это означает что элемент лежит на главной диагонали матрицы;
                 * Если номер строки превышает номер столбца (i > j), то элемент находится ниже главной диагонали;
                 * Если номер столбца больше номера строки (i < j), то элемент находится выше главной диагонали;
                 * Элемент лежит на побочной диагонали квадратной матрицы, если его индексы удовлетворяют равенству i + j + 1 = n;
                 * Неравенство i + j + 1 < n характерно для элемента находящегося выше побочной диагонали квадратной матрицы;
                 * соответственно, элементу квадратной матрицы, лежащему ниже побочной диагонали соответствует выражение i + j + 1 > n;
                 *
                 * Для нижнего правого квадрата (четверти) индексы i, j элементов начинаются с N / 2, где N - размер массива
                 */

                if ((i + j + 1 >= array.length) && ((i < array.length / 2) || (j < array.length / 2))) {

                    if (array[i][j] < 0) {

                        sum += array[i][j];
                    }

                }
            }
        }

        return sum;
    }


    @Override
    public void processArray(double[] array) {

        System.out.println(Arrays.toString(array));
    }

    @Override
    public void processArray(double[][] array) {

        // Этот метод предназначен для преобразования многомерных массивов в строки.
        // System.out.println(Arrays.deepToString(array));

        for (int i = 0; i < array.length; i++){

            for (int j = 0; j < array.length; j++){

                System.out.print(array[i][j] + " ");
            }

            System.out.println("\n");
        }

    }
}
