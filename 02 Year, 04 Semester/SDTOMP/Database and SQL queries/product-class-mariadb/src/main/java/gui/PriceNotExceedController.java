package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.DatabaseConnection;
import models.Logic;
import models.Product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

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

        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        Logic logic = new Logic();
        Connection connection = null;

        try {

            connection = databaseConnection.getConnection();

        } catch (SQLException | ClassNotFoundException e) {

            throw new RuntimeException(e);
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        manufacterColumn.setCellValueFactory(new PropertyValueFactory<>("manufacter"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        storageColumn.setCellValueFactory(new PropertyValueFactory<>("expirationDate"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        Connection finalConnection = connection;
        find.setOnAction(event -> {

            String userNameInput;
            double userPriceInput;
            userNameInput = nameInput.getText();
            userPriceInput = Double.parseDouble(priceInput.getText());

            ObservableList<Product> filteredProducts = FXCollections.observableArrayList(logic.filterNotExceedPrice(finalConnection, userNameInput, userPriceInput));
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
