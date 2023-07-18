package part2.products;

import java.util.Objects;

public class Product {

    private int id;
    private String name;
    private String manufacter;
    private double price;
    private int storagePeriod;
    private int amount;

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


    public Product(int id, String name, String manufacter, double price, int storagePeriod, int amount) {

        this.id = id;
        this.name = name;
        this.manufacter = manufacter;
        this.price = price;
        this.storagePeriod = storagePeriod;
        this.amount = amount;
    }

    public Product() {

        this(0, "", "", 0.0, 0, 0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStoragePeriod() {
        return storagePeriod;
    }

    public void setStoragePeriod(int storagePeriod) {
        this.storagePeriod = storagePeriod;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
