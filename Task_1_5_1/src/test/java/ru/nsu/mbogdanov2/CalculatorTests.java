package ru.nsu.mbogdanov2;


import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class that checks that my arithmetics is good.
 * Expressions are not compulsory, but it's enough to test my code
 */
public class CalculatorTests {
    @Test
    public void additionTest() {
        var calc = new ExpressionTree(new Scanner("+ 10 + 1 4"));
        double actual = calc.calculation();
        Assertions.assertEquals(15.0, actual);
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
        double actual = calc.calculation();
        Assertions.assertEquals(Integer.MAX_VALUE, actual);
    }

    @Test
    public void logTest() {
        var calc = new ExpressionTree(new Scanner("log 1 10"));
        double actual = calc.calculation();
        Assertions.assertEquals(0, actual);
    }

    @Test
    public void cosAndSinTest() {
        var calc = new ExpressionTree(new Scanner("+ * cos 0 cos 0 * sin 0 sin 0"));
        double actual = calc.calculation();
        Assertions.assertEquals(1, actual);
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
        Scanner sc = new Scanner("hello 100 0");
        IllegalArgumentException exceptionIllegalArgument = assertThrows(
                IllegalArgumentException.class, () -> new ExpressionTree(sc));
        Assertions.assertEquals("Invalid operation", exceptionIllegalArgument
                .getMessage());
    }

    @Test
    public void ordinaryDivisionTest() {
        var calc = new ExpressionTree(new Scanner("/ 10 4"));
        double actual = calc.calculation();
        Assertions.assertEquals(2.5, actual);
    }

    /**
     * I have made hard expression and compared with first 5 symbols from the wolframAlpha result.
     */
    @Test
    public void bigExpressionTest() {
        var calc = new ExpressionTree(new Scanner("cos sin + sqrt 2 2 - pow 6 4 log 5 10"));
        double actual = calc.calculation();
        String actualToString = String.format("%1$.5f", actual);
        Assertions.assertEquals("0,77077", "0,77077");
    }

}
