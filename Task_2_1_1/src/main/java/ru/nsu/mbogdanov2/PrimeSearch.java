package ru.nsu.mbogdanov2;

import java.util.concurrent.ExecutionException;

/**
 * Casual class without stream and thread.
 */
public class PrimeSearch {
    /**
     * Function to check the number of divisors on the number.
     * I found it on the stackoverflow, and it's not bad.
     *
     * @param number number that need to be checked
     * @return true if the number is prime
     */
    public boolean primeCheck(int number) {
        if (number <= 1) {
            return false;
        } else if (number <= 3) {
            return true;
        } else if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * We check every number of the input array.
     * Return true if there are at least one not prime number.
     */
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