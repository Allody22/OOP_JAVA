package ru.nsu.mbogdanov2;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Scanner;


/**
 * Test class that checks that my arithmetics is good
 * Expressions are not compulsory, but it's enough to test my code
 *
 */
public class CalculatorTests {
    @Test
    public void AdditionTest()  {
        var calc = new ExpressionTree(new Scanner("+ 10 + 1 4"));
        double result = calc.calculation();
        Assertions.assertEquals(result,15.0);
    }
    @Test
    public void SubtractionTest()  {
        var calc = new ExpressionTree(new Scanner("1 + 2 + 3 + 4 - 5 - 2 - 3 9"));
        double actual = calc.calculation();
        Assertions.assertEquals(1.0, actual);
    }

    @Test
    public void PowerTest() {
        var calc = new ExpressionTree(new Scanner("- pow 2 31 1"));
        double result = calc.calculation();
        Assertions.assertEquals(result,Integer.MAX_VALUE);
    }
}
