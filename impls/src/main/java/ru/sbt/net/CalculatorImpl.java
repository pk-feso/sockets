package ru.sbt.net;

public class CalculatorImpl implements Calculator {
    public double calculate(int a, int b) {
        return a + b + 204;
    }

    public double multiply(int a, int b) {
        throw new RuntimeException("ERROR DURING WORK");
        //return a * b;
    }
}
