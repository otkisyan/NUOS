package org.example.jsfweb.validation.tabulation;

public abstract class TabulationValidationHandler {
  protected TabulationValidationHandler nextHandler;

  public void setNextHandler(TabulationValidationHandler nextHandler) {
    this.nextHandler = nextHandler;
  }

  public abstract void handle(double start, double end, double step);

  protected void handleNext(double start, double end, double step) {
    if (nextHandler == null) {
      return;
    }
    nextHandler.handle(start, end, step);
  }
}
