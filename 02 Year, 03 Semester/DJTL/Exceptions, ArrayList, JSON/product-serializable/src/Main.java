import file.FileProcessor;
import logic.Logic;
import menu.MenuItem;
import products.Product;
import menu.Menu;

import java.util.*;
import java.util.concurrent.Callable;

public class Main {

    public static void main(String[] args) {

        Main main = new Main();
        main.run();

    }

    public void run() {

        Scanner scanner = new Scanner(System.in);
        Logic logic = new Logic();
        FileProcessor fileProcessor = new FileProcessor();
        List<Product> list = new ArrayList<>();

        list = fileProcessor.ReadFile(list);

        List<Product> finalList = list;

        // Метод asList() класса java.util.Arrays используется для возврата списка фиксированного размера, основанного на указанном массиве.
        List<MenuItem> menuItems = Arrays.asList(

                new MenuItem("Вийти з програми", () -> {

                    System.exit(0);

                }),

                new MenuItem("Додати продукт", () -> {

                    String productName = null;
                    Callable<String> nameInput = () -> {

                        System.out.println("Введіть назву продукту: ");
                        String name = scanner.nextLine();

                        if (!logic.inputValidate(name)) {

                            System.out.println("Введена назва містить неприпустимі символи");
                            return null;
                        }

                        return name;
                    };

                    try {

                        do {

                            productName = nameInput.call();

                        } while (productName == null);

                    } catch (Exception err) {

                        err.printStackTrace();
                    }


                    String productManufacter = null;
                    Callable<String> manufacterInput = () -> {

                        System.out.println("Введіть виробника продукту: ");
                        String manufacter = scanner.nextLine();

                        if (!logic.inputValidate(manufacter)) {

                            System.out.println("Введений виробник містить неприпустимі символи");
                            return null;
                        }

                        return manufacter;
                    };

                    try {

                        do {

                            productManufacter = manufacterInput.call();

                        } while (productManufacter == null);

                    } catch (Exception err) {

                        err.printStackTrace();
                    }

                    double productPrice = 0.0;
                    Callable<Double> priceInput = () -> {

                        System.out.println("Введіть ціну продукту: ");
                        double price;

                        try {

                            price = scanner.nextDouble();
                            scanner.nextLine();

                        } catch (InputMismatchException err) {

                            System.out.println("Введена ціна містить неприпустимі символи");
                            scanner.next();

                            return -1.0;
                        }

                        return price;
                    };

                    try {


                        do {

                            productPrice = priceInput.call();

                        } while (productPrice == -1.0);

                    } catch (Exception err) {

                        err.printStackTrace();
                    }

                    int productStoragePeriod = 0;
                    Callable<Integer> storagePeriodInput = () -> {

                        System.out.println("Введіть термін зберігання продукту: ");
                        int storagePeriod;

                        try {

                            storagePeriod = scanner.nextInt();
                            scanner.nextLine();

                        } catch (InputMismatchException err) {

                            System.out.println("Введений термін зберігання містить неприпустимі символи");
                            scanner.next();
                            return -1;
                        }

                        return storagePeriod;
                    };

                    try {


                        do {

                            productStoragePeriod = storagePeriodInput.call();

                        } while (productStoragePeriod == -1);

                    } catch (Exception err) {

                        err.printStackTrace();
                    }

                    int productAmount = 0;
                    Callable<Integer> amountInput = () -> {

                        System.out.println("Введіть кількість продукту: ");

                        int amount;

                        try {

                            amount = scanner.nextInt();
                            scanner.nextLine();

                        } catch (InputMismatchException err) {

                            System.out.println("Введена кількість містить неприпустимі символи");
                            scanner.next();
                            return -1;
                        }

                        return amount;
                    };

                    try {


                        do {

                            productAmount = amountInput.call();

                        } while (productAmount == -1);
                    } catch (Exception err) {

                        err.printStackTrace();
                    }

                    logic.addProduct(finalList, productName, productManufacter, productPrice, productStoragePeriod, productAmount);
                    fileProcessor.WriteFile(finalList);

                }),

                new MenuItem("Видалити продукт", () -> {

                    logic.showAllProducts(finalList);
                    System.out.println("Введіть id продукту для видалення");
                    int productToDelete = scanner.nextInt();
                    scanner.nextLine();

                    logic.removeProduct(productToDelete, finalList);
                    fileProcessor.WriteFile(finalList);

                }),

                new MenuItem("Список всіх продуктів", () -> {

                    logic.showAllProducts(finalList);

                }),

                new MenuItem("Список всіх продуктів для заданого найменування", () -> {

                    System.out.println("Введіть назву продукту для пошуку");
                    String name = scanner.nextLine();

                    System.out.println(logic.filterByName(name, finalList));

                }),

                new MenuItem("Список продуктів для заданого найменування, ціна яких не перевищує задану", () -> {

                    System.out.println("Введіть назву продукту для пошуку");
                    String name = scanner.nextLine();
                    System.out.println("Введіть ціну продукту для пошуку");
                    double price = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println(logic.filterNotExceedPrice(name, price, finalList));

                }),

                new MenuItem("Список товарів, термін зберігання яких більше заданого", () -> {

                    System.out.println("Введіть термін придатності продукту для пошуку");
                    int storagePeriod = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println(logic.filterPeriodLongerThan(storagePeriod, finalList));

                })
        );


        Menu menu = new Menu(menuItems);
        menu.run();

    }

}
