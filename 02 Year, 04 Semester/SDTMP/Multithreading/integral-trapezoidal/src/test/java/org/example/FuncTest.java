package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuncTest {

    @Test
    void calculate() {

        double result = Func.calculate(5.2);
        assertEquals(result, 0.2078, 0.0001);
    }
}