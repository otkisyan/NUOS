package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Product implements Serializable {

    @JsonIgnore
    transient private int id;
    private static int tempId;
    private String name;
    private String manufacter;
    private double price;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate storagePeriod;
    private int amount;

    @Override
    public String toString() {

        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacter='" + manufacter + '\'' +
                ", price=" + price +
                ", storagePeriod=" + storagePeriod +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;

        return id == product.id && Double.compare(product.price, price) == 0 && storagePeriod == product.storagePeriod &&
                amount == product.amount && Objects.equals(name, product.name)
                && Objects.equals(manufacter, product.manufacter);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, manufacter, price, storagePeriod, amount);
    }

    public Product(String name, String manufacter, double price, LocalDate storagePeriod, int amount) {

        tempId++;
        this.id = tempId;
        this.name = name;
        this.manufacter = manufacter;
        this.price = price;
        this.storagePeriod = storagePeriod;
        this.amount = amount;
    }

    public Product() {

        this("", "", 0.0, null, 0);
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

    @JsonIgnore
    public double getCost(){

       return price * amount;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public LocalDate getStoragePeriod() {

        return storagePeriod;
    }

    public void setStoragePeriod(LocalDate storagePeriod) {

        this.storagePeriod = storagePeriod;
    }

    public int getAmount() {

        return amount;
    }

    public void setAmount(int amount) {

        this.amount = amount;
    }

}
