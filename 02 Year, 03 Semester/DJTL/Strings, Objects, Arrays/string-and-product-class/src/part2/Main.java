/*
 * Product: id, Найменування, Виробник, Ціна, Термін зберігання, Кількість.
 * Скласти масив об’єктів. Вивести:
 * - список товарів для заданого найменування;
 * - список товарів для заданого найменування, ціна яких не перевищує задану;
 * - список товарів, термін зберігання яких більше заданого.
 */

package part2;

import part2.products.Product;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Main prog = new Main();
        prog.run();
    }

    public void run() {

        Product[] products = fillProductsArray();

        System.out.println("Список товарів заданого найменування");
        outputByName("Молоко", products);
        //System.out.println(Arrays.toString(filterByName("Молоко", products)));

        System.out.println("Список товарів заданого найменування, ціна яких не перевищує задану");
        outputNotExceedPrice("Орешки", 28.5, products);
        //System.out.println(Arrays.toString(filterNotExceedPrice("Орешки" , 28.5, products)));

        System.out.println("Список товарів, термін зберігання яких більше заданого");
        outputPeriodLongerThan(10, products);
        //System.out.println(Arrays.toString(filterPeriodLongerThan(10, products)));


    }

    public Product[] fillProductsArray() {

        return new Product[]{

                new Product(1, "Молоко", "Молочный завод", 30, 2, 30),
                new Product(2, "Орешки", "Орешочный завод", 28.5, 20, 20),
                new Product(3, "Чипсы", "Чипсочный завод", 21.99, 15, 50),
                new Product(4, "Кетчуп", "Кетчупский завод", 49.50, 10, 90)

        };

    }

    public void outputByName(String nameToFind, Product[] products) {

        for (int i = 0; i < products.length; i++) {

            if (products[i].getName().equals(nameToFind)) {

                System.out.println(products[i].toString());
            }
        }


    }



    public Product[] filterByName(String nameToFind, Product[] products) {

        Product[] productsTemp = new Product[products.length];
        int foundedProducts = 0;

        for (int i = 0; i < products.length; i++) {

            if (products[i].getName().equals(nameToFind)) {

                productsTemp[foundedProducts] = products[i];
                foundedProducts++;
            }
        }

        return Arrays.copyOf(productsTemp, foundedProducts);
    }

    public void outputNotExceedPrice(String nameToFind, double price, Product[] products) {

        for (int i = 0; i < products.length; i++) {

            if (products[i].getName().equals(nameToFind) && products[i].getPrice() <= price) {

                System.out.println(products[i].toString());

            }
        }
    }


    public Product[] filterNotExceedPrice(String nameToFind, double price, Product[] products) {

        Product[] productsTemp = new Product[products.length];
        int foundedProducts = 0;

        for (int i = 0; i < products.length; i++) {

            if (products[i].getName().equals(nameToFind) && products[i].getPrice() <= price) {

                productsTemp[foundedProducts] = products[i];
                foundedProducts++;

            }
        }

        return Arrays.copyOf(productsTemp, foundedProducts);
    }

    public void outputPeriodLongerThan(int storagePeriod, Product[] products) {

        for (int i = 0; i < products.length; i++) {

            if (products[i].getStoragePeriod() > storagePeriod) {

                System.out.println(products[i].toString());
            }
        }

    }

    public Product[] filterPeriodLongerThan(int storagePeriod, Product[] products) {

        Product[] productsTemp = new Product[products.length];
        int foundedProducts = 0;

        for (int i = 0; i < products.length; i++) {

            if (products[i].getStoragePeriod() > storagePeriod) {

                productsTemp[foundedProducts] = products[i];
                foundedProducts++;
            }

        }

        return Arrays.copyOf(productsTemp, foundedProducts);
    }
}

