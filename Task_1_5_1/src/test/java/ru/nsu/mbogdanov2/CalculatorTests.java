package ru.nsu.mbogdanov2;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Test class that checks that my arithmetics is good.
 * Expressions are not compulsory, but it's enough to test my code
 */
public class CalculatorTests {
    @Test
    public void additionTest() {
        var calc = new ExpressionTree(new Scanner("+ 10 + 1 4"));
        double result = calc.calculation();
        Assertions.assertEquals(result, 15.0);
    }

    @Test
    public void subtractionTest() {
        var calc = new ExpressionTree(new Scanner("1 + 2 + 3 + 4 - 5 - 2 - 3 9"));
        double actual = calc.calculation();
        Assertions.assertEquals(1.0, actual);
    }

    @Test
    public void powerTest() {
        var calc = new ExpressionTree(new Scanner("- pow 2 31 1"));
        double result = calc.calculation();
        Assertions.assertEquals(result, Integer.MAX_VALUE);
    }

    @Test
    public void logTest() {
        var calc = new ExpressionTree(new Scanner("log 1 10"));
        double result = calc.calculation();
        Assertions.assertEquals(result, 0);
    }

    @Test
    public void cosAndSinTest() {
        var calc = new ExpressionTree(new Scanner("+ * cos 0 cos 0 * sin 0 sin 0"));
        double result = calc.calculation();
        Assertions.assertEquals(result, 1);
    }

    @Test
    public void divisionTest() {
        var calc = new ExpressionTree(new Scanner("/ 100 0"));
        IllegalArgumentException exceptionEmptyPattern = assertThrows(
                IllegalArgumentException.class, calc::calculation);
        Assertions.assertEquals("You can't divide by zero", exceptionEmptyPattern.getMessage());
    }

    @Test
    public void invalidExpressionTest() {
        var calc = new ExpressionTree(new Scanner("hello 100 0"));
        IllegalArgumentException exceptionEmptyPattern = assertThrows(
                IllegalArgumentException.class, calc::calculation);
        Assertions.assertEquals("Something wrong with this expression", exceptionEmptyPattern.getMessage());
    }
}
