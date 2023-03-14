package ru.nsu.mbogdanov2.model.people;

import ru.nsu.mbogdanov2.model.order.Order;
import ru.nsu.mbogdanov2.pizzeria.MyBlockingDequeue;

/**
 * The Customers class simulates how several customers behave.
 * Customers create orders and place them in a shared order queue.
 */
public class Customers implements Runnable {
    private boolean runCustomers;
    private final MyBlockingDequeue<Order> queue;

    /**
     * Constructor of the class Customers.
     *
     * @param queue - shared order queue.
     */
    public Customers(MyBlockingDequeue<Order> queue) {
        this.runCustomers = false;
        this.queue = queue;
    }

    /**
     * Implementation of the Runnable interface.
     * Starts the customers flow and stops only when stop method is called.
     */
    @Override
    public void run() {
        runCustomers = true;
        for (int i = 0; runCustomers; ++i) {
            Order order = new Order(i);
            new Customer(this.queue).produce(order);
        }
    }

    /**
     * Stops all customers.
     */
    public void stop() {
        runCustomers = false;
    }
}