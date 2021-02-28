package io.home.junit5app.logic;

public class Calculator {

    public int add(int num1, int num2) {
        if(num1 < 0 || num2 < 0) {
            throw new RuntimeException("Negative numbers are not allowed");
        }
        return num1 + num2;
    }

    public int subtract(int num1, int num2) {
        return num1 - num2;
    }

    public int divide(int num1, int num2) {
        if(num2 == 0) {
            throw new RuntimeException("Zero is not allowed in denominator");
        }
        return num1 / num2;
    }

    public int multiply(int num1, int num2) {
        return num1 * num2;
    }

}
