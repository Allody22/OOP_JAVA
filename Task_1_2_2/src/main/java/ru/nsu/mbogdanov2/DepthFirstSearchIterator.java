package ru.nsu.mbogdanov2;

import java.util.ArrayDeque;
import java.util.ConcurrentModificationException;
import java.util.Deque;
import java.util.Iterator;

/**
 * DFS iterator class for Tree class.
 */
public class DepthFirstSearchIterator<T> implements Iterator<T> {

    private final Deque<Node<T>> stack = new ArrayDeque<>();

    public DepthFirstSearchIterator(Node<T> vertex) {
        this.stack.push(vertex);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public T next() {
        Node<T> next = stack.pop();
        for (var treeNode : next.getListOfChildren()) {
            stack.push(treeNode);
        }
        return next.getValue();
    }

    @Override
    public void remove() {
        throw new ConcurrentModificationException();
    }
}
