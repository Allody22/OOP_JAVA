package ru.nsu.mbogdanov2.model.people;

import ru.nsu.mbogdanov2.model.order.Order;
import ru.nsu.mbogdanov2.participants.Producer;
import ru.nsu.mbogdanov2.participants.User;
import ru.nsu.mbogdanov2.pizzeria.MyBlockingDequeue;

import java.util.Random;

import static ru.nsu.mbogdanov2.model.constant.State.COOKING;
import static ru.nsu.mbogdanov2.model.constant.State.IN_STOCK;

/**
 * The Baker class simulates the work of a baker.
 * The baker receives an order from the general queue of orders, within a certain
 * time (depending on his work experience) makes an order and places it in the storage.
 */
public class Baker extends Employee implements User<Order>, Producer<Order> {
    private static final long MAX_COOKING_TIME = 5000;
    private final int workingExperience;
    private final MyBlockingDequeue<Order> queue;
    private final MyBlockingDequeue<Order> storage;

    /**
     * Constructor for the baker entity.
     *
     * @param id                - id of the baker.
     * @param workingExperience - work experience of the baker.
     * @param queue             - shared order queue.
     * @param storage           - place to store finished orders.
     */
    public Baker(int id, int workingExperience,
                 MyBlockingDequeue<Order> queue, MyBlockingDequeue<Order> storage) {
        super(id);
        this.workingExperience = workingExperience;
        //this.random = new Random();
        this.queue = queue;
        this.storage = storage;
    }

    /**
     * Gets an order from the order queue.
     *
     * @return consumed order.
     */
    @Override
    public Order use() {
        try {
            Order order = queue.get();
            order.setState(COOKING);
            return order;
        } catch (InterruptedException exception) {
            System.err.println("The baker with id: " + getId() + " could not take the order.");
            return null;
        }
    }

    /**
     * Removes the finished order to the warehouse.
     *
     * @param order - object which should be produced.
     */
    @Override
    public void produce(Order order) {
        Random random = new Random();
        long leadTime = (long) (random.nextDouble() *  MAX_COOKING_TIME) / workingExperience;
        try {
            Thread.sleep(leadTime);
            order.setState(IN_STOCK);
            storage.put(order);
        } catch (NullPointerException exception) {
            System.err.println("The baker with id: " + getId()
                    + " tried to make an order that does not exist.");
        } catch (InterruptedException exception) {
            System.err.println("The baker with id: " + getId() + "could not make the order.");
        }
    }

    /**
     * Takes an order out of the order queue,
     * executes the order within a certain time, and then sends it to the storage.
     * This method is used in the run method,
     * which allows to simulate the constant work of a baker.
     * In case of failure, this method stops the run method.
     */
    @Override
    public void work() {
        Order order = use();
        if (order == null) {
            stop();
        }
        produce(order);
    }
}