package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarSinglyLinkedListTest {

    CarSinglyLinkedList carSinglyLinkedList;

    @BeforeEach
    void setUp() {

        carSinglyLinkedList = new CarSinglyLinkedList();
    }

    @Test
    void addFirstCar() {

        Car car1 = new Car("Toyota", "123456789", 1500, 2019);
        Car car2 = new Car("Honda", "234567890", 1700, 2020);
        carSinglyLinkedList.addFirstCar(car1);
        carSinglyLinkedList.addFirstCar(car2);

        assertEquals(car2, carSinglyLinkedList.getCarByIndex(0));
    }

    @Test
    void addLastCar() {

        Car car1 = new Car("Toyota", "123456789", 1500, 2019);
        Car car2 = new Car("Honda", "234567890", 1700, 2020);
        carSinglyLinkedList.addLastCar(car1);
        carSinglyLinkedList.addLastCar(car2);
        assertEquals(car2, carSinglyLinkedList.getCarByIndex(1));

    }

    @Test
    void insertCarAt() {

        Car car1 = new Car("Toyota", "123456789", 1500, 2019);
        Car car2 = new Car("Honda", "234567890", 1700, 2020);
        Car car3 = new Car("Nissan", "345678901", 1600, 2018);
        carSinglyLinkedList.addLastCar(car1);
        carSinglyLinkedList.addLastCar(car2);
        carSinglyLinkedList.insertCarAt(1, car3);
        assertEquals(car3, carSinglyLinkedList.getCarByIndex(1));

    }

    @Test
    void removeFirstCar() {

        Car car1 = new Car("Toyota", "123456789", 1500, 2019);
        Car car2 = new Car("Honda", "234567890", 1700, 2020);
        carSinglyLinkedList.addLastCar(car1);
        carSinglyLinkedList.addLastCar(car2);
        carSinglyLinkedList.removeFirstCar();
        assertEquals(car2, carSinglyLinkedList.getCarByIndex(0));
    }

    @Test
    void removeLastCar() {

        Car car1 = new Car("Toyota", "123456789", 1500, 2019);
        Car car2 = new Car("Honda", "234567890", 1700, 2020);
        carSinglyLinkedList.addLastCar(car1);
        carSinglyLinkedList.addLastCar(car2);
        carSinglyLinkedList.removeLastCar();
        assertEquals(car1, carSinglyLinkedList.getCarByIndex(0));
    }

    @Test
    void removeCarAt() {

        Car car1 = new Car("Toyota", "123456789", 1500, 2019);
        Car car2 = new Car("Honda", "234567890", 1700, 2020);
        Car car3 = new Car("Nissan", "345678901", 1600, 2018);
        carSinglyLinkedList.addLastCar(car1);
        carSinglyLinkedList.addLastCar(car2);
        carSinglyLinkedList.addLastCar(car3);
        carSinglyLinkedList.removeCarAt(1);
        assertEquals(car3, carSinglyLinkedList.getCarByIndex(1));

    }

    @Test
    void getCarByIndex() {

        Car testCar = new Car("Toyota", "123456789", 1500, 2019);
        Car car1 = new Car("Toyota", "123456789", 1500, 2019);
        Car car2 = new Car("Honda", "234567890", 1700, 2020);
        carSinglyLinkedList.addLastCar(car1);
        carSinglyLinkedList.addLastCar(car2);
        assertEquals(testCar, carSinglyLinkedList.getCarByIndex(0));
    }

    @Test
    void getCarsOfMark() {

        Car car1 = new Car("Toyota", "126416789", 1200, 2003);
        Car car2 = new Car("Toyota", "123456789", 1500, 2019);
        Car car3 = new Car("Honda", "234567890", 1700, 2020);
        carSinglyLinkedList.addLastCar(car1);
        carSinglyLinkedList.addLastCar(car2);
        CarSinglyLinkedList carsOfMark = carSinglyLinkedList.getCarsOfMark("Toyota");
        assertTrue(carsOfMark.contains(car1));
        assertTrue(carsOfMark.contains(car2));
        assertFalse(carsOfMark.contains(car3));
    }

    @Test
    void getCarsWithSerialDigit() {

        Car car1 = new Car("Toyota", "126416089", 1200, 2003);
        Car car2 = new Car("Toyota", "123456789", 1500, 2019);
        Car car3 = new Car("Honda", "234567894", 1700, 2020);
        Car car4 = new Car("Nissan", "345678901", 1600, 2018);
        carSinglyLinkedList.addLastCar(car1);
        carSinglyLinkedList.addLastCar(car2);
        carSinglyLinkedList.addLastCar(car3);
        carSinglyLinkedList.addLastCar(car4);
        CarSinglyLinkedList carsWithSerialDigit = carSinglyLinkedList.getCarsWithSerialDigit('0');
        assertTrue(carsWithSerialDigit.contains(car1));
        assertTrue(carsWithSerialDigit.contains(car4));
    }

    @Test
    void getCarsWithDisplacementAndExploitation() {

        Car car1 = new Car("Toyota", "123456789", 1500, 2014);
        Car car2 = new Car("Honda", "234567894", 1500, 2017);
        Car car3 = new Car("Nissan", "345678901", 1600, 2008);
        carSinglyLinkedList.addLastCar(car1);
        carSinglyLinkedList.addLastCar(car2);
        carSinglyLinkedList.addLastCar(car3);
        double engineDisplacement = 1400;
        int yearsOfExploitation = 10;

        CarSinglyLinkedList carsWithEngineDisplacementAndExploitation = carSinglyLinkedList.getCarsWithDisplacementAndExploitation(engineDisplacement, yearsOfExploitation);
        assertTrue(carsWithEngineDisplacementAndExploitation.contains(car1));
        assertTrue(carsWithEngineDisplacementAndExploitation.contains(car2));
        assertFalse(carsWithEngineDisplacementAndExploitation.contains(car3));
    }
}