package com.computergraphics.lb3_cg;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

/**
 * 1. Создать программу, которая будет иметь удобный интерфейс (окно, главное меню, строка статуса и др.).
 * В окне программы должно создаваться и отображаться изображение
 * фигуры, созданной в соответствии с заданием лабораторной работы (Буква С и цифра 9)
 * <br>
 * 2. Реализовать аффинные преобразования фигуры (перемещение по экрану, увеличение/уменьшение, повороты).
 * Программа должна предоставлять возможность выполнения над фигурой трех основных аффинных преобразований,
 * при этом выбор точки, относительно которой будет происходить поворот также должен
 * выбираться пользователем.
 */
public class Transform extends Application {

    private Text letter;
    private Translate translate;
    private Scale scale;
    private Rotate rotate;
    private Translate translatePivotCircle;
    private final double pivot = 200.0;
    private double pivotTranslateX = 200;
    private double pivotTranslateY = 200;
    private double pivotScaleY = 200;
    private double pivotScaleX = 200;
    private double pivotRotateX = 200;
    private double pivotRotateY = 200;
    private ComboBox<String> transformationComboBox;
    private final Slider translateSliderX = new Slider(0, 400, 200);
    private final Slider translateSliderY = new Slider(0, 400, 200);
    private final Slider scaleSliderX = new Slider(0, 400, 200);
    private final Slider scaleSliderY = new Slider(0, 400, 200);
    private final Slider pivotRotateSliderX = new Slider(0, 400, 200);
    private final Slider pivotRotateSliderY = new Slider(0, 400, 200);
    private final Slider rotateSlider = new Slider(0, 360, 0);
    Circle pivotCircle = new Circle(5);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        setupSliders();

        pivotCircle.setFill(Color.RED);
        pivotCircle.setStroke(Color.BLACK);
        letter = new Text("С9");
        letter.setFont(Font.font("Arial", 150));
        letter.setFill(Color.TRANSPARENT);
        letter.setStroke(Color.BLACK);
        letter.setStrokeWidth(1);

        translate = new Translate();
        scale = new Scale();
        rotate = new Rotate();
        translatePivotCircle = new Translate();
        transformationComboBox = new ComboBox<>();
        transformationComboBox.getItems().addAll("Translate", "Scale", "Rotate");
        transformationComboBox.setValue("Translate");
        letter.getTransforms().addAll(translate, scale, rotate);
        pivotCircle.getTransforms().addAll(translatePivotCircle);

        ColorPicker colorPicker = new ColorPicker(Color.TRANSPARENT);

        colorPicker.setOnAction(event -> {
            Color selectedColor = colorPicker.getValue();
            letter.setFill(selectedColor);
        });

        translateSliderX.valueProperty().addListener((obs, oldValue, newValue) -> {
            pivotTranslateX = newValue.doubleValue();
            updateTransformation();
        });

        translateSliderY.valueProperty().addListener((obs, oldValue, newValue) -> {
            pivotTranslateY = newValue.doubleValue();
            updateTransformation();
        });

        scaleSliderX.valueProperty().addListener((obs, oldValue, newValue) -> {
            pivotScaleX = newValue.doubleValue();
            updateTransformation();
        });

        scaleSliderY.valueProperty().addListener((obs, oldValue, newValue) -> {
            pivotScaleY = newValue.doubleValue();
            updateTransformation();
        });

        pivotRotateSliderX.valueProperty().addListener((obs, oldValue, newValue) -> {
            translatePivotCircle.setX(pivotRotateX - 200);
            pivotRotateX = newValue.doubleValue();
            updateTransformation();
        });

        pivotRotateSliderY.valueProperty().addListener((obs, oldValue, newValue) -> {
            translatePivotCircle.setY(200 - pivotRotateY);
            pivotRotateY = newValue.doubleValue();
            updateTransformation();
        });

        rotateSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            updateTransformation();
            performRotation(newValue.intValue());
        });

        BorderPane root = new BorderPane();
        HBox topContainer = new HBox();
        topContainer.setSpacing(10);
        topContainer.setPadding(new Insets(10));
        topContainer.getChildren().addAll(transformationComboBox, colorPicker);

        StackPane leftContainer = new StackPane();
        leftContainer.setAlignment(Pos.CENTER_LEFT);
        leftContainer.getChildren().addAll(translateSliderY, scaleSliderY, pivotRotateSliderY);

        StackPane centerContainer = new StackPane(letter, pivotCircle, pivotRotateSliderX);
        StackPane.setAlignment(letter, Pos.CENTER);
        StackPane.setAlignment(pivotCircle, Pos.CENTER);

        StackPane bottomContainer = new StackPane();
        bottomContainer.setAlignment(Pos.BOTTOM_CENTER);
        bottomContainer.getChildren().addAll(translateSliderX, scaleSliderX, pivotRotateSliderX);

        root.setTop(topContainer);
        root.setLeft(leftContainer);
        root.setCenter(centerContainer);
        root.setRight(rotateSlider);
        root.setBottom(bottomContainer);

        transformationComboBox.setOnAction(event -> {
            updateTransformation();
        });

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Перша літера прізвища та цифра 9");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    private void setupSliders() {
        rotateSlider.setVisible(false);
        scaleSliderX.setVisible(false);
        scaleSliderY.setVisible(false);
        pivotRotateSliderX.setVisible(false);
        pivotRotateSliderY.setVisible(false);
        pivotCircle.setVisible(false);
        rotateSlider.setShowTickLabels(true);
        rotateSlider.setShowTickMarks(true);
        rotateSlider.setMajorTickUnit(45);
        rotateSlider.setOrientation(Orientation.VERTICAL);
        translateSliderY.setOrientation(Orientation.VERTICAL);
        scaleSliderY.setOrientation(Orientation.VERTICAL);
        pivotRotateSliderY.setOrientation(Orientation.VERTICAL);
    }

    private void updateTransformation() {
        String selectedTransformation = transformationComboBox.getValue();
        switch (selectedTransformation) {
            case "Translate":
                pivotCircle.setVisible(false);
                rotateSlider.setVisible(false);
                scaleSliderX.setVisible(false);
                scaleSliderY.setVisible(false);
                pivotRotateSliderX.setVisible(false);
                pivotRotateSliderY.setVisible(false);
                translateSliderX.setVisible(true);
                translateSliderY.setVisible(true);
                performTranslation();
                break;
            case "Scale":
                pivotCircle.setVisible(false);
                rotateSlider.setVisible(false);
                pivotRotateSliderX.setVisible(false);
                pivotRotateSliderY.setVisible(false);
                translateSliderX.setVisible(false);
                translateSliderY.setVisible(false);
                scaleSliderY.setVisible(true);
                scaleSliderX.setVisible(true);
                performScaling();
                break;
            case "Rotate":
                translateSliderX.setVisible(false);
                translateSliderY.setVisible(false);
                scaleSliderY.setVisible(false);
                scaleSliderX.setVisible(false);
                pivotRotateSliderX.setVisible(true);
                pivotRotateSliderY.setVisible(true);
                rotateSlider.setVisible(true);
                pivotCircle.setVisible(true);
                break;
        }
    }

    private void performTranslation() {
        translate.setX(pivotTranslateX - pivot);
        translate.setY(pivot - pivotTranslateY);
    }

    private void performScaling() {
        double scaleX = 1.0 + (pivotScaleX - pivot) / pivot;
        double scaleY = 1.0 + (pivotScaleY - pivot) / pivot;
        scale.setX(scaleX);
        scale.setY(scaleY);
    }

    private void performRotation(int rotateAngle) {
        rotate.setPivotX(translatePivotCircle.getX());
        rotate.setPivotY(translatePivotCircle.getY());
        rotate.setAngle(rotateAngle);
    }
}
