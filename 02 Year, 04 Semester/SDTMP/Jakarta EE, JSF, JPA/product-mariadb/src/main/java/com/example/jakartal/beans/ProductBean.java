package com.example.jakartal.beans;

import com.example.jakartal.dao.ManufacturerDao;
import com.example.jakartal.dao.ProductDao;
import com.example.jakartal.entities.Manufacturer;
import com.example.jakartal.entities.Product;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Named
@SessionScoped
public class ProductBean implements Serializable {

    @EJB
    private ProductDao productDao;
    @EJB
    ManufacturerDao manufacturerDao;

    private Product product;

    String name;
    double productPrice;
    String manufacturerName;
    LocalDate productExpirationDate;


    @PostConstruct
    public void init() {

        product = new Product();
        name = "";
        manufacturerName = "";
        productPrice = 0.0;
    }

    public Product getProduct() {

        return product;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturer) {
        this.manufacturerName = manufacturer;
    }

    public double getProductPrice() {

        return productPrice;
    }

    public void setProductPrice(double productPrice) {

        this.productPrice = productPrice;
    }

    public LocalDate getProductExpirationDate() {

        return productExpirationDate;
    }

    public void setProductExpirationDate(LocalDate productExpirationDate) {

        this.productExpirationDate = productExpirationDate;
    }

    public void addProduct() {

        Manufacturer manufacturer;

        try {

            manufacturer = manufacturerDao.findByName(manufacturerName);

        } catch (Exception err) {

            manufacturer = new Manufacturer();
            manufacturer.setName(manufacturerName);
            manufacturerDao.add(manufacturer);
        }

        product.setManufacturer(manufacturer);

        productDao.add(product);
        product = new Product();
        manufacturerName = "";

    }

    public void removeProduct(Long id) {

        productDao.delete(id);
    }

    public List<Product> getProducts() {

        return productDao.getProducts();

    }

    public List<Product> findByNameOrderByExpirationDateDesc(String name) {

        return productDao.findByNameOrderByExpirationDateDesc(name);
    }

    // B - Список товарів для заданого найменування, ціна яких не перевищує задану
    public List<Product> findByNameAndPriceLessThanEqual(String name, double price) {

        return productDao.findByNameAndPriceLessThanEqual(name, price);

    }

    // C - Список товарів, термін зберігання яких більше заданого
    public List<Product> findByExpirationDateGreaterThan(LocalDate expirationDate) {

        return productDao.findByExpirationDateGreaterThan(expirationDate);

    }

    // D - Список товарів, впорядкований за зростанням вартості (кількість * ціна),
    // якщо вартість однакова, то за спаданням ціни;
    public List<Product> sortListByPriceAscending() {

        return productDao.sortListByPriceAscending();

    }
}
