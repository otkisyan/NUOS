package org.example;

/**
 * Многоалфавитная подстановка
 */
public class Multialphabetic {

    private final String letters = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя'_";
    private final String key = "термінповідомлення";

    public String getLetters() {

        return letters;
    }

    public String getKey() {

        return key;
    }

    public String encrypt(String text) {

        if (text.length() != key.length()) {

            throw new IllegalArgumentException
                    ("Текст для шифрування та ключ шифрування мають бути однакової довжини");
        }

        int[][] array = new int[3][key.length()];

        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {

            char textChar = text.charAt(i);
            char keyChar = key.charAt(i);

            int textIndex = letters.indexOf(textChar);
            int keyIndex = letters.indexOf(keyChar);

            int cipherIndex = textIndex + keyIndex;

            if (cipherIndex > letters.length() - 1) {

                cipherIndex = cipherIndex % (letters.length() - 1);
            }

            array[0][i] = textIndex;
            array[1][i] = keyIndex;
            array[2][i] = cipherIndex;

            encryptedText.append(letters.charAt(cipherIndex));
        }


        System.out.println();
        System.out.printf("%1s", "Індекс");

        for (int i = 0; i < letters.length(); i++) {

            System.out.printf("%3d", i);
        }

        System.out.println();

        System.out.printf("%2s", "Буква ");

        for (int i = 0; i < letters.length(); i++) {

            System.out.printf("%3c", letters.charAt(i));
        }

        System.out.println();
        System.out.println();
        System.out.println("Текст      | Ключ    | Результат");
        System.out.println("+----------+---------+-------------+");

        for (int i = 0; i < array[0].length; i++) {

            System.out.printf("| %8d | %7d | %11d |\n", array[0][i], array[1][i], array[2][i]);
        }

        System.out.println("+----------+---------+-------------+");

        return encryptedText.toString();
    }


    public String decrypt(String encryptedText) {

        if (encryptedText.length() != key.length()) {

            throw new IllegalArgumentException
                    ("Текст для розшифрування та ключ шифрування мають бути однакової довжини");
        }

        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < encryptedText.length(); i++) {

            char encryptedChar = encryptedText.charAt(i);
            char keyChar = key.charAt(i);

            int encryptedIndex = letters.indexOf(encryptedChar);
            int keyIndex = letters.indexOf(keyChar);

            // int textIndex = (encryptedIndex - keyIndex + (letters.length() - 1)) % (letters.length() - 1);
            int textIndex = encryptedIndex - keyIndex;

            if (textIndex < 0) {

                textIndex = textIndex + (letters.length() - 1);
            }

            decryptedText.append(letters.charAt(textIndex));
        }

        return decryptedText.toString();

    }

}
