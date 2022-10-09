package ru.nsu.mbogdanov2;

import java.util.ArrayList;
import java.util.List;

/**
 * Node class to define vertex.
 */
public class Node<T> {
    private T value;
    private final List<Node<T>> listOfChildren;

    private Node<T> parent;

    /**
     * Node constructor that calls Tree constructor if it's needed.
     */

    public Node() {
        super();
        listOfChildren = new ArrayList<>();
    }

    public Node(T value) {
        this();
        setValue(value);
    }

    /**
     * Get value function to get the value of this node.
     */

    public T getValue() {
        return value;
    }

    /**
     * Set parent function to set the parent of this node.
     */

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    /**
     * Function that return list of children of this node.
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
     */

    public int getNumberOfChildren() {
        return listOfChildren.size();
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
     * Function that returns child of this node.
     *
     * @param index - id of the child that should be removed
     */
    public void removeChildAt(int index) {
        if (index > listOfChildren.size() - 1) {
            throw new IndexOutOfBoundsException("This index is incorrect");
        }
        else {
            Node<T> element = this.listOfChildren.get(index);
            if (element.listOfChildren.size() > 0) {
                this.listOfChildren.addAll(element.listOfChildren);
                element.listOfChildren.forEach(child -> child.setParent(child.parent));
            }
            listOfChildren.remove(index);
        }
    }

}
