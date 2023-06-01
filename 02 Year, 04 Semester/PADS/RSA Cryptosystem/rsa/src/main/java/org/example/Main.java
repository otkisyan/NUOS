package org.example;

import java.math.BigInteger;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Main main = new Main();
        //main.run();
        main.run2();
    }

    public void run() {

        String firstName = "Vadym";
        String lastName = "Smokvina";

        System.out.println("Ім'я: " + firstName);
        System.out.println("Прізвище: " + lastName);

        int hashValue = calculateHash(firstName, lastName);
        System.out.println("Значення хеш-функції від прізвища та імені: " + hashValue);
        System.out.println();

        RSA rsa = new RSA(500);
        BigInteger message = new BigInteger(String.valueOf(hashValue));
        BigInteger cypher = rsa.encrypt(message);
        BigInteger decrypt = rsa.decrypt(cypher);

        System.out.println("Повідомлення для шифрування: " + message);
        System.out.println("Зашироване повідомлення: " + cypher);
        System.out.println("Розшироване повідомлення: " + decrypt);

    }


    public void run2() {

        String firstName = "Vadym";
        String lastName = "Smokvina";

        byte[] firstNameBytes = firstName.getBytes();
        byte[] lastNameBytes = lastName.getBytes();
        System.out.println("Ім'я: " + firstName + " - ASCII: " + Arrays.toString(firstNameBytes));
        System.out.println("Прізвище: " + lastName + " - ASCII: " + Arrays.toString(lastNameBytes));

        int hashValue = calculateHash(firstName, lastName);
        System.out.println("Значення хеш-функції від прізвища та імені: " + hashValue);
        System.out.println();

        RSA rsa = new RSA(512);
        System.out.println("Відкритий ключ: (e, n)");
        System.out.println("e: " + rsa.getE());
        System.out.println("n (modulus): " + rsa.getModulus());
        System.out.println();

        System.out.println("Закритий ключ: (d, n)");
        System.out.println("d: " + rsa.getD());
        System.out.println("n (modulus): " + rsa.getModulus());
        System.out.println();

        BigInteger firstNameToEncrypt = new BigInteger(firstNameBytes);
        BigInteger encryptedFirstName = rsa.encrypt(firstNameToEncrypt);
        BigInteger decryptedFirstName = rsa.decrypt(encryptedFirstName);
        byte[] decryptedBytesFirstName = decryptedFirstName.toByteArray();

        System.out.println("Ім'я у представленні BigInteger з ASCII кодів: " + firstNameToEncrypt);
        System.out.println("Зашироване ім'я: " + encryptedFirstName);
        System.out.println("Розшироване ім'я: " + decryptedFirstName);
        String decryptedFirstNameString = new String(decryptedBytesFirstName);
        System.out.println(decryptedFirstNameString);
        System.out.println();

        BigInteger lastNameToEncrypt = new BigInteger(lastNameBytes);
        BigInteger encryptedLastName = rsa.encrypt(lastNameToEncrypt);
        BigInteger decryptedLastName = rsa.decrypt(encryptedLastName);
        byte[] decryptedBytesLastName = decryptedLastName.toByteArray();

        System.out.println("Прізвище у представленні BigInteger з ASCII кодів: " + lastNameToEncrypt);
        System.out.println("Зашироване прізвище: " + encryptedLastName);
        System.out.println("Розшироване прізвище: " + decryptedLastName);
        String decryptedLastNameString = new String(decryptedBytesLastName);
        System.out.println(decryptedLastNameString);
        System.out.println();

        int decryptedHashValue = calculateHash(decryptedFirstNameString, decryptedLastNameString);
        System.out.println("Значення хеш-функції від розшифрованого прізвища та імені: " + decryptedHashValue);

    }


    /**
     * Находит значение хэш-функции от имени и фамилии.
     * <br>
     * Значением хэш-функции будет результат исполнения
     * операции <code>xor</code> между буквами фамилии и имени в кодировке ASCII
     *
     * @param firstName имя
     * @param lastName  фамилия
     * @return значение хэш-функции
     */
    public static int calculateHash(String firstName, String lastName) {

        int hashValue = 0;

        for (int i = 0; i < firstName.length(); i++) {

            hashValue ^= firstName.charAt(i);
        }

        for (int i = 0; i < lastName.length(); i++) {

            hashValue ^= lastName.charAt(i);
        }

        return hashValue;
    }

}