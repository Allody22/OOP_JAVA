package ru.nsu.mbogdanov2;

import static ru.nsu.mbogdanov2.PrimeSearchWithThread.primeCheck;

import org.jetbrains.annotations.NotNull;

/**
 * Casual class without stream and thread.
 */
public class PrimeSearch {

    /**
     * We check every number of the input array.
     * Return true if there are at least one not prime number.
     */
    public boolean search(int @NotNull [] array) {
        for (int number : array) {
            if (!primeCheck(number)) {
                return true;
            }
        }
        return false;
    }

}