package ru.nsu.mbogdanov2;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS iterator class for Tree class.
 */
public class BreadthFirstSearchIterator<T> implements Iterator<T> {

    Queue<Node<T>> queue = new LinkedList<>();
    private final int modCount;


    public BreadthFirstSearchIterator(Node<T> vertex) {
        queue.add(vertex);
        modCount = vertex.getModCount();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public T next() {
        Node<T> next = queue.poll();
        assert next != null;
        if (modCount != next.getModCount()) {
            throw new ConcurrentModificationException("Don't change the collection "
                    + "while iterator is on");
        }
        queue.addAll(next.getListOfChildren());
        return next.getValue();
    }
}
