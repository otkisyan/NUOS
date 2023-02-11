package models;

import java.time.LocalDate;
import java.util.*;

public class Logic {

    public void addProduct(List<Product> list, String name, String manufacter, double price, LocalDate storagePeriod, int amount) {

        Product product = new Product(name, manufacter, price, storagePeriod, amount);
        list.add(product);
    }

    public void removeProduct(int productToDelete, List<Product> list) {

        if (list.isEmpty()) {

            throw new IllegalArgumentException();
        }

        boolean remove = list.removeIf(product -> product.getId() == productToDelete);

        if (!remove) {

            throw new NoSuchElementException();
        }

    }

    public void showAllProducts(List<Product> list) {

        if (list.isEmpty()) {

            return;
        }

        list.forEach(System.out::println);
    }

    // A - Список товарів для заданого найменування в порядку спадання терміну зберігання
    public List<Product> filterByName(String nameToFind, List<Product> list) {

        if (list.isEmpty() || nameToFind.isEmpty()) {

            throw new IllegalArgumentException();
        }

        List<Product> tempList = new ArrayList<>();

        list.forEach(product -> {

            if (product.getName().equals(nameToFind))

                tempList.add(product);

        });

        tempList.sort(Comparator.comparing(Product::getStoragePeriod).reversed());

        return tempList;
    }

    // B - Список товарів для заданого найменування, ціна яких не перевищує задану
    public List<Product> filterNotExceedPrice(String nameToFind, double price, List<Product> list) {

        if (list.isEmpty() || nameToFind.isEmpty()) {

            throw new IllegalArgumentException();
        }

        List<Product> tempList = new ArrayList<>();

        list.forEach(product -> {

            if (product.getName().equals(nameToFind) && product.getPrice() <= price)

                tempList.add(product);
        });

        return tempList;
    }

    // C - Список товарів, термін зберігання яких більше заданого
    public List<Product> filterPeriodLongerThan(LocalDate storagePeriod, List<Product> list) {

        if (list.isEmpty()) {

            throw new IllegalArgumentException();
        }

        List<Product> tempList = new ArrayList<>();


        list.forEach(product -> {

            if (product.getStoragePeriod().isAfter(storagePeriod))

                tempList.add(product);
        });

        return tempList;

    }

    // D - Список товарів, впорядкований за зростанням вартості (кількість * ціна), якщо вартість однакова, то за спаданням ціни;
    public List<Product> sortListByPriceAscending(List<Product> list) {

        if (list.isEmpty()) {

            throw new IllegalArgumentException();
        }

        List<Product> tempList = new ArrayList<>(list);

        tempList.sort((o1, o2) -> {

            if (o1.getCost() == o2.getCost()) {

                return Double.compare(o2.getPrice(), o1.getPrice());
            }

            return Double.compare(o1.getCost(), o2.getCost());
        });

        return tempList;
    }

    // E - Список виробників продуктів, зареєстрованих в програмі;
    public Set<String> getSetOfManufacters(List<Product> list) {

        if (list.isEmpty()) {

            throw new IllegalArgumentException();
        }

        Set<String> manufacters = new HashSet<>();

        list.forEach(product -> {

            String manufacter = product.getManufacter();
            manufacters.add(manufacter);

        });

        return manufacters;
    }

    // F - Для кожного виробника вивести список продуктів, які він виробляє.
    public Map<String, List<Product>> filterProductsByManufacters(List<Product> list) {

        if (list.isEmpty()) {

            throw new IllegalArgumentException();
        }

        Map<String, List<Product>> manufacterProducts = new HashMap<>();

        list.forEach(product -> {

            String manufacter = product.getManufacter();

            if (!manufacterProducts.containsKey(manufacter)) {

                manufacterProducts.put(manufacter, new ArrayList<>());
            }

            manufacterProducts.get(manufacter).add(product);

        });

        return manufacterProducts;
    }

}
