package ru.nsu.mbogdanov2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.mbogdanov2.model.constant.State;
import ru.nsu.mbogdanov2.model.order.Order;
import ru.nsu.mbogdanov2.model.people.Baker;
import ru.nsu.mbogdanov2.pizzeria.MyBlockingDeque;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Tests for bakers. They simulate baker's work with orders.
 */
class BakerTest {
    private static final int MAX_SIZE = 10;
    private MyBlockingDeque<Order> queue;
    private MyBlockingDeque<Order> storage;
    private Baker baker;

    @BeforeEach
    void setUp() {
        queue = new MyBlockingDeque<>(MAX_SIZE);
        storage = new MyBlockingDeque<>(MAX_SIZE);
        baker = new Baker(1, 2, queue, storage);
    }

    @Test
    void testUse() throws InterruptedException {
        Order order = new Order(1);
        queue.put(order);
        assertEquals(order, baker.use());
        Assertions.assertTrue(queue.isEmpty());
        Assertions.assertFalse(storage.contains(order));
    }

    @Test
    void testProduce() throws InterruptedException {
        Order order = new Order(1);
        queue.put(order);
        baker.work();
        Assertions.assertTrue(storage.contains(order));
        assertEquals(State.IN_STOCK, order.getState());
        Assertions.assertTrue(queue.isEmpty());
    }


    @Test
    void testWork() throws InterruptedException {
        Order order = new Order(1);
        queue.put(order);
        baker.work();
        Assertions.assertTrue(storage.contains(order));
        assertEquals(State.IN_STOCK, order.getState());
        Assertions.assertTrue(queue.isEmpty());
    }

    @Test
    void testWorkSecond() throws InterruptedException {
        Order order = new Order(1);
        queue.put(order);

        Thread bakerThread = new Thread(baker::work);
        bakerThread.start();

        bakerThread.join();
        Assertions.assertEquals(State.IN_STOCK, order.getState());
        Assertions.assertEquals(order, storage.get());
    }
}
