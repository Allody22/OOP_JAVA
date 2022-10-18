package ru.nsu.mbogdanov2;


import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Different test for BFS iterator.
 */
public class BreadthFirstSearchTests {
    @Test
    public void algorithmTest() {
        Node<Integer> root = new Node<>(10);
        Node<Integer> childA = new Node<>(11);
        root.addChildren(childA);
        Node<Integer> childA2 = new Node<>(111);
        root.addChildren(childA2);
        Node<Integer> childB = new Node<>(21);
        childA.addChildren(childB);
        Node<Integer> childB2 = new Node<>(211);
        childA.addChildren(childB2);
        Node<Integer> childB3 = new Node<>(221);
        childA2.addChildren(childB3);
        Node<Integer> childC = new Node<>(311);
        childB.addChildren(childC);
        BreadthFirstSearchIterator<Integer> bfs = new BreadthFirstSearchIterator<>(root);
        ArrayList<Integer> actualSearchList = new ArrayList<>();
        ArrayList<Integer> expectedSearchList =
                new ArrayList<>(Arrays.asList(10, 11, 111, 21, 211, 221, 311));
        while (bfs.hasNext()) {
            actualSearchList.add(bfs.next());
        }
        Assertions.assertEquals(actualSearchList, expectedSearchList);
    }

    @Test
    public void hashNextTest() {
        Node<Integer> root = new Node<>(10);
        Node<Integer> childA = new Node<>(11);
        root.addChildren(childA);
        Node<Integer> childA2 = new Node<>(111);
        root.addChildren(childA2);
        Node<Integer> childB = new Node<>(21);
        childA.addChildren(childB);
        Node<Integer> childB2 = new Node<>(211);
        childA.addChildren(childB2);
        Node<Integer> childB3 = new Node<>(221);
        childA2.addChildren(childB3);
        BreadthFirstSearchIterator<Integer> bfs = new BreadthFirstSearchIterator<>(root);
        for (int i = 0; i < 6; i++) {
            Assertions.assertTrue(bfs.hasNext());
            bfs.next();
        }
        Assertions.assertFalse(bfs.hasNext());
    }

    /** Test that my DFS gives exceptions.
     * We walk through the tree and call hasNext() and next()
     * Then we make some modifications in tree and call iterator again
     * We will see error message about modifications
     */
    @Test
    public void modificationsExceptionsTest() {
        Node<Integer> root = new Node<>(10);
        Node<Integer> childA = new Node<>(11);
        root.addChildren(childA);
        Node<Integer> childA2 = new Node<>(111);
        root.addChildren(childA2);
        Node<Integer> childB = new Node<>(21);
        childA.addChildren(childB);
        Node<Integer> childB2 = new Node<>(211);
        childA.addChildren(childB2);
        BreadthFirstSearchIterator<Integer> bfs = new BreadthFirstSearchIterator<>(root);
        bfs.next();
        bfs.next();
        childA.addChildren(childB2);
        ConcurrentModificationException exception =
                assertThrows(ConcurrentModificationException.class, bfs::next);
        Assertions.assertEquals("Don't change the collection"
                + " while iterator is on", exception.getMessage());
    }

    /** Test that my DFS doesn't give redundant exceptions.
     * We walk through the tree and call hasNext() and next()
     * methods for existing elements
     */
    @Test
    public void noExceptionsTest() {
        Node<Integer> root = new Node<>(10);
        Node<Integer> childA = new Node<>(11);
        root.addChildren(childA);
        Node<Integer> childA2 = new Node<>(111);
        root.addChildren(childA2);
        Node<Integer> childB = new Node<>(21);
        childA.addChildren(childB);
        BreadthFirstSearchIterator<Integer> bfs = new BreadthFirstSearchIterator<>(root);
        Assertions.assertDoesNotThrow(bfs::hasNext);
        Assertions.assertDoesNotThrow(bfs::next);
        Assertions.assertDoesNotThrow(bfs::hasNext);
        Assertions.assertDoesNotThrow(bfs::next);
        Assertions.assertDoesNotThrow(bfs::hasNext);
        Assertions.assertDoesNotThrow(bfs::next);
        Assertions.assertDoesNotThrow(bfs::hasNext);
        Assertions.assertDoesNotThrow(bfs::next);
    }
}
