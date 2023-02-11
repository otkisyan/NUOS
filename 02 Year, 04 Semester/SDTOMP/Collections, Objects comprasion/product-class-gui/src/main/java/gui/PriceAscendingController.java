package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Logic;
import models.Product;
import models.ProductsSingleton;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class PriceAscendingController {

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
    private Button backHome;

    @FXML
    void initialize() {

        Logic logic = new Logic();

        ProductsSingleton productsSingleton = ProductsSingleton.getInstance();
        List<Product> productsList = productsSingleton.getProductsList();

        idColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        manufacterColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("manufacter"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        storageColumn.setCellValueFactory(new PropertyValueFactory<Product, LocalDate>("storagePeriod"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("amount"));

        ObservableList<Product> filteredProducts =
                FXCollections.observableArrayList(logic.sortListByPriceAscending(productsList));

        table.setItems(filteredProducts);

        backHome.setOnAction(event -> {

            try {

                App.setRoot("/primary");

            } catch (IOException e) {

                throw new RuntimeException(e);
            }

        });


    }

}
