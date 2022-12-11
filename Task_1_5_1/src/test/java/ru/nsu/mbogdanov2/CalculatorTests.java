package ru.nsu.mbogdanov2;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class CalculatorTests {
    @Test
    public void AdditionTest() {
        Calculator myCalculator = new Calculator();
        Number result = myCalculator.calculate("+ 10 + 1 4");
        Assertions.assertEquals(result,15.0);
    }
    @Test
    public void SubtractionTest() {
        Calculator myCalculator = new Calculator();
        Number result = myCalculator.calculate("+1 + 2 + 3 + 4 - 5 -2 - 3 9");
        Assertions.assertEquals(result,1.0);
    }
}
