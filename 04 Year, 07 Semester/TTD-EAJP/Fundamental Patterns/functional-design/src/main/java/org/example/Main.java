package org.example;

public class Main {
    public static void main(String[] args) {
        // Создание продуктов
        Product product1 = new Product("Laptop", 999.99);
        Product product2 = new Product("Mouse", 19.99);

        // Создание заказа
        Order order = new Order();
        order.addProduct(product1);
        order.addProduct(product2);

        // Расчет стоимости заказа
        OrderCalculator calculator = new OrderCalculator();
        double total = calculator.calculateTotal(order);

        // Печать информации о заказе
        OrderPrinter printer = new OrderPrinter();
        printer.printOrder(order);

        // Вывод общей стоимости
        System.out.println("Total price: $" + total);
    }
}