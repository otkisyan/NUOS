package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegralCalculatorTest {

    IntegralCalculator integralCalculator;

    @BeforeEach
    void setUp() {

        integralCalculator = new IntegralCalculator();
    }

    @Test
    void calculate() {

        double a = 0.0;
        double b = Math.PI / 3.0;
        int n = 1000;

        double result = integralCalculator.calculate(a, b, n);

        assertEquals(0.216506, result, 0.000001);

    }


    @Test
    void calculateParallel() {

        double a = 0.0;
        double b = Math.PI / 3.0;
        int n = 1000;
        int numThreads = 20;

        double result = integralCalculator.parallelCalculate(a, b, n, numThreads);

        assertEquals(0.216506, result, 0.000001);
    }
}