package ru.nsu.mbogdanov2;


import org.junit.jupiter.api.Test;
import ru.nsu.mbogdanov2.model.order.Order;
import ru.nsu.mbogdanov2.model.people.Customers;
import ru.nsu.mbogdanov2.pizzeria.MyBlockingDequeue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing class for customers entity.
 */
public class CustomersTest {
    @Test
    public void testStopCustomers() throws InterruptedException {
        MyBlockingDequeue<Order> queue = new MyBlockingDequeue<>(10);
        Customers customers = new Customers(queue);

        // Запускаем генерацию заказов в отдельном потоке
        Thread customersThread = new Thread(customers);
        customersThread.start();

        // Даем потоку некоторое время для создания нескольких заказов
        Thread.sleep(1000);

        // Останавливаем генерацию заказов и проверяем, что она остановилась
        customers.stop();
        int ordersBeforeStop = queue.getSize();
        Thread.sleep(1000);
        int ordersAfterStop = queue.getSize();
        assertEquals(ordersBeforeStop, ordersAfterStop);
    }

    @Test
    public void testCustomersQueueLimit() throws InterruptedException {
        MyBlockingDequeue<Order> queue = new MyBlockingDequeue<>(1);
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
