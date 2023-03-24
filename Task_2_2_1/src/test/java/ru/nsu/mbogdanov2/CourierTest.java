package ru.nsu.mbogdanov2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.mbogdanov2.model.constant.State;
import ru.nsu.mbogdanov2.model.order.Order;
import ru.nsu.mbogdanov2.model.people.Courier;
import ru.nsu.mbogdanov2.pizzeria.MyBlockingDeque;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for courier entity.
 */
class CourierTest {

    private static final int DEFAULT_CAPACITY = 5;
    private static final int DEFAULT_BAG_CAPACITY = 3;

    private MyBlockingDeque<Order> storage;
    private Courier courier;

    @BeforeEach
    void setUp() {
        storage = new MyBlockingDeque<>(DEFAULT_CAPACITY);
        courier = new Courier(1, DEFAULT_BAG_CAPACITY, storage);
    }

    @Test
    void testUseSetsOrderStateToDelivering() throws InterruptedException {
        List<Order> expectedOrders = new ArrayList<>();
        expectedOrders.add(new Order(1));
        expectedOrders.add(new Order(2));
        for (Order order : expectedOrders) {
            storage.put(order);
        }

        courier.use();

        for (Order order : expectedOrders) {
            assertEquals(State.DELIVERED, order.getState());
        }
    }

    @Test
    void testInterruptedException() throws InterruptedException {
        List<Order> expectedOrders = new ArrayList<>();
        expectedOrders.add(new Order(1));
        expectedOrders.add(new Order(2));
        for (Order order : expectedOrders) {
            storage.put(order);
        }

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> courier.use());

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Order order : expectedOrders) {
            assertEquals(State.DELIVERED, order.getState());
        }
    }

    @Test
    public void testUseWithOneNormalOrders() throws InterruptedException {
        MyBlockingDeque<Order> storage = new MyBlockingDeque<>(2);
        storage.put(new Order(1));
        storage.put(new Order(2));

        Courier courier = new Courier(1, 1, storage);

        List<Order> deliveredOrders = courier.use();
        assertEquals(1, deliveredOrders.size());
        assertEquals(State.DELIVERED, deliveredOrders.get(0).getState());
    }

    @Test
    public void testUseWithEmptyStorage() throws InterruptedException {
        Order order = new Order(1);
        storage.put(order);
        List<Order> orders = courier.use();

        assertTrue(storage.isEmpty());
        assertNotNull(orders);
        assertEquals(1, orders.size());
        assertEquals(order, orders.get(0));
        assertEquals(State.DELIVERED, order.getState());
    }
}
