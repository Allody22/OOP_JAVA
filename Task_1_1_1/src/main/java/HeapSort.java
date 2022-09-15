/**
 * Heapsort algorithm.
 *
 *@author Михаил Allody22 Богданов
 */
public class HeapSort {
    /**
     * heapify function - swap elements.
     * we find the largest element among father and his sons
     * and make that largest element new father
     *
     * @param array array
     * @param i index of element
     * @param length length of array
     */
    public static void heapify(int[] array, int length, int i) {
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;
        int largest = i;
        if (leftChild < length && array[leftChild] > array[largest]) {
            largest = leftChild;
        }
        if (rightChild < length && array[rightChild] > array[largest]) {
            largest = rightChild;
        }
        if (largest != i) {
            int tmp = array[i];
            array[i] = array[largest];
            array[largest] = tmp;
            heapify(array, length, largest);
        }
    }
    /**
     * Heapsort - sort algorithm based on array (heap).
     * Array (4, 10, 3, 5, 1) becomes a binary heap:
     *          4 (0)
     *         /     \
     *      10 (1)   3 (2)
     *      /    \
     *   5 (3)  1 (4)
     * output - sorted array
     *
     * @param array array
     */

    public static int[] heapSort(int[] array) {
        int length = array.length;
        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify(array, length, i);
        }
        for (int i = length - 1; i >= 0; i--) {
            int tmp = array[0];
            array[0] = array[i];
            array[i] = tmp;
            heapify(array, i, 0);
        }
        return array;
    }
}