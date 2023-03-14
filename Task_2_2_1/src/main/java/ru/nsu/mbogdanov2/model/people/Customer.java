package ru.nsu.mbogdanov2.model.people;

import ru.nsu.mbogdanov2.model.order.Order;
import ru.nsu.mbogdanov2.model.constant.State;
import ru.nsu.mbogdanov2.participants.Producer;
import ru.nsu.mbogdanov2.pizzeria.MyBlockingDequeue;

import java.util.Random;

/**
 * The Customer class simulates customer behavior.
 * The consumer creates a new order during a random time and adds it to the general order queue.
 */
public class Customer implements Producer<Order> {
    private static final long MAX_ORDERING_TIME = 100;
    private final Random random;
    private final MyBlockingDequeue<Order> queue;

    /**
     * Constructor class Customer.
     *
     * @param queue - shared order queue.
     */
    public Customer(MyBlockingDequeue<Order> queue) {
        this.random = new Random();
        this.queue = queue;
    }

    /**
     * Adds an order to the order queue with a random delay.
     * In case of failure, displays an error message.
     *
     * @param order - object which should be produced.
     */
    @Override
    public void produce(Order order) {
        long orderingTime = random.nextLong(MAX_ORDERING_TIME);
        try {
            Thread.sleep(orderingTime);
            order.setState(State.IN_QUEUE);
            queue.put(order);
        } catch (NullPointerException exception) {
            System.err.println("A non-existent order was received from a customer.");
        } catch (InterruptedException exception) {
            System.err.println("The customer was unable to place an order with id: " + order.getId());
        }
    }
}