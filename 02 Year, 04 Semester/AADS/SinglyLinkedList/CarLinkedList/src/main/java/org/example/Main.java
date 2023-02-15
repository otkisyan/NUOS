package org.example;

import java.util.*;

/*
 * Разработать программу для работы с односвязными списками.
 * Каждый элемент списка содержит информационные поля (атрибуты) некоторого типа (число, символ, строка) и адресное поле.
 *
 * Программа должна обеспечивать создание списка (минимальное количество элементов - 10),
 * добавление элементов в любую позицию списка, поиск по списку,
 * удаление элементов из любой позиции списка, удаление списка.
 */
public class Main {
    public static void main(String[] args) {

        CarLinkedList carLinkedList = new CarLinkedList();
        List<Car> cars = new ArrayList<>(Arrays.asList(

                new Car("Toyota", "123456789", 1500, 2019),
                new Car("Honda", "234567890", 1700, 2020),
                new Car("Nissan", "345678901", 1600, 2018),
                new Car("Mazda", "456789012", 1700, 2017),
                new Car("Ford", "567890123", 1600, 2020),
                new Car("Chevrolet", "678901234", 1500, 2019),
                new Car("Kia", "789012345", 1700, 2018),
                new Car("Hyundai", "890123456", 1600, 2017),
                new Car("Jeep", "901234567", 1500, 2020),
                new Car("Jeep", "102264867", 1500, 2020),
                new Car("Dodge", "062135678", 1700, 2019)

        ));

        for (Car car : cars) {

            carLinkedList.addLastCar(car);
        }

        Scanner input = new Scanner(System.in);

        int option = 0;

        while (option != 11) {

            System.out.println("Select an option from the menu:");
            System.out.println("1. Add auto to the beginning of the list");
            System.out.println("2. Add auto to the end of the list");
            System.out.println("3. Insert auto by index");
            System.out.println("4. Delete first car in the list");
            System.out.println("5. Delete the last car in the list");
            System.out.println("6. Delete car by index");
            System.out.println("7. List of cars of a certain mark");
            System.out.println("8. List of cars with a certain number in the serial number");
            System.out.println("9. List of cars that have an engine capacity of more than N liters and have been in exploitation for less than K years");
            System.out.println("10. Print entire list");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            option = input.nextInt();
            input.nextLine();

            switch (option) {

                case 1 -> {

                    System.out.println("Enter the mark of the car: ");
                    String mark = input.nextLine();
                    System.out.println("Enter the serial number of the car: ");
                    String serialNumber = input.nextLine();
                    System.out.println("Enter the engine displacement of the car: ");
                    double engineDisplacement = input.nextDouble();

                    System.out.println("Enter the year of manufacture of the car: ");
                    int year = input.nextInt();
                    Car car = new Car(mark, serialNumber, engineDisplacement, year);

                    carLinkedList.addFirstCar(car);
                }
                case 2 -> {

                    System.out.println("Enter the mark of the car: ");
                    String mark = input.nextLine();
                    System.out.println("Enter the serial number of the car: ");
                    String serialNumber = input.nextLine();
                    System.out.println("Enter the engine displacement of the car: ");
                    double engineDisplacement = input.nextDouble();

                    System.out.println("Enter the year of manufacture of the car: ");
                    int year = input.nextInt();
                    Car car = new Car(mark, serialNumber, engineDisplacement, year);

                    carLinkedList.addLastCar(car);
                }
                case 3 -> {

                    System.out.println("Enter the mark of the car: ");
                    String mark = input.nextLine();
                    System.out.println("Enter the serial number of the car: ");
                    String serialNumber = input.nextLine();
                    System.out.println("Enter the engine displacement of the car: ");
                    double engineDisplacement = input.nextDouble();

                    System.out.println("Enter the year of manufacture of the car: ");
                    int year = input.nextInt();
                    Car car = new Car(mark, serialNumber, engineDisplacement, year);

                    System.out.println("Enter index to insert car");
                    int index = input.nextInt();

                    carLinkedList.insertCarAt(index, car);
                }
                case 4 -> {

                    carLinkedList.removeFirstCar();
                }
                case 5 -> {

                    carLinkedList.removeLastCar();
                }
                case 6 -> {

                    System.out.print("Enter the index of the car to delete: ");
                    int index = input.nextInt();
                    carLinkedList.removeCarAt(index);
                }
                case 7 -> {

                    System.out.print("Enter the mark of the car: ");
                    String mark = input.nextLine();
                    CarLinkedList carsOfMark = carLinkedList.getCarsOfMark(mark);
                    carsOfMark.print();
                }
                case 8 -> {

                    System.out.print("Enter the char of the serial number: ");
                    char serialNumber = input.next().charAt(0);
                    CarLinkedList carsOfSerialNumber = carLinkedList.getCarsWithSerialDigit(serialNumber);
                    carsOfSerialNumber.print();
                }

                case 9 -> {

                    System.out.print("Enter the engine displacement: ");
                    double engineDisplacement = input.nextDouble();

                    System.out.print("Enter the years of exploitation: ");
                    int yearsOfExploitation = input.nextInt();

                    CarLinkedList carsWithEngineDisplacementAndExploitation = carLinkedList.getCarsWithDisplacementAndExploitation(engineDisplacement, yearsOfExploitation);
                    carsWithEngineDisplacementAndExploitation.print();
                }

                case 10 -> {

                    carLinkedList.print();
                }

                case 11 -> {

                    System.out.println("Exiting program.");
                    System.exit(0);
                }

                default -> System.out.println("Invalid option. Try again.");
            }
        }

        input.close();
    }

}