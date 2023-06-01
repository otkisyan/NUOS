package gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Logic;
import models.Product;
import models.ProductsSingleton;

import java.io.IOException;
import java.util.List;

public class AllManufactersController {

    @FXML
    private Button backHome;

    @FXML
    private TableColumn<String, String> manufacterColumn;

    @FXML
    private TableView<String> table;

    @FXML
    void initialize() {

        Logic logic = new Logic();
        ProductsSingleton productsSingleton = ProductsSingleton.getInstance();
        List<Product> productsList = productsSingleton.getProductsList();

        ObservableList<String> manufactersList = FXCollections.observableArrayList(logic.getSetOfManufacters(productsList));

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
