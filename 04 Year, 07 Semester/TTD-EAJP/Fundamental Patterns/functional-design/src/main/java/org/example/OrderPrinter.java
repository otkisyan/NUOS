package org.example;

// Класс для вывода информации о заказе
class OrderPrinter {
    public void printOrder(Order order) {
        System.out.println("Order contains:");
        for (Product product : order.getProducts()) {
            System.out.println(product.getName() + " - $" + product.getPrice());
        }
    }
}