package ru.nsu.mbogdanov2.pizzeria;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * This class provides methods for working with a deque for different models.
 *
 * @param <T> - The type of data stored in a deque.
 */
public class MyBlockingDeque<T> {
    private final int size;
    private final Deque<T> deque;

    /**
     * Constructor of the class MyBlockingDeque.
     *
     * @param size maximal size of the deque.
     */
    public MyBlockingDeque(int size) {
        this.size = size;
        this.deque = new ArrayDeque<>();
    }

    /**
     * Checks if the deque is empty.
     *
     * @return true if deque is empty.
     */
    public synchronized boolean isEmpty() {
        return deque.isEmpty();
    }

    /**
     * Returns the number of objects in the deque.
     *
     * @return number of objects in the deque.
     */
    public synchronized int getSize() {
        return deque.size();
    }

    /**
     * Gets the object from the deque and deletes it.
     *
     * @return object from deque.
     * @throws InterruptedException in case of an error.
     */
    public synchronized T get() throws InterruptedException {
        while (deque.isEmpty()) {
            wait();
        }
        T object = deque.pop();
        notifyAll();
        return object;
    }

    /**
     * Gets several objects from the deque and deletes them.
     *
     * @param amount - amount of objects.
     * @return - objects from deque.
     * @throws IllegalArgumentException if the amount argument
     *                                  is not positive or exceeds the maximum deque size.
     * @throws InterruptedException     in case of an exception.
     */
    public synchronized List<T> get(int amount)
            throws IllegalArgumentException, InterruptedException {
        if (amount < 1 || amount > size) {
            throw new IllegalArgumentException();
        }
        while (deque.isEmpty()) {
            wait();
        }
        List<T> objects = new ArrayList<>();
        while (!deque.isEmpty() && objects.size() != amount) {
            objects.add(deque.pop());
        }
        return objects;
    }

    /**
     * Puts one object in a deque.
     *
     * @param object - the object to be added to the deque.
     * @throws InterruptedException in case of an exception.
     */
    public synchronized void put(@NotNull T object) throws InterruptedException {
        while (deque.size() == size) {
            wait();
        }
        deque.push(object);
        notifyAll();
    }

    /**
     * Contains method for tests to check the deque objects.
     *
     * @param object order
     * @return true if there are this order
     */
    public synchronized boolean contains(@NotNull T object) {
        return deque.contains(object);
    }
}
