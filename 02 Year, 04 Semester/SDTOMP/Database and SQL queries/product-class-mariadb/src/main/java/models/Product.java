package models;

import java.time.LocalDate;
import java.util.Objects;

public class Product {

    private int id;
    private String name;
    private String manufacter;
    private double price;

    LocalDate expirationDate;
    private int amount;

    @Override
    public String toString() {

        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacter='" + manufacter + '\'' +
                ", price=" + price +
                ", storagePeriod=" + expirationDate +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;

        return id == product.id && Double.compare(product.price, price) == 0 && expirationDate == product.expirationDate &&
                amount == product.amount && Objects.equals(name, product.name)
                && Objects.equals(manufacter, product.manufacter);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, manufacter, price, expirationDate, amount);
    }

    public Product(int id, String name, String manufacter, double price, LocalDate expirationDate, int amount) {

        this.id = id;
        this.name = name;
        this.manufacter = manufacter;
        this.price = price;
        this.expirationDate = expirationDate;
        this.amount = amount;
    }

    public Product() {

        this(0, "", "", 0.0, null, 0);
    }

    public int getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getManufacter() {

        return manufacter;
    }

    public void setManufacter(String manufacter) {

        this.manufacter = manufacter;
    }

    public double getPrice() {

        return price;
    }

    public double getCost() {

        return price * amount;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public LocalDate getExpirationDate() {

        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {

        this.expirationDate = expirationDate;
    }

    public int getAmount() {

        return amount;
    }

    public void setAmount(int amount) {

        this.amount = amount;
    }

}
