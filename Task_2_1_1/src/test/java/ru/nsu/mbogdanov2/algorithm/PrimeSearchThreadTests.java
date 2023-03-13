package ru.nsu.mbogdanov2.algorithm;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import ru.nsu.mbogdanov2.PrimeSearchWithThread;


/**
 * The same tests for algorithm with threads.
 */
public class PrimeSearchThreadTests {
    @Test
    public void search_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PrimeSearchWithThread()
                .search(null, true, 0));
    }

    @Test
    public void search_smallData1() throws ExecutionException, InterruptedException {
        int size = 100;
        int[] array = new int[size];
        Arrays.fill(array, 0, size, 17);
        assertFalse(new PrimeSearchWithThread().search(array, true, 0));
    }

    @Test
    public void search_smallData2() throws ExecutionException, InterruptedException {
        int size = 100;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 13);
        array[size - 1] = 222;
        assertTrue(new PrimeSearchWithThread().search(array, true, 0));
    }

    @Test
    public void search_largeData1() throws ExecutionException, InterruptedException {
        int size = 10000000;
        int[] array = new int[size];
        Arrays.fill(array, 0, size, 262411);
        assertFalse(new PrimeSearchWithThread().search(array, true, 0));
    }

    @Test
    public void search_largeData2() throws ExecutionException, InterruptedException {
        int size = 10000000;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 1046527);
        array[size - 1] = 444;
        assertTrue(new PrimeSearchWithThread().search(array, true, 0));
    }
}
