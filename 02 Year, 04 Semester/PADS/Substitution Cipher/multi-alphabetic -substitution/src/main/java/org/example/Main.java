package org.example;

public class Main {

    public static void main(String[] args) {

        Main main = new Main();
        main.run();
    }

    public void run(){

        Multialphabetic multialphabetic = new Multialphabetic();
        System.out.println("Алфавіт для шифрування: " + multialphabetic.getLetters());
        System.out.println("Ключ шифрування: " + multialphabetic.getKey() + " | Довжина: " + multialphabetic.getKey().length());
        System.out.println();

        String text = "надішліть_допомогу";
        System.out.println("Текст для шифрування: " + text + " | Довжина: " + text.length());

        String encrypted = "";

        try {

            encrypted = multialphabetic.encrypt(text);
            System.out.println("Зашифрований текст: " + encrypted);
        }
        catch (IllegalArgumentException err){

            System.out.println(err.getMessage() + "!");
        }


        String decrypted = "";

        try {

            decrypted = multialphabetic.decrypt(encrypted);
            System.out.println("Розшифрований текст: " + decrypted);
        }
        catch (IllegalArgumentException err){

            System.out.println(err.getMessage() + "!");
        }

    }
}