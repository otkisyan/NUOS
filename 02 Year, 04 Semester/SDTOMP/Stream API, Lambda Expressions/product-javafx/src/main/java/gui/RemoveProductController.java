package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.FileProcessor;
import models.Product;
import models.ProductsSingleton;

import java.io.IOException;
import java.time.LocalDate;

public class RemoveProductController {

    @FXML
    private TableView<Product> table;

    @FXML
    private TableColumn<Product, Integer> amountColumn;
    @FXML
    private TableColumn<Product, Integer> idColumn;
    @FXML
    private TableColumn<Product, String> manufacterColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private TableColumn<Product, LocalDate> storageColumn;

    @FXML
    private Button deleteButton;

    @FXML
    private Button backHome;

    @FXML
    void initialize() {

        FileProcessor fileProcessor = new FileProcessor();
        ProductsSingleton productsSingleton = ProductsSingleton.getInstance();
        ObservableList<Product> productsList = productsSingleton.getProductsList();

        idColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        manufacterColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("manufacter"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        storageColumn.setCellValueFactory(new PropertyValueFactory<Product, LocalDate>("storagePeriod"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("amount"));

        table.setItems(productsList);

        deleteButton.setOnAction(event -> {

            Product selectedItem = table.getSelectionModel().getSelectedItem();
            table.getItems().remove(selectedItem);

            productsList.remove(selectedItem);
            fileProcessor.writeFile(productsList);

        });

        backHome.setOnAction(event -> {

            try {

                App.setRoot("/primary");


            } catch (IOException e) {

                throw new RuntimeException(e);
            }

        });

    }

}
