package ru.nsu.mbogdanov2;

import java.util.ArrayDeque;
import java.util.ConcurrentModificationException;
import java.util.Deque;
import java.util.Iterator;

public class DeepFirstSearchIterator<T> implements Iterator<T> {

    private final Deque<Node<T>> stack = new ArrayDeque<>();

    public DeepFirstSearchIterator(Node<T> vertex) {
        this.stack.push(vertex);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public T next() {
        Node<T> next = stack.pop();

        for (Node<T> tNode: next.getListOfChildren()) {
            stack.push(tNode);
        }

        return next.getValue();
    }

    @Override
    public void remove() {
        throw new ConcurrentModificationException();
    }
}
