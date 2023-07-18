package org.example;

/**
 * Табличная перестановка по строкам и столбцам - табличная перестановка,
 * при которой исходный текст записывается в таблицу определенного размера рядками (строками),
 * а зашифрованный текст считывается столбцами.
 * <p>Например, текст «НАДІШЛІТЬ_ДОПОМОГУ» с помощью таблицы
 * размером 3x6 будет зашифрованный в «НІПАТОДЬМІ_ОШДГЛОУ».</p>
 * <br>
 * Н А Д І Ш Л
 * <br></br>
 * І Т Ь _ Д О
 * <br></br>
 * П О М О Г У
 */
public class Main {
    public static String encrypt(String message, int rows, int columns) {

        char[][] grid = new char[rows][columns];

        int index = 0;
        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {

                grid[i][j] = message.charAt(index++);

            }
        }

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {

                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        StringBuilder encrypted = new StringBuilder();

        for (int col = 0; col < columns; col++) {

            for (int row = 0; row < rows; row++) {

                encrypted.append(grid[row][col]);
            }
        }

        return encrypted.toString().trim();
    }

    public static String decrypt(String encrypted, int rows, int columns) {

        char[][] grid = new char[rows][columns];
        int index = 0;

        for (int col = 0; col < columns; col++) {

            for (int row = 0; row < rows; row++) {

                grid[row][col] = encrypted.charAt(index++);
            }
        }

        StringBuilder decrypted = new StringBuilder();

        for (int row = 0; row < rows; row++) {

            for (int col = 0; col < columns; col++) {

                decrypted.append(grid[row][col]);
            }
        }

        return decrypted.toString().trim();
    }

    public static void main(String[] args) {

        String name = "xxxxx";
        String surname = "xxxxxxxx";
        int rows = name.length();
        int columns = surname.length();

        System.out.println("Кількість рядків дорівнює кількості букв імені: " + name + " - кількість букв: " + rows);
        System.out.println("Кількість стобвців дорівнює кількості букв прізвища: " + surname + " - кількість букв: " + columns);
        System.out.println("Розмір матриці: " + rows + " x " + columns);
        System.out.println("Кількість елементів матриці: " + rows * columns);
        System.out.println();

        String message = "Lorem ipsum dolor sit amet orci aliquame";

        System.out.println("Вихідний текст: " + message);
        System.out.println("У вихідному тексті замінюємо пробіли на нижнє підкреслення");
        System.out.println();

        message = message.replaceAll("\\s+", "_");

        System.out.println("Вихідний текст з заміненими пробілами: " + message);

        String encrypted = encrypt(message, rows, columns);
        System.out.println("Зашифроване повідомлення: " + encrypted);
        String decrypted = decrypt(encrypted, rows, columns);
        System.out.println("Розшифроване повідомлення: " + decrypted);


    }
}