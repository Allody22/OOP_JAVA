package ru.nsu.mbogdanov2.algorithm;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import ru.nsu.mbogdanov2.PrimeSearch;


/**
 * Tests for simple algorithm.
 */
public class PrimeSearchTests {

    @Test
    public void oneNumberTest() {
        assertTrue(new PrimeSearch().primeCheck(3));
    }

    @Test
    public void nullPointerExceptionTest() {
        assertThrows(NullPointerException.class, () -> new PrimeSearch().search(null));
    }

    @Test
    public void smallDataTestFalse() throws ExecutionException, InterruptedException {
        int size = 70;
        int[] array = new int[size];
        Arrays.fill(array, 0, size, 17);
        assertFalse(new PrimeSearch().search(array));
    }

    @Test
    public void smallDataTestTrue() throws ExecutionException, InterruptedException {
        int size = 70;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 13);
        array[69] = 100;
        assertTrue(new PrimeSearch().search(array));
    }

    @Test
    public void largeDataTestFalse() throws ExecutionException, InterruptedException {
        int size = 100000;
        int[] array = new int[size];
        Arrays.fill(array, 0, size, 2624183);
        assertFalse(new PrimeSearch().search(array));
    }

    @Test
    public void largeDataTestTrue() throws ExecutionException, InterruptedException {
        int size = 100000;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 262419);
        array[size - 1] = 22222228;
        assertTrue(new PrimeSearch().search(array));
    }
}
