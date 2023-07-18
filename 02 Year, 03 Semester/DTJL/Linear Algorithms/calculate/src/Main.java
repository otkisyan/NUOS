/*
* 1. Створити клас, що має методи для обчислення на ЕОМ значень змінних, що зазначені у таблиці варіантів, за даними розрахунковими формулами і наборами вхідних даних.
* 2. Доповнити клас методом, що виводить на екран значення вхідних даних і результати обчислень, супроводжуючи вивід найменуваннями виведених змінних.
* 3. Додати в клас метод, що друкує поточну дату і час у вказаному форматі.
* 4. Доповнити клас методом введення початкових значень.
* 5. Створити метод, що вводить дані, обчислює потрібні значення за вказаними формулами, та друкує потрібні результати.
* 6. Доповнити клас методом main, що є необхідним для використання класу, як автономної програми, та виконати цю програму.
*
* y = синус 3 степени (x в квадрате + а) в квадрате;
* z = (x в квадрате деленное на a) + косинус (x + b) в третей степени
* a = 1.5, b = 0.004; x = 0.2
*
* Время в формате чч::мм и дата в формате месяц::гг
 */


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Math.*;


public class Main {

    public static void main(String[] args) {
        Main prog = new Main();
        prog.run();
    }

    private double calculateY(double a, double b, double x) {

        //return pow(sin(pow(pow(x, 2) + a, 2)), 3) - sqrt((x / b));
        double t = x * x + a;
        double temp = sin(t * t);
        return temp * temp * temp - sqrt(x / b);
    }

    private double calculateZ(double a, double b, double x) {
        
        //return (pow(x, 2) / a) + (cos(pow(x + b, 3)));
        return ((x * x) / a) + cos((x + b) * (x + b) * (x + b));
    }

    private void print(double a, double b, double x, double y, double z) {
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("x = " + x);

        System.out.println("y = " + y);
        System.out.println("z = " + z);
    }

    private void printDate() {
        LocalDateTime date = LocalDateTime.now();
        System.out.println(date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
    }

    private void run() {
        double a = 1.1;
        double b = 0.004;
        double x = 0.2;
        double y = calculateY(a, b, x);
        double z = calculateZ(a, b, x);

        print(a, b, x, y, z);
        printDate();
    }
}
