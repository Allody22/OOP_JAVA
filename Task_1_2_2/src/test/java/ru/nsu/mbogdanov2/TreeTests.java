package ru.nsu.mbogdanov2;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Different test for tree realization.
 */
public class TreeTests {
    /**
     * Test for setRoot function.
     */
    @Test
    public void SetRootTest() {
        Node<Integer> actualRoot = new Node<>(10);
        Integer expectedRoot = 10;
        Assertions.assertEquals(actualRoot.getValue(), expectedRoot);
    }

    /**
     * Test for addChildren function with the help of numberOfChildren function.
     */
    @Test
    public void numberOfChildrenTest() {
        Node<Integer> root = new Node<>(10);
        Node<Integer> childA = new Node<>(322);
        root.addChildren(childA);
        Node<Integer> childA2 = new Node<>(3221);
        root.addChildren(childA2);
        Node<Integer> childB = new Node<>(522);
        childA.addChildren(childB);
        Node<Integer> childB2 = new Node<>(5221);
        childA.addChildren(childB2);
        Node<Integer> childB3 = new Node<>(6221);
        childA2.addChildren(childB3);
        root.removeChildAt(0);
        Node<Integer> childC = new Node<>(722);
        childB.addChildren(childC);
        Assertions.assertEquals(root.getNumberOfChildren(), 3);
    }

    /**
     * Test of Deep first search iterator.
     * We make actual tree and compare ArrayList
     */
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
        DeepFirstSearchIterator<Integer> dfs = new DeepFirstSearchIterator<>(root);
        ArrayList<Integer> actualSearchList = new ArrayList<>();
        ArrayList<Integer> expectedSearchList =
                new ArrayList<>(Arrays.asList(10, 111, 221, 11, 211, 21, 311));
        while (dfs.hasNext()) {
            actualSearchList.add(dfs.next());
        }
        Assertions.assertEquals(actualSearchList, expectedSearchList);
    }

    /**
     * Test of removeChildrenAt function.
     * We make actual tree and compare ArrayList
     */
    @Test
    public void removeChildrenTest() {
        Tree<String> actual = new Tree<>();
        Node<String> root = new Node<>("FirstLevel");
        actual.setRoot(root);
        Node<String> childA = new Node<>("SecondLevelOne");
        root.addChildren(childA);
        Node<String> childA2 = new Node<>("SecondLevelTwo");
        root.addChildren(childA2);
        Node<String> childB = new Node<>("ThirdLevelOne");
        childA.addChildren(childB);
        Node<String> childB2 = new Node<>("ThirdLevelTwo");
        childA.addChildren(childB2);
        Node<String> childB3 = new Node<>("ThirdLevelThree");
        childA2.addChildren(childB3);
        root.removeChildAt(0);
        Node<String> childC = new Node<>("FourthLevel");
        childB.addChildren(childC);
        int expected = 6;
        Assertions.assertEquals(actual.getNumberOfNodesInTree(), expected);
    }

    /**
     * Exception test.
     * I show that my removeChildAd method have checks for invalid indexes
     */
    @Test
    public void invalidNumberOfChildrenTest() {
        Node<Integer> root = new Node<>(10);
        Node<Integer> childA = new Node<>(11);
        root.addChildren(childA);
        IndexOutOfBoundsException exception =
                assertThrows(IndexOutOfBoundsException.class, () -> root.removeChildAt(-1));
        Assertions.assertEquals("This index is incorrect", exception.getMessage());
    }
}
