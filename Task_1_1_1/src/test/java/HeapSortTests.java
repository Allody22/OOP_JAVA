import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HeapSortTest {
    @Test
    public void SortedArrayTest(){
        int[] expected = new int[] {1,2,3,4};
        int []actual = HeapSort.heapSort(new int[] {1,2,3,4});
        Assertions.assertArrayEquals(expected,actual);
    }
    @Test
    public void EmptyArrayTest(){
        int[] expected = new int[] {};
        int [] actual = HeapSort.heapSort(new int[] {});
        Assertions.assertArrayEquals(expected,actual);
    }
    @Test
    public void ArrayTest(){
        int[] expected = new int[] {-1000,0,1000,2500,2578,3000};
        int[] actual = HeapSort.heapSort(new int[] {3000,1000,2500,2578,-1000,0});
        Assertions.assertArrayEquals(expected,actual);
    }
    @Test
    public void OneElementArrayTest(){
        int[] expected = new int[] {2};
        int[] actual = HeapSort.heapSort(new int[] {2});
        Assertions.assertArrayEquals(expected,actual);
    }
}