package ru.nsu.mbogdanov2;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void setRootValueTest() {
        Node<Integer> actualRoot = new Node<>(10);
        Integer expectedRoot = 10;
        Assertions.assertEquals(actualRoot.getValue(), expectedRoot);
    }

    /**
     * Test for getRoot function with special equals method.
     */
    @Test
    public void sameRootTest() {
        Node<Integer> actualRoot = new Node<>(10);
        Node<Integer> expectedRoot = new Node<>(10);
        Assertions.assertEquals(actualRoot, expectedRoot);
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
     * Test of removeChildrenAt function.
     * We make actual tree and compare ArrayList
     */
    @Test
    public void removeChildrenTest() {
        Node<String> root = new Node<>("FirstLevel");
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
        Assertions.assertEquals(root.getNumberOfNodesInTree(), expected);
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

    /**
     * Test for function that returns the root.
     */
    @Test
    public void getRootTest() {
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
        childA.getRoot();
        Assertions.assertEquals(root, childA.getRoot());
    }

    /**
     * Test for modCount function.
     * It returns the number of modifications in tree
     */
    @Test
    public void modCountTest() {
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
        Assertions.assertEquals(7, root.getModCount());
    }
}
