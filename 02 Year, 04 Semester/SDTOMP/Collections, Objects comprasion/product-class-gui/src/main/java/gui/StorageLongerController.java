package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.FileProcessor;
import models.Logic;
import models.Product;
import models.ProductsSingleton;

import java.io.IOException;
import java.time.LocalDate;

public class StorageLongerController {

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
    private Button find;

    @FXML
    private TextField storageDay;

    @FXML
    private TextField storageMonth;

    @FXML
    private TextField storageYear;

    @FXML
    void initialize() {

        Logic logic = new Logic();
        ProductsSingleton productsSingleton = ProductsSingleton.getInstance();
        ObservableList<Product> productsList = productsSingleton.getProductsList();

        idColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        manufacterColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("manufacter"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        storageColumn.setCellValueFactory(new PropertyValueFactory<Product, LocalDate>("storagePeriod"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("amount"));


        find.setOnAction(event -> {

            int year = 0;
            int month = 0;
            int day = 0;

            try {

                year = Integer.parseInt(storageYear.getText());
                month = Integer.parseInt(storageMonth.getText());
                day = Integer.parseInt(storageDay.getText());

            } catch (NumberFormatException err) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Помилка");
                alert.setHeaderText("Некоректно введено значення! У поле можна ввести тільки цифри!");
                alert.setContentText("Примітка: Використовуйте крапки замість ком для позначення не цілих чисел!");
                alert.show();
            }

            LocalDate userStoragePeriodInput = LocalDate.of(year, month, day);

            ObservableList<Product> filteredProducts = FXCollections.observableArrayList(logic.filterPeriodLongerThan(userStoragePeriodInput, productsList));
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
