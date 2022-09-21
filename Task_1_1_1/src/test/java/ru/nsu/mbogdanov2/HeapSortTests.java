package ru.nsu.mbogdanov2;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class HeapSortTest for test heapsort.
 */
class HeapSortTests {
    /**
     * Test for already sorted array.
     */
    @Test
    public void sortedArrayTest() {

        int[] expected = new int[] {1, 2, 3, 4};
        int[] actual = HeapSort.heapSort(new int[] {1, 2, 3, 4});

        Assertions.assertArrayEquals(expected, actual);
    }

    /**
     * Test for empty array.
     */
    @Test
    public void emptyArrayTest() {
        int[] expected = new int[] {};
        int[] actual = HeapSort.heapSort(new int[] {});
        Assertions.assertArrayEquals(expected, actual);
    }

    /**
     * Test for normal arrays.
     */
    @Test
    public void normalArrayTest() {
        int[] expected = new int[] {-1000, 0, 1000, 2500, 2578, 3000};
        int[] actual = HeapSort.heapSort(new int[] {3000, 1000, 2500, 2578, -1000, 0});
        Assertions.assertArrayEquals(expected, actual);
    }

    /**
     * Test for 1 element.
     */
    @Test
    public void oneElementArrayTest() {
        int[] expected = new int[] {2};
        int[] actual = HeapSort.heapSort(new int[] {2});
        Assertions.assertArrayEquals(expected, actual);
    }
    
    /**
     * Test for array with random elements.
     * Import of java.util.Random helps us to ger random variable
     */
    @Test
    public void randomArrayTest() { 
        Random randomNumber = new Random();
        int[] randomArray = new int[7];
        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = randomNumber.nextInt(7);
        }
        int[] expected = randomArray;
        int[] actual = HeapSort.heapSort(randomArray);
        Arrays.sort(expected);
        Assertions.assertArrayEquals(expected, actual);
    }
}
