package gui;

import models.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class AddProductController {

    @FXML
    private Button addProduct;

    @FXML
    private TextField amount;

    @FXML
    private Button home;

    @FXML
    private TextField manufacter;

    @FXML
    private TextField name;

    @FXML
    private TextField price;

    @FXML
    private TextField storageDay;

    @FXML
    private TextField storageMonth;

    @FXML
    private TextField storageYear;

    @FXML
    void initialize() {

        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        Connection connection = null;

        try {

            connection = databaseConnection.getConnection();

        } catch (SQLException | ClassNotFoundException e) {

            throw new RuntimeException(e);
        }

        Logic logic = new Logic();

        Connection finalConnection = connection;
        addProduct.setOnAction(event -> {

            int year = 0;
            int month = 0;
            int day = 0;

            String productName;
            String productManufacter;
            double productPrice = 0.0;
            LocalDate productStoragePeriod = null;
            int productAmount = 0;
            boolean success = true;

            productName = name.getText();
            productManufacter = manufacter.getText();

            try {

                productPrice = Double.parseDouble(price.getText());
                year = Integer.parseInt(storageYear.getText());
                month = Integer.parseInt(storageMonth.getText());
                day = Integer.parseInt(storageDay.getText());

                productAmount = Integer.parseInt(amount.getText());

            } catch (NumberFormatException err) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Помилка");
                alert.setHeaderText("Некоректно введено значення! У поле можна ввести тільки цифри!");
                alert.setContentText("Примітка: Використовуйте крапки замість ком для позначення не цілих чисел!");
                alert.show();
                success = false;
            }

            if (success) {

                productStoragePeriod = LocalDate.of(year, month, day);
                logic.addProduct(finalConnection, productName, productManufacter, productPrice, productStoragePeriod, productAmount);

            }

        });

        home.setOnAction(event -> {

            try {

                App.setRoot("/primary");

            } catch (IOException e) {

                throw new RuntimeException(e);
            }
        });
    }

}
