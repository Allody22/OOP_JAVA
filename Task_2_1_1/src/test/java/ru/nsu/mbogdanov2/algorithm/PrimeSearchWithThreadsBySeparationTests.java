package ru.nsu.mbogdanov2.algorithm;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import ru.nsu.mbogdanov2.PrimeSearchWithThreadsBySeparation;


/**
 * Testing class for threads algorithm with separated array.
 */
public class PrimeSearchWithThreadsBySeparationTests {
    @Test
    public void search_throwsNullPointerException() {
        assertThrows(IllegalStateException.class, () -> new PrimeSearchWithThreadsBySeparation()
                .search(0, true, new ArrayList<>()));
    }

    @Test
    public void smallDataTestFalse() {
        int size = 70;
        Integer[] array = new Integer[size];
        Arrays.fill(array, 0, size, 17);
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, array);
        assertFalse(new PrimeSearchWithThreadsBySeparation().search(2, true, list));
    }

    @Test
    public void smallDataTestTrue() {
        int size = 70;
        Integer[] array = new Integer[size];
        Arrays.fill(array, 0, size - 1, 13);
        array[69] = 100;
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, array);
        assertTrue(new PrimeSearchWithThreadsBySeparation().search(8, true, list));
    }

    @Test
    public void largeDataTestFalse() {
        int size = 100000;
        Integer[] array = new Integer[size];
        Arrays.fill(array, 0, size, 2624183);
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, array);
        assertFalse(new PrimeSearchWithThreadsBySeparation().search(8, true, list));
    }

    @Test
    public void largeDataTestTrue() {
        int size = 100000;
        Integer[] array = new Integer[size];
        Arrays.fill(array, 0, size - 1, 262419);
        array[size - 1] = 22222228;
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, array);
        assertTrue(new PrimeSearchWithThreadsBySeparation().search(8, true, list));
    }
}
