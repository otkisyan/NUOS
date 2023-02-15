package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarLinkedListTest {

    CarLinkedList carLinkedList;

    @BeforeEach
    void setUp() {

        carLinkedList = new CarLinkedList();
    }

    @Test
    void addFirstCar() {

        Car car1 = new Car("Toyota", "123456789", 1500, 2019);
        Car car2 = new Car("Honda", "234567890", 1700, 2020);
        carLinkedList.addFirstCar(car1);
        carLinkedList.addFirstCar(car2);

        assertEquals(car2, carLinkedList.getCarByIndex(0));
    }

    @Test
    void addLastCar() {

        Car car1 = new Car("Toyota", "123456789", 1500, 2019);
        Car car2 = new Car("Honda", "234567890", 1700, 2020);
        carLinkedList.addLastCar(car1);
        carLinkedList.addLastCar(car2);
        assertEquals(car2, carLinkedList.getCarByIndex(1));

    }

    @Test
    void insertCarAt() {

        Car car1 = new Car("Toyota", "123456789", 1500, 2019);
        Car car2 = new Car("Honda", "234567890", 1700, 2020);
        Car car3 = new Car("Nissan", "345678901", 1600, 2018);
        carLinkedList.addLastCar(car1);
        carLinkedList.addLastCar(car2);
        carLinkedList.insertCarAt(1, car3);
        assertEquals(car3, carLinkedList.getCarByIndex(1));

    }

    @Test
    void removeFirstCar() {

        Car car1 = new Car("Toyota", "123456789", 1500, 2019);
        Car car2 = new Car("Honda", "234567890", 1700, 2020);
        carLinkedList.addLastCar(car1);
        carLinkedList.addLastCar(car2);
        carLinkedList.removeFirstCar();
        assertEquals(car2, carLinkedList.getCarByIndex(0));
    }

    @Test
    void removeLastCar() {

        Car car1 = new Car("Toyota", "123456789", 1500, 2019);
        Car car2 = new Car("Honda", "234567890", 1700, 2020);
        carLinkedList.addLastCar(car1);
        carLinkedList.addLastCar(car2);
        carLinkedList.removeLastCar();
        assertEquals(car1, carLinkedList.getCarByIndex(0));
    }

    @Test
    void removeCarAt() {

        Car car1 = new Car("Toyota", "123456789", 1500, 2019);
        Car car2 = new Car("Honda", "234567890", 1700, 2020);
        Car car3 = new Car("Nissan", "345678901", 1600, 2018);
        carLinkedList.addLastCar(car1);
        carLinkedList.addLastCar(car2);
        carLinkedList.addLastCar(car3);
        carLinkedList.removeCarAt(1);
        assertEquals(car3, carLinkedList.getCarByIndex(1));

    }

    @Test
    void getCarByIndex() {

        Car testCar = new Car("Toyota", "123456789", 1500, 2019);
        Car car1 = new Car("Toyota", "123456789", 1500, 2019);
        Car car2 = new Car("Honda", "234567890", 1700, 2020);
        carLinkedList.addLastCar(car1);
        carLinkedList.addLastCar(car2);
        assertEquals(testCar, carLinkedList.getCarByIndex(0));
    }

    @Test
    void getCarsOfMark() {

        Car car1 = new Car("Toyota", "126416789", 1200, 2003);
        Car car2 = new Car("Toyota", "123456789", 1500, 2019);
        Car car3 = new Car("Honda", "234567890", 1700, 2020);
        carLinkedList.addLastCar(car1);
        carLinkedList.addLastCar(car2);
        List<Car> carsOfMark = carLinkedList.getCarsOfMark("Toyota");
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
        carLinkedList.addLastCar(car1);
        carLinkedList.addLastCar(car2);
        carLinkedList.addLastCar(car3);
        carLinkedList.addLastCar(car4);
        List<Car> carsOfMark = carLinkedList.getCarsWithSerialDigit('0');
        assertTrue(carsOfMark.contains(car1));
        assertTrue(carsOfMark.contains(car4));
    }

    @Test
    void getCarsWithDisplacementAndExploitation(){

        Car car1 = new Car("Toyota", "123456789", 1500, 2014);
        Car car2 = new Car("Honda", "234567894", 1500, 2017);
        Car car3 = new Car("Nissan", "345678901", 1600, 2008);
        carLinkedList.addLastCar(car1);
        carLinkedList.addLastCar(car2);
        carLinkedList.addLastCar(car3);
        double engineDisplacement = 1400;
        int yearsOfExploitation = 10;

        List<Car> carsWithEngineDisplacementAndExploitation = carLinkedList.getCarsWithDisplacementAndExploitation(engineDisplacement, yearsOfExploitation);
        assertTrue(carsWithEngineDisplacementAndExploitation.contains(car1));
        assertTrue(carsWithEngineDisplacementAndExploitation.contains(car2));
        assertFalse(carsWithEngineDisplacementAndExploitation.contains(car3));
    }
}