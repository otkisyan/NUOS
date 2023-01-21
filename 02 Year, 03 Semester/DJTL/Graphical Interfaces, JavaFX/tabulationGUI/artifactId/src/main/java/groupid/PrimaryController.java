package groupid;

import java.text.DecimalFormat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import groupid.logic.Logic;
import javafx.scene.input.MouseEvent;

public class PrimaryController {
    @FXML
    private TextField aValue;

    @FXML
    private TextField averageOfElements;

    @FXML
    private TextField biggestX;

    @FXML
    private TextField biggestY;

    @FXML
    private Button calculate;

    @FXML
    private TextField end;

    @FXML
    private TextField numberOfSteps;

    @FXML
    private TextField smallestX;

    @FXML
    private TextField smallestY;

    @FXML
    private TextField start;

    @FXML
    private TextField step;

    @FXML
    private TextField sumOfElements;


    @FXML
    void initialize() {

        Logic logic = new Logic();

       /* calculate.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

               ...
            }
        });
*/

        calculate.setOnAction(event -> {

            boolean success = true;
            double startValue = 0; 
            int endValue = 0;
            double stepValue = 0;
            double a = 0;

            try {

                startValue = Double.parseDouble(start.getText());
                endValue = Integer.parseInt(end.getText());
                stepValue = Double.parseDouble(step.getText());
                a = Double.parseDouble(aValue.getText());
                
            } catch (NumberFormatException err) {

                Alert b = new Alert(Alert.AlertType.ERROR);
                b.setTitle("Помилка");
                b.setHeaderText("Некоректно введено значення! У поле можна ввести тільки цифри!");
                b.setContentText("Примітка: Використовуйте крапки замість ком для позначення не цілих чисел!");
                b.show();
                success = false;
            }

            if (success) {

                double[] xValues = logic.xValuesArrayFill(startValue, endValue, stepValue);
                double[] yValues = logic.yValuesArrayFill(xValues, a);

                DecimalFormat df = new DecimalFormat("#.###");
                numberOfSteps.setText(String.valueOf(logic.calculateSteps(startValue, endValue, stepValue)));
                biggestY.setText(String.valueOf(df.format(logic.getMaxElement(yValues))));
                biggestX.setText(String.valueOf(logic.getMaxElementArgument(yValues, xValues)));
                smallestY.setText(String.valueOf(df.format(logic.getMinElement(yValues))));
                smallestX.setText(String.valueOf(logic.getMinElementArgument(yValues, xValues)));
                sumOfElements.setText(String.valueOf(df.format(logic.getSum(yValues))));
                averageOfElements.setText(String.valueOf(df.format(logic.getAverage(yValues))));
                System.out.println("button clicked");
            }

        });
    }
}
