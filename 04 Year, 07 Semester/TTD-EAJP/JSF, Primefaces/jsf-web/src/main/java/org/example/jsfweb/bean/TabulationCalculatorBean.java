package org.example.jsfweb.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.example.jsfweb.logic.TabulationCalculator;
import org.example.jsfweb.model.tabulation.TabulationResult;
import org.example.jsfweb.validation.tabulation.NumberFormatHandler;
import org.example.jsfweb.validation.tabulation.RangeCheckHandler;
import org.example.jsfweb.validation.tabulation.StepZeroCheckHandler;
import org.example.jsfweb.validation.tabulation.TabulationValidationHandler;

import java.io.Serializable;

@Named
@SessionScoped
@Getter
@Setter
public class TabulationCalculatorBean implements Serializable {

    private double startValue;
    private double endValue;
    private double stepValue;
    private TabulationResult tabulationResult;

    public void calculateTabulation() {
        try {
            validateInput();
        } catch (NumberFormatException e) {
            return;
        }
        catch (IllegalArgumentException e) {
            return;
        }

        TabulationCalculator calculator = new TabulationCalculator(startValue, endValue, stepValue);
        tabulationResult = new TabulationResult(
                calculator.getMinElement(),
                calculator.getMinElementArgument(),
                calculator.getMaxElement(),
                calculator.getMaxElementArgument(),
                calculator.getAverage(),
                calculator.getSum(),
                calculator.getPoints()
        );
    }

    private void validateInput(){
        TabulationValidationHandler numberFormatHandler = new NumberFormatHandler();
        TabulationValidationHandler rangeCheckHandler = new RangeCheckHandler();
        TabulationValidationHandler stepZeroCheckHandler = new StepZeroCheckHandler();
        numberFormatHandler.setNextHandler(rangeCheckHandler);
        rangeCheckHandler.setNextHandler(stepZeroCheckHandler);
        numberFormatHandler.handle(startValue, endValue, stepValue);
    }
}