package ru.nsu.mbogdanov2;

import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class StackTest for testing my stack.
 */
public class StackTests {
    /**
     * Test number 1 with small stack.
     * All functions are tested here
     */
    @Test
    public void smallStackTest() {
        Stack<Integer> actual = new Stack<>();
        actual.push(6);
        actual.push(5);
        Stack<Integer> expected = new Stack<>();
        expected.push(6);
        expected.push(6);
        Assertions.assertEquals(actual.count(), expected.count());
        actual.pop();
        Stack<Integer> newArr = new Stack<>();
        newArr.push(6);
        newArr.push(6);
        actual.pushStack(newArr);
        actual.pop();
        expected.push(6);
        expected.pop();
        Assertions.assertEquals(expected.toString(), actual.toString());
        actual.popStack(2);
        expected.pop();
        expected.pop();
        Assertions.assertEquals(expected.toString(), actual.toString());
    }

    /**
     * Test number 2 from OOP tasks example.
     * It also has test for empty stack pop
     */
    @Test
    public void exampleTest() {
        Stack<Integer> actual = new Stack<>();
        actual.push(2);
        actual.push(7);
        Stack<Integer> newArr = new Stack<>();
        newArr.push(4);
        newArr.push(8);
        actual.pushStack(newArr);
        actual.pop();
        actual.popStack(2);
        Stack<Integer> expected = new Stack<>();
        expected.push(2);
        Assertions.assertEquals(expected.count(), actual.count());
        Assertions.assertEquals(expected.toString(), actual.toString());
    }

    /**
    * Test number 3 with a lot of random elements.
    */
    @Test
    public void thirdTest() {
        Random randomNumbers = new Random();
        int x1 = randomNumbers.nextInt(120);
        int x2 = randomNumbers.nextInt(300);
        int x3 = randomNumbers.nextInt(999);
        Stack<Integer> newArr = new Stack<>();
        newArr.push(x1);
        newArr.push(x2);
        newArr.push(x3);
        Stack<Integer> actual = new Stack<>();
        actual.pushStack(newArr);
        actual.pushStack(newArr);
        actual.pushStack(newArr);
        int expCount = 9;
        Assertions.assertEquals(expCount, actual.count());
        Stack<Integer> expected = new Stack<>();
        expected.push(x1);
        expected.push(x2);
        expected.push(x3);
        actual.popStack(6);
        Assertions.assertEquals(expected.toString(), actual.toString());
    }
}
