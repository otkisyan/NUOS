package org.example.jsfweb.logic;


import org.example.jsfweb.model.sinh.SinhResultWithE;

public class SinhCalculator {

  private final double x;
  private final int n;
  private final double e;

  public SinhCalculator(double x, int n, double e) {
    this.x = x;
    this.n = n;
    this.e = e;
  }

  private double calculateTerm(int i) {
    return Math.pow(x, 2 * i + 1) / factorial(2 * i + 1);
  }

  public double calculateSum() {
    double sum = 0.0;
    for (int i = 0; i < n; i++) {
      sum += calculateTerm(i);
    }
    return sum;
  }

  public SinhResultWithE calculateSumWithE() {
    double sum = 0.0;
    int termCount = 0;
    for (int i = 0; i < n; i++) {
      double term = calculateTerm(i);
      if (Math.abs(term) > e) {
        sum += term;
        termCount++;
      } else {
        break;
      }
    }
    return new SinhResultWithE(sum, termCount);
  }

  public double exactSinh() {
    return Math.sinh(x);
  }

  private long factorial(int n) {
    long result = 1;
    for (int i = 1; i <= n; i++) {
      result *= i;
    }
    return result;
  }
}
