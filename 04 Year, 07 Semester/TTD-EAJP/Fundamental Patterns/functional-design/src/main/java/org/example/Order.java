package org.example;

import java.util.ArrayList;
import java.util.List;

// Класс для работы с заказами
class Order {
    private List<Product> products;

    public Order() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }
}