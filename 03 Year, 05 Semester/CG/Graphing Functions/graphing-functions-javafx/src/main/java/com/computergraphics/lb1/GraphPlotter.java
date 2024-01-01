package com.computergraphics.lb1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Создать программу с удобным интерфейсом пользователя, которая будет реализовывать
 * возможность построения графика функции в соответствии с вариантом задания.
 * <br>
 * Реализовать в программе возможность диалогового режима ввода границ интервала, на
 * котором строится график функции.
 * <br>
 * y = cos(1 + 0.5x^2) / (1.5 + sqrt(0.2 + x))
 */

public class GraphPlotter extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Графік функції");

        Label intervalLabel = new Label("Інтервал [a, b]:");
        TextField intervalStartField = new TextField("1.0");
        TextField intervalEndField = new TextField("3.0");
        LineChart<Number, Number> lineChart = createLineChart();

        javafx.scene.control.Button plotButton = new javafx.scene.control.Button("Побудувати графік");
        plotButton.setOnAction(event -> {
            try {
                double a = Double.parseDouble(intervalStartField.getText());
                double b = Double.parseDouble(intervalEndField.getText());
                if (a >= b) {
                    showAlert("Помилка", "Початок інтервалу повинен бути менше кінця інтервалу.");
                    return;
                }
                plotFunction(lineChart, a, b);
            } catch (NumberFormatException e) {
                showAlert("Помилка", "Будь ласка, введіть коректні значення для інтервалу.");
            }
        });

        VBox vbox = new VBox(intervalLabel, intervalStartField, intervalEndField, plotButton, lineChart);
        Scene scene = new Scene(vbox, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Создание графика
    private LineChart<Number, Number> createLineChart() {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Графік функції");
        xAxis.setLabel("x");
        yAxis.setLabel("y");

        return lineChart;
    }

    // Построение графика функции на интервале [a, b]
    private void plotFunction(LineChart<Number, Number> lineChart, double a, double b) {
        lineChart.getData().clear();

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("y = cos(1 + 0.5x^2) / (1.5 + sqrt(0.2 + x))");

        for (double x = a; x <= b; x += 0.1) {
            double y = Math.cos(1 + 0.5 * x * x) / (1.5 + Math.sqrt(0.2 + x));
            series.getData().add(new XYChart.Data<>(x, y));
        }

        lineChart.getData().add(series);
    }

    // Уведомление об ошибках
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
