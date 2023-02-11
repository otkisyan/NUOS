package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductsSingleton {

    private static ProductsSingleton instance;
    private ObservableList<Product> productsList;

    private ProductsSingleton() {

        FileProcessor fileProcessor = new FileProcessor();
        fileProcessor.checkFileExists();
        productsList = FXCollections.observableArrayList(fileProcessor.readFile());
    }

    public static ProductsSingleton getInstance() {

        if (instance == null) {

            instance = new ProductsSingleton();
        }

        return instance;
    }

    public ObservableList<Product> getProductsList(){

        return productsList;
    }
}
