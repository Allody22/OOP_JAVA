package ru.nsu.mbogdanov2;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearchIterator<T> implements Iterator<T> {

    Queue<Node<T>> queue = new LinkedList<>();

    public BreadthFirstSearchIterator(Node<T> vertex) {
        this.queue.add( vertex);
    }


    @Override
    public void remove() {
        throw new ConcurrentModificationException();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public T next() {
        Node<T> next = queue.poll();
        assert next != null;
        queue.addAll(next.getListOfChildren());
        return next.getValue();
    }
}
