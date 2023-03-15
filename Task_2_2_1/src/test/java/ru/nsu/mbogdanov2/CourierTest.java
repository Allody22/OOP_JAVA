package ru.nsu.mbogdanov2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.mbogdanov2.model.constant.State;
import ru.nsu.mbogdanov2.model.order.Order;
import ru.nsu.mbogdanov2.model.people.Courier;
import ru.nsu.mbogdanov2.pizzeria.MyBlockingDequeue;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourierTest {

    private static final int DEFAULT_CAPACITY = 5;
    private static final int DEFAULT_BAG_CAPACITY = 3;

    private MyBlockingDequeue<Order> storage;
    private Courier courier;

    @BeforeEach
    void setUp() {
        storage = new MyBlockingDequeue<>(DEFAULT_CAPACITY);
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

}
