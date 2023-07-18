package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class PrimaryController {

    @FXML
    private Button addProduct;

    @FXML
    private Button allManufacters;

    @FXML
    private Button allPriceAscending;

    @FXML
    private Button allProducts;

    @FXML
    private Button allStoragePeriodLongerThan;

    @FXML
    private Button nameNotExceedPrice;

    @FXML
    private Button nameStoragePeriodDescending;

    @FXML
    private Button productsOfEachManufacter;

    @FXML
    private Button removeProduct;

    @FXML
    void initialize() {

        addProduct.setOnAction(event -> {

            try {

                App.setRoot("/addProduct");


            } catch (IOException e) {

                throw new RuntimeException(e);
            }
        });

        removeProduct.setOnAction(event -> {

            try {

                App.setRoot("/removeProduct");

            } catch (IOException e) {

                throw new RuntimeException(e);
            }
        });

        allProducts.setOnAction(event -> {

            try {

                App.setRoot("/allProducts");

            } catch (IOException e) {

                throw new RuntimeException(e);
            }
        });

        nameStoragePeriodDescending.setOnAction(event -> {

            try {

                App.setRoot("/storageDescendingOrder");

            } catch (IOException e) {

                throw new RuntimeException(e);
            }
        });

        nameNotExceedPrice.setOnAction(event -> {

            try {

                App.setRoot("/priceNotExceed");

            } catch (IOException e) {

                throw new RuntimeException(e);
            }
        });

        allStoragePeriodLongerThan.setOnAction(event -> {

            try {

                App.setRoot("/storagePeriodLonger");

            } catch (IOException e) {

                throw new RuntimeException(e);
            }
        });

        allPriceAscending.setOnAction(event -> {

            try {

                App.setRoot("/priceAscending");

            } catch (IOException e) {

                throw new RuntimeException(e);
            }
        });


        allManufacters.setOnAction(event -> {

            try {

                App.setRoot("/allManufacters");

            } catch (IOException e) {

                throw new RuntimeException(e);
            }
        });
        productsOfEachManufacter.setOnAction(event -> {

            try {

                App.setRoot("/manufacterProducts");

            } catch (IOException e) {

                throw new RuntimeException(e);
            }
        });
    }
}
