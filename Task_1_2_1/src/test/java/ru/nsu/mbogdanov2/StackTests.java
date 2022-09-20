package ru.nsu.mbogdanov2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Random;

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
      Stack<Integer> NewArr = new Stack<>();
      NewArr.push(6);
      NewArr.push(6);
      actual.pushStack(NewArr);
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
     */
    @Test
    public void exampleTest() {
      Stack<Integer> actual = new Stack<>();
      actual.push(2);
      actual.push(7);
      Stack<Integer> NewArr = new Stack<>();
      NewArr.push(4);
      NewArr.push(8);
      actual.pushStack(NewArr);
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
      Stack<Integer> actual = new Stack<>();
      Stack<Integer> NewArr = new Stack<>();
      int x1 = randomNumbers.nextInt(120);
      int x2 = randomNumbers.nextInt(300);
      int x3 = randomNumbers.nextInt(999);
      NewArr.push(x1);
      NewArr.push(x2);
      NewArr.push(x3);
      actual.pushStack(NewArr);
      actual.pushStack(NewArr);
      actual.pushStack(NewArr);
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
