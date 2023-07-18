import io.ArrayReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Reader implements ArrayReader {

    @Override
    public double[] readOneDimensionalArray(File file) {

        try (Scanner in = new Scanner(file)) {

            int n = in.nextInt();
            double[] arr = new double[n];

            for (int i = 0; i < arr.length; i++) {

                arr[i] = in.nextDouble();
            }

            return arr;

        }

        // Сигнализирует о том, что произошло какое-то исключение ввода/вывода.
        // Этот класс является общим классом исключений, возникающих при неудачных или прерванных операциях ввода/вывода.
        catch (IOException ex) {

            System.err.println("Error reading file");

            return null;
        }

    }

    @Override
    public double[] readOneDimensionalArray(String filePath) {

       /* try (Scanner in = new Scanner(new File(filePath))) {

            int n = in.nextInt();
            double[] arr = new double[n];

            for (int i = 0; i < arr.length; i++) {

                arr[i] = in.nextDouble();
            }

            return arr;

        } catch (IOException ex) {

            System.err.println("Error reading file");

            return null;
        }*/

        try (Scanner in = new Scanner(new File(filePath))) {

            int n = in.nextInt();
            double[] arr = new double[n];
            in.nextLine();

            String temp = in.nextLine();
            String[] strArray = temp.trim().split("[, ]+");

            for (int j = 0; j < arr.length; j++) {

                arr[j] = Double.parseDouble(strArray[j]);
            }

            return arr;

        } catch (IOException ex) {

            System.err.println("Error reading file");

            return null;
        }
    }

    @Override
    public double[][] readTwoDimensionalArray(File file) {

       /*

        try (Scanner in = new Scanner(file)) {

            int n = in.nextInt();
            double[][] arr = new double[n][n];

            for (int i = 0; i < arr.length; i++) {

                for (int j = 0; j < arr.length; j++) {

                    arr[i][j] = in.nextDouble();

                }
            }

            return arr;

        } catch (IOException ex) {

            System.err.println("Error reading file");

            return null;
        }

        */

        /*
         * Java класс BufferedReader читает текст из потока ввода символов, буферизуя прочитанные символы,
         * чтобы обеспечить эффективное считывание символов, массивов и строк.
         * Можно указать в конструкторе вторым параметром размер буфера.
         * Чтобы создать объект BufferedReader, нам нужно создать объект Reader для чтения данных из источника (например, из файла).
         * Поскольку Reader - это абстрактный класс, поэтому вам нужно создать его из одного из его подклассов
         */

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            double[][] arr = null;
            int row = 0;
            int size = 0;
            reader.readLine();

            // Можно записать так если хотим избавиться от условия в цикле while
            // int size = Integer.parseInt(reader.readLine());
            // double[][] arr = new double[size][size];

            String line;
            while ((line = reader.readLine()) != null) {

                String[] split = line.trim().split("[, ]+");

                if (arr == null) {

                    size = split.length;
                    arr = new double[size][size];
                }

                for (int col = 0; col < arr.length; col++) {

                    arr[row][col] = Double.parseDouble(split[col]);

                }

                // Вышли из цикла for, на следующий раз reader перейдет на новую строку,
                // поэтому также переходим на следующую строку массива
                row++;
            }

            return arr;

        } catch (IOException e) {

            System.out.println("Error reading file");
            return null;
        }


    }

    @Override
    public double[][] readTwoDimensionalArray(String filePath) {

        try (Scanner in = new Scanner(new File(filePath))) {

            int n = in.nextInt();
            double[][] arr = new double[n][n];

            for (int i = 0; i < arr.length; i++) {

                for (int j = 0; j < arr.length; j++) {

                    arr[i][j] = in.nextDouble();

                }
            }

            return arr;

        } catch (IOException ex) {

            System.err.println("Error reading file");

            return null;
        }

    }
}

