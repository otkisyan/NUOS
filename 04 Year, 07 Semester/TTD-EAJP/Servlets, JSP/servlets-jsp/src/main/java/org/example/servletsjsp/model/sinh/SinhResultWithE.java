package org.example.servletsjsp.model.sinh;

public class SinhResultWithE {
  private double sumWithE;
  private int sumWithETermCount;

  public SinhResultWithE(double sumWithE, int sumWithETermCount) {
    this.sumWithE = sumWithE;
    this.sumWithETermCount = sumWithETermCount;
  }

  public double getSumWithE() {
    return sumWithE;
  }

  public void setSumWithE(double sumWithE) {
    this.sumWithE = sumWithE;
  }

  public int getSumWithETermCount() {
    return sumWithETermCount;
  }

  public void setSumWithETermCount(int sumWithETermCount) {
    this.sumWithETermCount = sumWithETermCount;
  }
}
