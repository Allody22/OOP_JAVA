package ru.nsu.mbogdanov2;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Different test for DFS iterator.
 */
public class DepthFirstSearchTests {
    @Test
    public void deepFirstSearchTest() {
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
        DepthFirstSearchIterator<Integer> dfs = new DepthFirstSearchIterator<>(root);
        ArrayList<Integer> actualSearchList = new ArrayList<>();
        ArrayList<Integer> expectedSearchList =
                new ArrayList<>(Arrays.asList(10, 111, 221, 11, 211, 21, 311));
        while (dfs.hasNext()) {
            actualSearchList.add(dfs.next());
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
        DepthFirstSearchIterator<Integer> dfs = new DepthFirstSearchIterator<>(root);
        for (int i = 0; i <6; i ++){
            Assertions.assertTrue(dfs.hasNext());
            dfs.next();
        }
        Assertions.assertFalse(dfs.hasNext());
    }
}
