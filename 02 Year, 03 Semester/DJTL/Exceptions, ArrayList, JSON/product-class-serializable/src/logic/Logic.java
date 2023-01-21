package logic;

import products.Product;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.sort;

public class Logic {

    public void addProduct(List<Product> list, String name, String manufacter, double price, int storagePeriod, int amount) {

        String uuid = UUID.randomUUID().toString();
        Product product = new Product(uuid, name, manufacter, price, storagePeriod, amount);
        list.add(product);
    }


    public void removeProduct(int productToDelete, List<Product> list) {

        if (list.isEmpty()) {

            return;
        }

       /* int index = -1;

        for (int i = 0; i < list.size(); i++){

            if (list.get(i).getId() == productToDelete){

                index = i;
            }
        }

        if (index == -1) {

            System.out.println("Продукту з таким id не існує");
            return;

        }*/

        //list.remove(index);


        boolean remove = list.removeIf(product -> product.getId() == productToDelete);

        if (!remove) {

            return;
        }

    }

    public void showAllProducts(List<Product> list) {

        if (list.isEmpty()) {

            return;
        }

        list.forEach(System.out::println);
    }


    // Список товарів для заданого найменування
    public List<Product> filterByName(String nameToFind, List<Product> list) {

        if (list.isEmpty()) {

            return null;
        }

        List<Product> tempList = new ArrayList<>();

        list.forEach(product -> {

            if (product.getName().equals(nameToFind))

                tempList.add(product);

        });

    

        return tempList;
    }

    // Список товарів для заданого найменування, ціна яких не перевищує задану
    public List<Product> filterNotExceedPrice(String nameToFind, double price, List<Product> list) {

        if (list.isEmpty()) {

            return null;
        }

        List<Product> tempList = new ArrayList<>();

        list.forEach(product -> {

            if (product.getName().equals(nameToFind) && product.getPrice() <= price)

                tempList.add(product);
        });


        return tempList;
    }

    // Список товарів, термін зберігання яких більше заданого
    public List<Product> filterPeriodLongerThan(int storagePeriod, List<Product> list) {

        if (list.isEmpty()) {

            return null;
        }

        List<Product> tempList = new ArrayList<>();

       /* tempList = list.stream()
                .filter(product -> product.getStoragePeriod() > storagePeriod)
                .collect(Collectors.toList());*/


        list.forEach(product -> {

            if (product.getStoragePeriod() > storagePeriod)

                tempList.add(product);
        });

        return tempList;

    }

    public boolean inputValidate(String input) {

        if (input.contains(" ") || input.contains("\n") || input.contains("\t") || input.isEmpty()) {

            return false;
        }

        return true;
    }

    public List<Product> sortListByPrice(List<Product> list) {

        return list.stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());
    }
}
