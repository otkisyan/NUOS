package org.example.jsfweb.validation.tabulation;

public class RangeCheckHandler extends TabulationValidationHandler {

  @Override
  public void handle(double startValue, double endValue, double stepValue) {
    if (endValue < startValue) {
      throw new IllegalArgumentException("End value must be greater than or equal to start value");
    }
    handleNext(startValue, endValue, stepValue);
  }
}
