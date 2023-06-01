package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Logic;
import models.Product;
import models.ProductsSingleton;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ManufacterProductsController {

    @FXML
    private Accordion accordion;
    @FXML
    private Button backHome;

    @FXML
    void initialize() {

        Logic logic = new Logic();
        ProductsSingleton productsSingleton = ProductsSingleton.getInstance();
        List<Product> productsList = productsSingleton.getProductsList();
        Map<String, List<Product>> manufacterProducts = logic.filterProductsByManufacters(productsList);

        List<TitledPane> panes = new ArrayList<>();

        for (Map.Entry<String, List<Product>> entry : manufacterProducts.entrySet()) {

            String m = entry.getKey();
            ObservableList<Product> list = FXCollections.observableArrayList(entry.getValue());

            TableColumn<Product, Integer> amountColumn = new TableColumn<>("Кількість");
            TableColumn<Product, Integer> idColumn = new TableColumn<>("id");
            TableColumn<Product, String> nameColumn = new TableColumn<>("Назва");
            TableColumn<Product, Double> priceColumn = new TableColumn<>("Ціна");
            TableColumn<Product, LocalDate> storageColumn = new TableColumn<>("Час збереження");
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            storageColumn.setCellValueFactory(new PropertyValueFactory<>("storagePeriod"));
            amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

            TableView<Product> tableView = new TableView<>(list);
            tableView.getColumns().addAll(idColumn, nameColumn, priceColumn, storageColumn, amountColumn);

            TitledPane tp = new TitledPane(m, tableView);
            tp.setMinHeight(400);

            panes.add(tp);
        }

        accordion.getPanes().addAll(panes);

        backHome.setOnAction(event -> {

            try {

                App.setRoot("/primary");

            } catch (IOException e) {

                throw new RuntimeException(e);
            }

        });

    }
}
