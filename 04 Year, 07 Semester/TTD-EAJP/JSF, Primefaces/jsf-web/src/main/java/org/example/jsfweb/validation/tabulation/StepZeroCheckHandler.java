package org.example.jsfweb.validation.tabulation;

public class StepZeroCheckHandler extends TabulationValidationHandler {

  @Override
  public void handle(double startValue, double endValue, double stepValue) {
    if (stepValue == 0) {
      throw new IllegalArgumentException("Step value must not be zero");
    }
    handleNext(startValue, endValue, stepValue);
  }
}
