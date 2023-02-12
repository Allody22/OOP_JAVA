package ru.nsu.mbogdanov2;

import java.util.concurrent.ExecutionException;

/**
 * Casual class without stream and thread.
 */
public class PrimeSearch {

    public boolean primeCheck(int number) {
        if (number <= 1) {
            return false;
        } else if (number <= 3) {
            return true;
        } else if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0)
                return false;
        }
        return true;
    }

    public boolean search(int[] array) throws NullPointerException,
            InterruptedException, ExecutionException {
        if (array == null) {
            throw new NullPointerException();
        }
        for (int number : array) {
            if (!primeCheck(number)) {
                return true;
            }
        }
        return false;
    }

}