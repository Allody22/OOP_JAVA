package ru.nsu.mbogdanov2;

import org.junit.jupiter.api.Test;
import ru.nsu.mbogdanov2.model.constant.State;
import ru.nsu.mbogdanov2.model.order.Order;
import ru.nsu.mbogdanov2.model.people.Customer;
import ru.nsu.mbogdanov2.pizzeria.MyBlockingDeque;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for customer entity.
 */
class CustomerTest {

    @Test
    void testProduce() throws InterruptedException {
        MyBlockingDeque<Order> queue = new MyBlockingDeque<>(10);
        Customer customer = new Customer(queue);
        Order order = new Order(1);

        customer.produce(order);
        List<Order> orders = new ArrayList<>(queue.get(1));
        assertEquals(1, orders.size());
        Order receivedOrder = orders.get(0);
        assertEquals(order, receivedOrder);
        assertEquals(State.IN_QUEUE, receivedOrder.getState());

    }

    @Test
    public void testProduceWithNullOrder() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent));

        MyBlockingDeque<Order> queue = new MyBlockingDeque<>(1);
        Customer customer = new Customer(queue);
        customer.produce(null);

        String expectedOutput = "A non-existent order was received from a customer.";
        assertEquals(expectedOutput, outContent.toString());

        System.setErr(System.err);
    }

    @Test
    public void testInterruptedException() {
        MyBlockingDeque<Order> queue = new MyBlockingDeque<>(1);
        Order order = new Order(1);
        order.setState(State.IN_QUEUE);
        Customer customer = new Customer(queue);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> customer.produce(order));

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(State.IN_QUEUE, order.getState());
    }
}