package ru.nsu.mbogdanov2.participants;

/**
 * The Consumer interface must be implemented by any class who can use or consume anything.
 *
 * @param <T> - type of consumed objects.
 */
public interface User<T> {
    /**
     * The implementation of this method must allow the class
     * to consume an object from some shared resource.
     *
     * @return - consumed object.
     */
    T use();
}