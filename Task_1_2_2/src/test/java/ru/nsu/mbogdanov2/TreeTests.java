package ru.nsu.mbogdanov2;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests
 */
public class TreeTests {
    /**
     * Test for addChildren function with the help of numberOfChildren function.
     */
    @Test
    public void numberOfChildrenTest() {
        Node <Integer> root = new Node<>(10);
        Node <Integer> ChildA = new Node<>(322);
        Node <Integer> ChildA2 = new Node<>(3221);
        Node <Integer> ChildB = new Node<>(522);
        Node <Integer> ChildB2 = new Node<>(5221);
        Node <Integer> ChildB3 = new Node<>(6221);
        root.addChildren(ChildA);
        root.addChildren(ChildA2);
        ChildA.addChildren(ChildB);
        ChildA.addChildren(ChildB2);
        ChildA2.addChildren(ChildB3);
        root.removeChildAt(0);
        Node <Integer> ChildC = new Node<>(722);
        ChildB.addChildren(ChildC);
        Assertions.assertEquals(root.getNumberOfChildren(),3);
    }

    /**
     * Test of Deep first search iterator.
     * We make actual tree and compare ArrayList
     */
    @Test
    public void deepFirstSearchTest() {
        Node <Integer> root = new Node<>(10);
        Node <Integer> childA = new Node<>(11);
        Node <Integer> childA2 = new Node<>(111);
        Node <Integer> childB = new Node<>(21);
        Node <Integer> childB2 = new Node<>(211);
        Node <Integer> childB3 = new Node<>(221);
        root.addChildren(childA);
        root.addChildren(childA2);
        childA.addChildren(childB);
        childA.addChildren(childB2);
        childA2.addChildren(childB3);
        Node <Integer> ChildC = new Node<>(311);
        childB.addChildren(ChildC);
        DeepFirstSearchIterator<Integer> dfs= new DeepFirstSearchIterator<>(root);
        ArrayList<Integer> actualDFSList = new ArrayList<>();
        ArrayList<Integer> expectedDFSList = new ArrayList<>(Arrays.asList(10,111,221,11,211,21,311));
        while (dfs.hasNext()){
            actualDFSList.add(dfs.next());
        }
        Assertions.assertEquals(actualDFSList, expectedDFSList);
    }

    /**
     * Test of removeChildrenAt function.
     * We make actual tree and compare ArrayList
     */
    @Test
    public void removeChildrenTest() {
        Node <Integer> root = new Node<>(10);
        Tree <Integer> actual = new Tree<>(root);
        Node <Integer> childA = new Node<>(11);
        Node <Integer> childA2 = new Node<>(111);
        Node <Integer> childB = new Node<>(21);
        Node <Integer> childB2 = new Node<>(211);
        Node <Integer> childB3 = new Node<>(221);
        root.addChildren(childA);
        root.addChildren(childA2);
        childA.addChildren(childB);
        childA.addChildren(childB2);
        childA2.addChildren(childB3);
        root.removeChildAt(0);
        Node <Integer> ChildC = new Node<>(311);
        childB.addChildren(ChildC);
        int expected = 6;
        Assertions.assertEquals(actual.getNumberOfNodesInTree(),expected);
    }
}
