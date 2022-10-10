package ru.nsu.mbogdanov2;

import java.util.Iterator;


/**
 * My realization of generic tree.
 *
 *@author Михаил Allody22 Богданов
 */
public class Tree<T> implements Iterable<T> {
    private Node<T> root;

    private boolean deepFirstSearch;

    public Tree() {
        super();
    }

    /**
     * Creating of tree, seating the root and choose DPS iterator.
     */

    @SuppressWarnings("unused")
    public Tree(Node<T> value) {
        this();
        setRoot(value);
        setDeepFirstSearch(true);
    }

    /**
     * push function to put new element into stack.
     *
     * @return number of nodes
     */

    public int getNumberOfNodesInTree() {
        Iterator<T> treeIterator = iterator();
        int number = 0;
        while (treeIterator.hasNext()) {
            treeIterator.next();
            number++;
        }
        return number;
    }

    /**
     * Method to get the root of this tree.
     *
     * @return root of this tree
     */
    public Node<T> getRoot() {
        return root;
    }

    public boolean getDeepFirstSearch() {
        return deepFirstSearch;
    }

    /**
     * Function to set the root of the tree.
     *
     * @param root - root of the tree with type Node T
     */

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    /**
     * Function to choose the iterator.
     * If the boolean value of this field is true - DFS iterator is on
     * Else BFS iterator turns on
     *
     * @param deepFirstSearch - root of the tree with type Node T
     */

    public void setDeepFirstSearch(boolean deepFirstSearch) {
        this.deepFirstSearch = deepFirstSearch;
    }

    /**
     * Overriding of iterator, where you can choose type of iterator.
     */
    @Override
    public Iterator<T> iterator() {
        if (deepFirstSearch) {
            return new DeepFirstSearchIterator<>(root);
        }
        return new BreadthFirstSearchIterator<>(root);
    }
}