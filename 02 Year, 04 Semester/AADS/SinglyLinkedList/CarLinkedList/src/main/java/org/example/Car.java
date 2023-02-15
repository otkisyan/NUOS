package org.example;

import java.util.Objects;

public class Car {
    private String mark;
    private String serialNumber;
    private double engineDisplacement;
    private int yearOfManufacture;

    public Car(String mark, String serialNumber, double engineDisplacement, int yearOfManufacture) {

        this.mark = mark;
        this.serialNumber = serialNumber;
        this.engineDisplacement = engineDisplacement;
        this.yearOfManufacture = yearOfManufacture;
    }

    @Override
    public String toString() {
        return "Car{" +
                "mark='" + mark + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", engineDisplacement=" + engineDisplacement +
                ", yearOfManufacture=" + yearOfManufacture +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(car.engineDisplacement, engineDisplacement) == 0 && yearOfManufacture == car.yearOfManufacture && Objects.equals(mark, car.mark) && Objects.equals(serialNumber, car.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mark, serialNumber, engineDisplacement, yearOfManufacture);
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public double getEngineDisplacement() {
        return engineDisplacement;
    }

    public void setEngineDisplacement(double engineDisplacement) {
        this.engineDisplacement = engineDisplacement;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }
}
