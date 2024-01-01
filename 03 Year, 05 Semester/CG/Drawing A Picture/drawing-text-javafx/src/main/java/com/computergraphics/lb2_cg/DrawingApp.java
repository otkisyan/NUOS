package com.computergraphics.lb2_cg;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class DrawingApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Text letterC = new Text("C9");
        letterC.setFont(Font.font("Arial", 200));
        letterC.setFill(Color.TRANSPARENT);
        letterC.setStroke(Color.BLACK);

        ColorPicker colorPicker = new ColorPicker(Color.TRANSPARENT);
        colorPicker.setOnAction(event -> {
            Color selectedColor = colorPicker.getValue();
            letterC.setFill(selectedColor);
        });

        StackPane root = new StackPane();
        root.getChildren().addAll(letterC, colorPicker);
        StackPane.setAlignment(letterC, Pos.CENTER);
        StackPane.setAlignment(colorPicker, Pos.BOTTOM_CENTER);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Text");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

}
