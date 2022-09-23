package ru.nsu.mbogdanov2;

import java.util.Arrays;
import java.util.Objects;


/**
 * My generic stack realization.
 *
 *@author Михаил Allody22 Богданов
 */
public class Stack<T> {

    int top;
    T[] stack;

    /**
     * Stack itself.
     * Its also has array stack to contain elements
     * and int variable top to get last element
     */
    public Stack() {
        stack = (T[]) new Object[1];
        top = -1;
    }

    /**
     * push function to put new element into stack.
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
     * pop function to delete last stack element.
     * Its also handle error with empty stack and return null
     */

    public T[] pop() {
        if (top == 0) {
            return null;
        }
        top--;
        return stack = Arrays.copyOfRange(stack, 0, stack.length - 1);
    }

    /**
     * Count function to get the number of elements in stack.
     */

    public int count() {
        return top + 1;
    }

    /**
     * pushStack function to put several new elements into stack.
     *
     * @param newStack - new array to combine its elements
     */

    public void pushStack(Stack<T> newStack) {
        for (int i = 0; i < newStack.stack.length; i++) {
            push(newStack.stack[i]);
        }
    }

    /**
     * popStack function to delete several last stack elements.
     * Its also handle error with empty stack and return null
     *
     * @param n - number of elements to delete
     */

    public void popStack(int n) {
        for (int i = 0; i < n; i++) {
            pop();
        }
    }

    /**
     * Override of equals method.
     * It's needed for checks to work correctly with generics arrays, to compare them
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Stack)) {
            return false;
        }
        Stack<?> stack1 = (Stack<?>) o;
        return top == stack1.top && Arrays.equals(stack, stack1.stack);
    }

    /**
     * Override of hashCode method.
     * It's needed for checks to work correctly with generics arrays, to compare them
     */

    @Override
    public int hashCode() {
        int result = Objects.hash(top);
        result = 31 * result + Arrays.hashCode(stack);
        return result;
    }

}
