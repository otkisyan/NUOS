package org.example.servletsjsp.model.tabulation;

import java.util.List;

public class TabulationResult {
  private double minElement;
  private double minElementArgument;
  private double maxElement;
  private double maxElementArgument;
  private double average;
  private double sum;
  private List<Point> points;

  public TabulationResult(
      double minElement,
      double minElementArgument,
      double maxElement,
      double maxElementArgument,
      double average,
      double sum,
      List<Point> points) {
    this.minElement = minElement;
    this.minElementArgument = minElementArgument;
    this.maxElement = maxElement;
    this.maxElementArgument = maxElementArgument;
    this.average = average;
    this.sum = sum;
    this.points = points;
  }

  public List<Point> getPoints() {
    return points;
  }

  public void setPoints(List<Point> points) {
    this.points = points;
  }

  public double getMinElement() {
    return minElement;
  }

  public void setMinElement(double minElement) {
    this.minElement = minElement;
  }

  public double getMinElementArgument() {
    return minElementArgument;
  }

  public void setMinElementArgument(double minElementArgument) {
    this.minElementArgument = minElementArgument;
  }

  public double getMaxElement() {
    return maxElement;
  }

  public void setMaxElement(double maxElement) {
    this.maxElement = maxElement;
  }

  public double getMaxElementArgument() {
    return maxElementArgument;
  }

  public void setMaxElementArgument(double maxElementArgument) {
    this.maxElementArgument = maxElementArgument;
  }

  public double getAverage() {
    return average;
  }

  public void setAverage(double average) {
    this.average = average;
  }

  public double getSum() {
    return sum;
  }

  public void setSum(double sum) {
    this.sum = sum;
  }
}
