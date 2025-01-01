package org.example.jsfweb.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.example.jsfweb.logic.SinhCalculator;
import org.example.jsfweb.model.sinh.SinhResult;

import java.io.Serializable;

@Named
@SessionScoped
@Getter
@Setter
public class SinhCalculatorBean implements Serializable {

    private double x;
    private int n;
    private double e;
    private SinhResult sinhResult;

    public void calculateSinh() {
        SinhCalculator calculator = new SinhCalculator(x, n, e);
        sinhResult = new SinhResult(
                calculator.calculateSum(),
                calculator.exactSinh(),
                calculator.calculateSumWithE()
        );
    }
}