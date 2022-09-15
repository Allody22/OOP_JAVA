import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class TestHeapSortTest for heapsort with tests.
 */
class HeapSortTest {
    /**
     * Test number 1 for already sorted array.
     */
    @Test
    public void firstTest() {

        int[] expected = new int[] {1, 2, 3, 4};
        int []actual = HeapSort.heapSort(new int[] {1, 2, 3, 4});

        Assertions.assertArrayEquals(expected, actual);
    }

    /**
     * Test number 2 for empty array.
     */
    @Test
    public void secondTest() {
        int[] expected = new int[] {};
        int [] actual = HeapSort.heapSort(new int[] {});
        Assertions.assertArrayEquals(expected, actual);
    }

    /**
     * Test number 3 for normal arrays.
     */
    @Test
    public void thirdTest(){
        int[] expected = new int[] {-1000, 0, 1000, 2500, 2578, 3000};
        int[] actual = HeapSort.heapSort(new int[] {3000, 1000, 2500, 2578, -1000, 0});
        Assertions.assertArrayEquals(expected, actual);
    }

    /**
     * Test number 4 for 1 element.
     */
    @Test
    public void fourthTest(){
        int[] expected = new int[] {2};
        int[] actual = HeapSort.heapSort(new int[] {2});
        Assertions.assertArrayEquals(expected, actual);
    }
}