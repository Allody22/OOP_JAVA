package ru.nsu.mbogdanov2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Thread algorithm.
 * Callable is better than runnable because it throws exceptions and returns the result.
 */
public class PrimeSearchWithThread {
    private Deque<Integer> deque;

    /**
     * We just get the element with pop if we can.
     *
     * @return integer number
     */
    private synchronized Integer getNumber() {
        if (deque.isEmpty()) {
            return null;
        }
        return deque.pop();
    }

    /**
     * We go through the array using threads in this algorithm.
     * Firstly, we find how many processors are available.
     * Secondly, we create list of callable tasks for every number of processors.
     * Then we just check the result and shutdown tasks.
     */
    public boolean search(int[] array, boolean turnOnThreadsNumber, int threadsNumber)
            throws InterruptedException, ExecutionException {
        if (array == null) {
            throw new NullPointerException();
        }
        if (turnOnThreadsNumber) {
            threadsNumber = Runtime.getRuntime().availableProcessors();
        }
        deque = Arrays.stream(array).boxed().collect(Collectors.toCollection(ArrayDeque::new));

        Callable<Boolean> task = () -> {
            Integer number;
            while ((number = getNumber()) != null) {
                if (!primeCheck(number)) {
                    return true;
                }
            }
            return false;
        };
        List<Callable<Boolean>> tasks = new ArrayList<>();
        for (int i = 0; i < threadsNumber; ++i) {
            tasks.add(task);
        }

        ExecutorService pool = Executors.newFixedThreadPool(threadsNumber);
        List<Future<Boolean>> futureList = pool.invokeAll(tasks);
        for (Future<Boolean> future : futureList) {
            if (future.get()) {
                pool.shutdownNow();
                return true;
            }
        }
        pool.shutdown();
        return false;
    }

    /**
     * Realization of algorithm to check if the number is prime.
     *
     * @param number number that should be checked.
     * @return false if the number is not prime.
     */
    public static boolean primeCheck(int number) {
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
}
