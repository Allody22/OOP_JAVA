package ru.nsu.mbogdanov2.model.people;

import ru.nsu.mbogdanov2.model.order.Order;
import ru.nsu.mbogdanov2.model.constant.State;
import ru.nsu.mbogdanov2.participants.User;
import ru.nsu.mbogdanov2.pizzeria.MyBlockingDequeue;

import java.util.List;
import java.util.Random;

import static ru.nsu.mbogdanov2.model.constant.State.DELIVERED;
import static ru.nsu.mbogdanov2.model.constant.State.DELIVERING;

/**
 * The Courier class simulates the work of a courier.
 * The courier receives one or more orders from the storage and then delivers them.
 */
public class Courier extends Employee implements User<List<Order>> {
    private static final long MAX_DELIVERY_TIME = 1000;
    private final int bagCapacity;
    private List<Order> orders;
    private final MyBlockingDequeue<Order> storage;
    private final Random random = new Random();

    /**
     * Creates an instance of the class Courier.
     *
     * @param id          - id of the courier.
     * @param bagCapacity - bag capacity of the courier.
     * @param storage     - place to store finished orders.
     */
    public Courier(int id, int bagCapacity, MyBlockingDequeue<Order> storage) {
        super(id);
        this.bagCapacity = bagCapacity;
        this.storage = storage;
        //this.random = new Random();
    }

    private void setOrdersState(State state) {
        for (Order order : orders) {
            order.setState(state);
        }
    }

    /**
     * Gets an order from the order queue.
     *
     * @return - consumed orders.
     */
    @Override
    public List<Order> use() {
        long deliveryTime = random.nextLong(MAX_DELIVERY_TIME);
        try {
            orders = storage.get(bagCapacity);
            setOrdersState(DELIVERING);
            Thread.sleep(deliveryTime);
            setOrdersState(DELIVERED);
            return orders;
        } catch (InterruptedException exception) {
            System.err.println("The courier with id: " + getId() + " could not deliver the order.");
            return null;
        }
    }

    /**
     * Receives orders from the storage and delivers them.
     * This method is used in the run method, which allows to simulate the constant work of a courier.
     * In case of failure, this method stops the run method.
     */
    @Override
    public void work() {
        List<Order> orders = use();
        if (orders == null) {
            stop();
        }
    }
}