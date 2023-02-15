package org.example;

import javax.naming.AuthenticationException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Main main = new Main();
        // main.run();
        main.run2();
    }

    public void run() {

        Scanner sc = new Scanner(System.in);
        PlayfairCipher playfairCipher = new PlayfairCipher();
        final int N = 7;
        final int K = 5;


        System.out.println("Encrypt (1) or Decrypt (2)");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {

            case 1 -> {

                String keyword;
                String password;
                System.out.print("Enter keyword: ");
                keyword = sc.nextLine();

                // Удаление дубликатов из ключа
                keyword = keyword.toLowerCase();
                keyword = playfairCipher.removeDuplicates(keyword);

                // Генерация матрицы
                char[][] matrix = playfairCipher.generateMatrix(keyword);

                System.out.print("Enter password to encrypt: ");
                password = sc.nextLine();
                password = password.toLowerCase();

                // Установка символа-заполнителя
                password = playfairCipher.insertPlaceholder(password);

                // Шифровка пароля
                String encryptedPassword = playfairCipher.encryptPassword(matrix, password);
                System.out.println("Encrypted password: " + encryptedPassword);
            }
            case 2 -> {

                String keyword;
                String encryptedPassword;
                System.out.print("Enter keyword: ");
                keyword = sc.nextLine();

                // Удаление дубликатов из ключа
                keyword = playfairCipher.removeDuplicates(keyword);

                // Генерация матрицы
                char[][] matrix = playfairCipher.generateMatrix(keyword);

                System.out.print("Enter password to decrypt: ");
                encryptedPassword = sc.nextLine();
                encryptedPassword = encryptedPassword.toLowerCase();

                // Расшифровка пароля
                String decryptedPassword = playfairCipher.decryptPassword(matrix, encryptedPassword);
                System.out.println("Decrypted password: " + decryptedPassword);
            }
        }
    }

    public void run2() {

        Scanner sc = new Scanner(System.in);
        Auth auth = new Auth();


        System.out.println("Register (1) or Auth (2)");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {

            case 1 -> {
                String username;
                String password;
                System.out.println("Enter username: ");
                username = sc.nextLine();
                System.out.println("Enter password: ");
                password = sc.nextLine();

                try {

                    auth.registerUser(username, password);
                } catch (AuthenticationException err) {

                    System.out.println(err.getMessage());
                }

            }
            case 2 -> {

                String username;
                String password;
                System.out.println("Enter username: ");
                username = sc.nextLine();
                System.out.println("Enter password: ");
                password = sc.nextLine();

                boolean success = false;
                try {

                    success = auth.authUser(username, password);

                } catch (AuthenticationException err) {

                    System.out.println(err.getMessage());
                }

                if (success){

                    System.out.println("Auth success");
                }

            }
        }


    }
}
