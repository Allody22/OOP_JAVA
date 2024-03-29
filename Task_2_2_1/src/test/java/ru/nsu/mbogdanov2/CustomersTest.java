package ru.nsu.mbogdanov2;

import org.junit.jupiter.api.Test;
import ru.nsu.mbogdanov2.model.order.Order;
import ru.nsu.mbogdanov2.model.people.Customers;
import ru.nsu.mbogdanov2.pizzeria.MyBlockingDeque;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing class for customers entity.
 */
public class CustomersTest {
    @Test
    public void testStopCustomers() throws InterruptedException {
        MyBlockingDeque<Order> queue = new MyBlockingDeque<>(10);
        Customers customers = new Customers(queue);

        Thread customersThread = new Thread(customers);
        customersThread.start();

        Thread.sleep(1000);

        customers.stop();
        int ordersBeforeStop = queue.getSize();
        Thread.sleep(1000);
        int ordersAfterStop = queue.getSize();
        assertEquals(ordersBeforeStop, ordersAfterStop);
    }

    @Test
    public void testCustomersQueueLimit() throws InterruptedException {
        MyBlockingDeque<Order> queue = new MyBlockingDeque<>(1);
        Customers customers = new Customers(queue);

        Thread customersThread = new Thread(customers);
        customersThread.start();

        Thread.sleep(1000);

        int ordersBeforeStop = queue.getSize();
        Thread.sleep(1000);
        int ordersAfterStop = queue.getSize();
        assertEquals(ordersBeforeStop, ordersAfterStop);
    }
}
