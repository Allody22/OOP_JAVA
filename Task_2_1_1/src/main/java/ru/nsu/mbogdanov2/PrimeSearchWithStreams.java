package ru.nsu.mbogdanov2;


import static ru.nsu.mbogdanov2.PrimeSearchWithThread.primeCheck;

import java.util.Arrays;


/**
 * Simple algorithm with streams.
 */
public class PrimeSearchWithStreams extends PrimeSearch {
    /**
     * The same search algorithm but with streams.
     * We return true if there is at least 1 non-prime number
     */
    @Override
    public boolean search(int[] array) {
        if (array == null) {
            throw new NullPointerException();
        }
        return Arrays.stream(array).parallel().anyMatch(number -> !primeCheck(number));
    }
}
