package ru.nsu.mbogdanov2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Node class to define vertex.
 */
public class Node<T> implements Iterable<T> {
    private T value;
    private final List<Node<T>> listOfChildren;
    private int typeOfFirstSearch;
    private Node<T> parent;

    /**
     * Node constructor that calls Tree constructor if it's needed.
     */

    public Node() {
        listOfChildren = new ArrayList<>();
    }

    /**
     * Constructor for node with value.
     *
     * @param value - generic type value
     */
    public Node(T value) {
        this();
        setValue(value);
        setTypeOfFirstSearch(1);
        parent = null;
    }

    /**
     * Get value function to get the value of this node.
     *
     * @return type T value (data) of this node
     */

    public T getValue() {
        return value;
    }

    /**
     * Set parent function to set the parent of this node.
     *
     * @param parent of current node
     */

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    /**
     * Function that return list of children of this node.
     *
     * @return list of children nodes of this node
     */

    public List<Node<T>> getListOfChildren() {
        return listOfChildren;
    }

    /**
     * Set value function that reminds the value of this node.
     *
     * @param value - type T value of the node
     */

    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Function that return number of children of this node.
     *
     * @return number of children of this node
     */


    public int getNumberOfChildren() {
        return listOfChildren.size();
    }

    /**
     * Function to choose the iterator.
     * If the boolean value of this field is true - DFS iterator is on
     * Else BFS iterator becomes main iterator
     *
     * @param typeOfFirstSearch - number 1 for DFS or number 2 for BFS
     */

    public void setTypeOfFirstSearch(int typeOfFirstSearch) {
        this.typeOfFirstSearch = typeOfFirstSearch;
    }

    /**
     * Function that add children to this node.
     * It save child node into the next free cell
     *
     * @param child - type T child node
     */

    public void addChildren(Node<T> child) {
        parent = this;
        listOfChildren.add(child);
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
     * Function that returns child of this node.
     *
     * @param index - id of the child that should be removed
     */
    public void removeChildAt(int index) {
        if (index > listOfChildren.size() - 1 || index < 0) {
            throw new IndexOutOfBoundsException("This index is incorrect");
        }
        Node<T> element = this.listOfChildren.get(index);
        if (element.listOfChildren.size() > 0) {
            this.listOfChildren.addAll(element.listOfChildren);
            element.listOfChildren.forEach(child -> child.setParent(child.parent));
        }
        listOfChildren.remove(index);
    }

    /**
     * Override of equals method for nodes.
     *
     * @param o some node to compare
     * @return boolean value of the comparing
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Node)) {
            return false;
        }
        Node<?> node = (Node<?>) o;
        return value.equals(node.value) && Objects.equals(listOfChildren, node.listOfChildren)
                && Objects.equals(parent, node.parent);
    }

    /**
     * Override of hash function for nodes.
     *
     * @return hash of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(value, listOfChildren, parent);
    }

    @Override
    public Iterator<T> iterator() {
        if (typeOfFirstSearch == 1) {
            return new DepthFirstSearchIterator<>(this);
        }
        return new BreadthFirstSearchIterator<>(this);
    }
}
