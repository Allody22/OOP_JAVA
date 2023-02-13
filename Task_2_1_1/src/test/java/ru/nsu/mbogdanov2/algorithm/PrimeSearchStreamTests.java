package ru.nsu.mbogdanov2.algorithm;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import ru.nsu.mbogdanov2.PrimeSearchWithStreams;


/**
 * Unit tests for stream algorithm.
 */
public class PrimeSearchStreamTests {
    @Test
    public void nullPointerExceptionTest() {
        assertThrows(NullPointerException.class, () -> new PrimeSearchWithStreams().search(null));
    }

    @Test
    public void smallDataTestFalse() {
        int size = 70;
        int[] array = new int[size];
        Arrays.fill(array, 0, size, 7);
        assertFalse(new PrimeSearchWithStreams().search(array));
    }

    @Test
    public void smallDataTestTrue() {
        int size = 70;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 11);
        array[69] = 6;
        assertTrue(new PrimeSearchWithStreams().search(array));
    }

    @Test
    public void largeDataTestFalse() {
        int size = 1000000;
        int[] array = new int[size];
        Arrays.fill(array, 0, size, 115249);
        assertFalse(new PrimeSearchWithStreams().search(array));
    }

    @Test
    public void largeDataTestTrue() {
        int size = 1000000;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 115249);
        array[size - 1] = 66;
        assertTrue(new PrimeSearchWithStreams().search(array));
    }
}
