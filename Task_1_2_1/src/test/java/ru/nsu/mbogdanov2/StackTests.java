package ru.nsu.mbogdanov2;

import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class StackTest for testing my stack.
 */
public class StackTests {
    /**
     * Test for count function.
     */
    @Test
    public void countStackTest() {
        Stack<Integer> actual = new Stack<>();
        actual.push(6);
        actual.push(5);
        Stack<Integer> expected = new Stack<>();
        expected.push(6);
        expected.push(6);
        Assertions.assertEquals(actual.count(), expected.count());
    }

    /**
     * Test for pushStack function.
     * We push {4,8} in actual stack with pushStack
     * And we push {4},{8} in expected with pop twice
     */
    @Test
    public void pushStackTest() {
        Stack<Integer> actual = new Stack<>();
        Stack<Integer> newArr = new Stack<>();
        newArr.push(4);
        newArr.push(8);
        actual.pushStack(newArr);
        actual.pushStack(newArr);
        Stack<Integer> expected = new Stack<>();
        expected.push(4);
        expected.push(8);
        expected.push(4);
        expected.push(8);
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Test pop function.
     * We push 4 elements, then pop them
     * And see what pop function returns
     */
    @Test
    public void popTest() {
        Stack<Integer> actual = new Stack<>();
        actual.push(4);
        actual.push(4);
        actual.pop();
        int expected = 4;
        Assertions.assertEquals(expected, actual.pop());
    }

    /**
     * Test for popStack with random elements.
     * We push 3 random elements with push stack in actual array 3 times
     * Then we pop 8 elements with popStack
     */
    @Test
    public void randomElementsTest() {
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
        Stack<Integer> expected = new Stack<>();
        expected.push(x1);
        actual.popStack(8);
        Assertions.assertEquals(expected, actual);
    }
}
