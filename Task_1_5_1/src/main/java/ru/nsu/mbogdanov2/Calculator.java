package ru.nsu.mbogdanov2;

import java.util.Scanner;

public class Calculator {
    public Number calculate(String expression) {
        ExpressionTree calc;
        double result = 0;

        if (expression.length() > 0) {

            calc = new ExpressionTree(new Scanner(expression));

            result = calc.calculation();
        }
        return result;
    }
}