package models;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

        return list.stream()
                .filter(product -> product.getName().equals(nameToFind))
                .sorted(Comparator.comparing(Product::getStoragePeriod).reversed())
                .collect(Collectors.toList());
    }

    // B - Список товарів для заданого найменування, ціна яких не перевищує задану
    public List<Product> filterNotExceedPrice(String nameToFind, double price, List<Product> list) {

        if (list.isEmpty() || nameToFind.isEmpty()) {

            throw new IllegalArgumentException();
        }

        return list.stream()
                .filter(product -> product.getName().equals(nameToFind) && product.getPrice() <= price)
                .collect(Collectors.toList());
    }

    // C - Список товарів, термін зберігання яких більше заданого
    public List<Product> filterPeriodLongerThan(LocalDate storagePeriod, List<Product> list) {

        if (list.isEmpty()) {

            throw new IllegalArgumentException();
        }

        return list.stream()
                .filter(product -> product.getStoragePeriod().isAfter(storagePeriod))
                .collect(Collectors.toList());
    }

    // D - Список товарів, впорядкований за зростанням вартості (кількість * ціна), якщо вартість однакова, то за спаданням ціни;
    public List<Product> sortListByPriceAscending(List<Product> list) {

        if (list.isEmpty()) {

            throw new IllegalArgumentException();
        }

        /*
        return list.stream()
                .sorted(((o1, o2) -> {

                    if (o1.getCost() == o2.getCost()) {

                        return Double.compare(o2.getPrice(), o1.getPrice());

                    }

                    return Double.compare(o1.getCost(), o2.getCost());

                }))

                .collect(Collectors.toList());
                */


        return list.stream()
                .sorted(Comparator.comparingDouble(Product::getCost)
                        .thenComparingDouble(Product::getPrice).reversed())
                .collect(Collectors.toList());
    }

    // E - Список виробників продуктів, зареєстрованих в програмі;
    public Set<String> getSetOfManufacters(List<Product> list) {

        if (list.isEmpty()) {

            throw new IllegalArgumentException();
        }

       /* return list.stream()
                .map(Product::getManufacter)
                .distinct()
                .collect(Collectors.toList())*/

        return list.stream()
                .map(Product::getManufacter)
                .collect(Collectors.toSet());
    }

    // F - Для кожного виробника вивести список продуктів, які він виробляє.
    public Map<String, List<Product>> filterProductsByManufacters(List<Product> list) {

        if (list.isEmpty()) {

            throw new IllegalArgumentException();
        }

        return list.stream()
                .collect(Collectors.groupingBy(Product::getManufacter));
    }

}
