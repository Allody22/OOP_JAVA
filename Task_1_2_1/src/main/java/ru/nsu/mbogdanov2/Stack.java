package ru.nsu.mbogdanov2;

import java.util.Arrays;


/**
 * My generic stack realization.
 *
 *@author Михаил Allody22 Богданов
 */
public class Stack<T> {

    int top;
    T[] stack;

    /**
     * Stack itself. with array stack to contain elements and int variable top to get last element
     */
    public Stack() {
      stack = (T[]) new Object[1];
      top = -1;
    }

    /**
     * push function to put new element into stack
     *
     * @param element - any variable of type T
     */

    public void push(T element) {
      if ((top + 1) == stack.length) {
        stack = Arrays.copyOf(stack, stack.length + 1);
      }
      stack[++top] = element;
    }

    /**
     * pop function to delete last stack element with error out of bounds handling that fails the
     * test
     */

    public void pop() {
      try {
        stack[top--] = null;
      } catch (ArrayIndexOutOfBoundsException exception) {
        throw new AssertionError("Stack is already empty,don't use pop when top = 0\n"
            + "Test failed, because of last pop");
      }
    }

    /**
     * count function to get the number of elements in stack
     */

    public int count() {
      return top + 1;
    }

    /**
     * pushStack function to put several new elements into stack
     *
     * @param NewStack - new array to combine its elements
     */

    public void pushStack(Stack<T> NewStack) {
      for (int i = 0; i < NewStack.stack.length; i++) {
        push(NewStack.stack[i]);
      }
    }

    /**
     * popStack function to delete several last stack elements with error out of bounds handling that
     * fails the test
     *
     * @param N - number of elements to delete
     */

    public void popStack(int N) {
      for (int i = 0; i < N; i++) {
        pop();
      }
    }

    /**
     * Override of toString method in order for the checks to work correctly with generics arrays, to
     * compare them
     */

    @Override
    public String toString() {
      return Arrays.toString(Arrays.copyOf(stack, top + 1));
    }
}
