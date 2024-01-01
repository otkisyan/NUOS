package com.computergraphics.lb2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 1. Создать программу с удобным интерфейсом пользователя,
 * которая будет реализовывать возможность построения рисунка в соответствии с вариантом задания.
 * <br>
 * 2. Единственной графической операцией, которую разрешается использовать, есть операция построения точки.
 * Все другие графические элементы - линии, которые образуют многоугольники контуров символов,
 * должны строиться с использованием инкрементных алгоритмов Брезенхема.
 * <br>
 * 3. Реализовать в программе возможность диалогового режима выбора цвета изображения.
 * @see <a href="https://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm">Wikipedia: Bresenham's line algorithm</a>
 */
public class DrawWithBresenham extends Application {
    private Canvas canvas;
    private GraphicsContext gc;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bresenham Letter Drawing");
        canvas = new Canvas(400, 400);
        gc = canvas.getGraphicsContext2D();
        drawC();
        drawNine();

        canvas.setOnMousePressed(event -> {
            System.out.println("x: " + event.getX());
            System.out.println("y: " + event.getY());
        });

        ColorPicker colorPicker = new ColorPicker(Color.BLACK);

        colorPicker.setOnAction(event -> {
            Color selectedColor = colorPicker.getValue();
            gc.setFill(selectedColor);
            drawC();
            drawNine();
        });

        HBox topContainer = new HBox();
        topContainer.setSpacing(10);
        topContainer.setPadding(new Insets(10));
        topContainer.getChildren().addAll(colorPicker);

        StackPane root = new StackPane();
        root.getChildren().addAll(canvas, topContainer);
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    private void drawC() {

        int width = 50;
        int height = 70;
        int centerX = (int) (canvas.getWidth() / 2);
        int centerY = (int) (canvas.getHeight() / 2);

        drawLine(centerX, centerY, centerX + width, centerY);
        drawLine(centerX + width, centerY, centerX + width, centerY + 20);
        drawLine(centerX + width, centerY + 20, centerX + 20, centerY + 20);
        drawLine(centerX + 20, centerY + 20, centerX + 20, centerY + height - 20);
        drawLine(centerX + 20, centerY + height - 20, centerX + width, centerY + height - 20);
        drawLine(centerX + width, centerY + height - 20, centerX + width, centerY + height);
        drawLine(centerX, centerY, centerX, centerY + height);
        drawLine(centerX, centerY + height, centerX + width, centerY + height);
    }

    private void drawNine() {

        int width = 50;
        int height = 70;
        int centerX = (int) (canvas.getWidth() / 2) + width + 20;
        int centerY = (int) (canvas.getHeight() / 2);

        drawLine(centerX, centerY, centerX + width, centerY);
        drawLine(centerX, centerY + height / 2, centerX + width / 2, centerY + height / 2);
        drawLine(centerX, centerY, centerX, centerY + height / 2);
        drawLine(centerX + width, centerY + height, centerX + width, centerY);
        drawLine(centerX + width, centerY + height, centerX, centerY + height);
        drawLine(centerX, centerY + height, centerX, centerY + height - 20);
        drawLine(centerX, centerY + height - 20, centerX + 10, centerY + height - 20);
        drawLine(centerX + 10, centerY + height - 20, centerX + 10, centerY + height - 10);
        drawLine(centerX + 10, centerY + height - 10, centerX + width / 2, centerY + height - 10);
        drawLine(centerX + width / 2, centerY + height - 10, centerX + width / 2, centerY + height / 2);
    }

    private void drawLine(int x0, int y0, int x1, int y1) {
        int dx = Math.abs(x1 - x0);
        int dy = -Math.abs(y1 - y0);
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;
        int error = dx + dy;

        while (true) {
            gc.fillRect(x0, y0, 1, 1);

            if (x0 == x1 && y0 == y1) {
                break;
            }

            int e2 = 2 * error;
            if (e2 >= dy) {
                if (x0 == x1) {
                    break;
                }
                error += dy;
                x0 += sx;
            }
            if (e2 <= dx) {
                if (y0 == y1) {
                    break;
                }
                error += dx;
                y0 += sy;
            }
        }
    }
}
