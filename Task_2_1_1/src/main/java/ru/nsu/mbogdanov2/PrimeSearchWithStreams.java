package ru.nsu.mbogdanov2;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Simple algorithm with streams.
 */
public class PrimeSearchWithStreams extends PrimeSearch {
    @Override
    public boolean search(int[] array) throws NullPointerException {
        if (array == null) {
            throw new NullPointerException();
        }
        IntStream intStream = Arrays.stream(array);
        return intStream.parallel().anyMatch(number -> !primeCheck(number));
    }
}
