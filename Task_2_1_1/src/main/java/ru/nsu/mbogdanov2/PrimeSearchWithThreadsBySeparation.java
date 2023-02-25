package ru.nsu.mbogdanov2;

import java.util.Arrays;
import java.util.List;


/**
 * Algorithm with separating of inputted array.
 */
public class PrimeSearchWithThreadsBySeparation {
    private boolean notPrimeNumber = false;

    /**
     * Main function that tries to find not prime
     * number in the list of numbers.
     *
     * @return false if there are only prime numbers.
     */
    public boolean search(int threadsNumber, boolean turnOnThreadsNumber, List<Integer> primeList) {
        if (primeList.isEmpty()) {
            throw new NullPointerException();
        }
        if (turnOnThreadsNumber) {
            threadsNumber = Runtime.getRuntime().availableProcessors();
        }
        int listPart;
        if (primeList.size() >= threadsNumber) {
            listPart = 1;
            threadsNumber = primeList.size();
        } else {
            listPart = primeList.size() / threadsNumber;
        }
        MyThread[] threads = new MyThread[threadsNumber];

        int leftPart;
        int rightPart;
        for (int i = 0; i < threadsNumber; i++) {
            leftPart = listPart * i;
            if (i == threadsNumber - 1) {
                rightPart = primeList.size();
            } else {
                rightPart = listPart * (i + 1);
            }
            threads[i] = new MyThread(primeList.subList(leftPart, rightPart));
            threads[i].start();
        }
        checkThreads(threads);
        return notPrimeNumber;
    }

    /**
     * We check that all created threads must be finished.
     */
    public void checkThreads(MyThread[] threads) {
        Arrays.stream(threads).forEach(myThread -> {
            try {
                myThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Class that creates my own threads with mu own run method.
     */
    public class MyThread extends Thread {
        private final List<Integer> primeList;

        /**
         * Constructor for my own threads.
         */
        public MyThread(List<Integer> numbers) {
            this.primeList = numbers;
        }

        /**
         * Override Run method for thread.
         */
        @Override
        public void run() {
            for (Integer i : primeList) {
                if (!PrimeSearchWithThread.primeCheck(i)) {
                    notPrimeNumber = true;
                    break;
                }
            }
        }
    }
}