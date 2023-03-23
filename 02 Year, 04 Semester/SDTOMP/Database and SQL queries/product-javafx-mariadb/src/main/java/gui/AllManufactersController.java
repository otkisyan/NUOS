package gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.DatabaseConnection;
import models.Logic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AllManufactersController {

    @FXML
    private Button backHome;

    @FXML
    private TableColumn<String, String> manufacterColumn;

    @FXML
    private TableView<String> table;

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

        ObservableList<String> manufactersList = FXCollections.observableArrayList(logic.getSetOfManufactures(connection));

        manufacterColumn.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue()));
        table.setItems(manufactersList);

        backHome.setOnAction(event -> {

            try {

                App.setRoot("/primary");

            } catch (IOException e) {

                throw new RuntimeException(e);
            }

        });

    }
}
