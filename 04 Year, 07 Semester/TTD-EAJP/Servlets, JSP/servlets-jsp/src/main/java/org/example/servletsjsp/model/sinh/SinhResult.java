package org.example.servletsjsp.model.sinh;

public class SinhResult {
  private double sumN;
  private double exactSinh;
  private SinhResultWithE sinhResultWithE;

  public SinhResult(double sumN, double exactSinh, SinhResultWithE sinhResultWithE) {
    this.sumN = sumN;
    this.exactSinh = exactSinh;
    this.sinhResultWithE = sinhResultWithE;
  }

  public double getSumN() {
    return sumN;
  }

  public void setSumN(double sumN) {
    this.sumN = sumN;
  }

  public double getExactSinh() {
    return exactSinh;
  }

  public void setExactSinh(double exactSinh) {
    this.exactSinh = exactSinh;
  }

  public SinhResultWithE getSinhResultWithE() {
    return sinhResultWithE;
  }

  public void setSinhResultWithE(SinhResultWithE sinhResultWithE) {
    this.sinhResultWithE = sinhResultWithE;
  }
}
