import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class StackTest for testing my stack.
 */
public class StackTests {
  /**
   * Test number 1 with small array.
   * All functions are tested here
   */
  @Test
  public void firstTest() {
    Stack<Integer> actual = new Stack<>();
    actual.push(6);
    actual.push(5);
    Stack<Integer> expected = new Stack<>();
    expected.push(6);
    expected.push(6);
    Assertions.assertEquals(actual.count(),expected.count());
    actual.pop();
    Stack<Integer> NewArr = new Stack<>();
    NewArr.push(6);
    NewArr.push(6);
    actual.pushStack(NewArr);
    actual.pop();
    expected.push(6);
    expected.pop();
    Assertions.assertEquals(expected.toString(),actual.toString());
    actual.popStack(2);
    expected.pop();
    expected.pop();
    Assertions.assertEquals(expected.toString(),actual.toString());
  }

  /**
   * Test number 2 from example.
   */
  @Test
  public void secondTest() {
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
    Assertions.assertEquals(expected.count(),actual.count());
    Assertions.assertEquals(expected.toString(),actual.toString());
  }

  /**
   * Test number 3 with a lot of elements.
   */
  @Test
  public void thirdTest() {
    Stack<Integer> actual = new Stack<>();
    Stack<Integer> NewArr = new Stack<>();
    NewArr.push(5);
    NewArr.push(2);
    NewArr.push(2);
    actual.pushStack(NewArr);
    actual.pushStack(NewArr);
    actual.pushStack(NewArr);
    int expCount = 9;
    Assertions.assertEquals(expCount,actual.count());
    Stack<Integer> expected = new Stack<>();
    expected.push(5);
    actual.popStack(8);
    Assertions.assertEquals(expected.toString(),actual.toString());
  }
}
