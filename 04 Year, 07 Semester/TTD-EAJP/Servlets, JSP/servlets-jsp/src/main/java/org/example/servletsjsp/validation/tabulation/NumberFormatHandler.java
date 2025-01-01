package org.example.servletsjsp.validation.tabulation;

public class NumberFormatHandler extends TabulationValidationHandler {

  @Override
  public void handle(double startValue, double endValue, double stepValue) {
    if (Double.isNaN(startValue) || Double.isNaN(endValue) || Double.isNaN(stepValue)) {
      throw new NumberFormatException("Invalid number format");
    }
    handleNext(startValue, endValue, stepValue);
  }
}
