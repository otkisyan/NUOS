package org.example;

// Класс для расчета стоимости заказа
class OrderCalculator {
    public double calculateTotal(Order order) {
        // mapToDouble - преобразует каждый объект Product в его цену (price).
        // Это делает поток из объектов Product потоком чисел типа double, представляющих цены продуктов.
        return order.getProducts().stream().mapToDouble(Product::getPrice).sum();
    }
}