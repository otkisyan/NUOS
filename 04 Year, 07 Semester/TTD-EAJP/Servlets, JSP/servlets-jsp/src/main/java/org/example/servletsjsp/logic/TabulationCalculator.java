package org.example.servletsjsp.logic;

import static java.lang.Math.*;

import java.util.ArrayList;
import java.util.List;
import org.example.servletsjsp.model.tabulation.Point;

public class TabulationCalculator {

  private final double startValue;
  private final double endValue;
  private final double stepValue;
  private final double[] xValues;
  private final double[] yValues;

  public TabulationCalculator(double startValue, double endValue, double stepValue) {
    this.startValue = startValue;
    this.endValue = endValue;
    this.stepValue = stepValue;
    this.xValues = createXValuesArray();
    this.yValues = createYValuesArray();
  }

  public double getStartValue() {
    return startValue;
  }

  public double getEndValue() {
    return endValue;
  }

  public double getStepValue() {
    return stepValue;
  }

  public double[] getXValues() {
    return xValues;
  }

  public double[] getYValues() {
    return yValues;
  }

  private double tabulation(double x) {
    return (exp(log(x) - 1) + sin(x));
  }

  private int calculateSteps() {
    return (int) ((endValue - startValue) / stepValue) + 1;
  }

  private double[] createXValuesArray() {
    int steps = calculateSteps();
    double[] xValues = new double[steps];
    for (int i = 0; i < steps; i++) {
      xValues[i] = startValue + i * stepValue;
    }
    return xValues;
  }

  private double[] createYValuesArray() {
    double[] yValues = new double[xValues.length];
    for (int i = 0; i < xValues.length; i++) {
      yValues[i] = tabulation(xValues[i]);
    }
    return yValues;
  }

  public int getMinIndex() {
    int minIndex = 0;
    for (int i = 0; i < yValues.length; i++) {
      if (yValues[i] < yValues[minIndex]) {
        minIndex = i;
      }
    }
    return minIndex;
  }

  public double getMinElement() {
    return yValues[getMinIndex()];
  }

  public double getMinElementArgument() {
    return xValues[getMinIndex()];
  }

  public int getMaxIndex() {
    int maxIndex = 0;
    for (int i = 0; i < yValues.length; i++) {
      if (yValues[i] > yValues[maxIndex]) {
        maxIndex = i;
      }
    }
    return maxIndex;
  }

  public double getMaxElement() {
    return yValues[getMaxIndex()];
  }

  public double getMaxElementArgument() {
    return xValues[getMaxIndex()];
  }

  public double getSum() {
    double sum = 0;
    for (double y : yValues) {
      sum += y;
    }
    return sum;
  }

  public double getAverage() {
    return getSum() / yValues.length;
  }

  public List<Point> getPoints() {
    List<Point> points = new ArrayList<>();
    for (int i = 0; i < xValues.length; i++) {
      points.add(new Point(xValues[i], yValues[i]));
    }
    return points;
  }
}
