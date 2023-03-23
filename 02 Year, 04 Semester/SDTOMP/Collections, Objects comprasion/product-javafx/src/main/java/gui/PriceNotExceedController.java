package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.FileProcessor;
import models.Logic;
import models.Product;
import models.ProductsSingleton;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class PriceNotExceedController {

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
    private TextField nameInput;

    @FXML
    private TextField priceInput;

    @FXML
    private Button find;

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

        find.setOnAction(event -> {

            String userNameInput;
            double userPriceInput;
            userNameInput = nameInput.getText();
            userPriceInput = Double.parseDouble(priceInput.getText());

            ObservableList<Product> filteredProducts = FXCollections.observableArrayList(logic.filterNotExceedPrice(userNameInput, userPriceInput, productsList));
            table.setItems(filteredProducts);

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
