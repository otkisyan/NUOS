package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarLinkedListTest {

    CarLinkedList carLinkedList;

    @BeforeEach
    void setUp() {

        carLinkedList = new CarLinkedList();

    }

    @Test
    void addFirst() {

        Car car1 = new Car("Toyota", "123456789", 1500, 2019);
        Car car2 = new Car("Honda", "234567890", 1700, 2020);
        carLinkedList.addFirst(car1);
        carLinkedList.addFirst(car2);

        assertEquals(car2, carLinkedList.getByIndex(0));
    }

    @Test
    void addLast() {

        Car car1 = new Car("Toyota", "123456789", 1500, 2019);
        Car car2 = new Car("Honda", "234567890", 1700, 2020);
        carLinkedList.addLast(car1);
        carLinkedList.addLast(car2);
        assertEquals(car2, carLinkedList.getByIndex(1));
    }

    @Test
    void insertByIndex() {

        Car car1 = new Car("Toyota", "123456789", 1500, 2019);
        Car car2 = new Car("Honda", "234567890", 1700, 2020);
        Car car3 = new Car("Nissan", "345678901", 1600, 2018);
        carLinkedList.addLast(car1);
        carLinkedList.addLast(car2);
        carLinkedList.insertAt(1, car3);
        assertEquals(car3, carLinkedList.getByIndex(1));

    }

    @Test
    void removeFirst() {

        Car car1 = new Car("Toyota", "123456789", 1500, 2019);
        Car car2 = new Car("Honda", "234567890", 1700, 2020);
        carLinkedList.addLast(car1);
        carLinkedList.addLast(car2);
        carLinkedList.removeFirst();
        assertEquals(car2, carLinkedList.getByIndex(0));
        assertEquals(1, carLinkedList.getSize());
    }

    @Test
    void removeLast() {

        Car car1 = new Car("Toyota", "123456789", 1500, 2019);
        Car car2 = new Car("Honda", "234567890", 1700, 2020);
        carLinkedList.addLast(car1);
        carLinkedList.addLast(car2);
        carLinkedList.removeLast();
        assertEquals(car1, carLinkedList.getByIndex(0));
        assertEquals(1, carLinkedList.getSize());

    }

    @Test
    void removeAt() {

        Car car1 = new Car("Toyota", "123456789", 1500, 2019);
        Car car2 = new Car("Honda", "234567890", 1700, 2020);
        Car car3 = new Car("Nissan", "345678901", 1600, 2018);
        carLinkedList.addLast(car1);
        carLinkedList.addLast(car2);
        carLinkedList.addLast(car3);
        carLinkedList.removeAt(1);
        assertEquals(car3, carLinkedList.getByIndex(1));
        assertEquals(2, carLinkedList.getSize());
    }

    @Test
    void getByIndex() {

        Car testCar = new Car("Toyota", "123456789", 1500, 2019);
        Car car1 = new Car("Toyota", "123456789", 1500, 2019);
        Car car2 = new Car("Honda", "234567890", 1700, 2020);
        carLinkedList.addLast(car1);
        carLinkedList.addLast(car2);
        assertEquals(testCar, carLinkedList.getByIndex(0));
    }

}