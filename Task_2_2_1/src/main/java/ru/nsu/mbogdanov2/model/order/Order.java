package ru.nsu.mbogdanov2.model.order;

import ru.nsu.mbogdanov2.model.constant.State;

/**
 * Represents an order in a system with an id and a state.
 */
public class Order {
    private final int id;
    private State state;

    /**
     * Creates a new Order object with the given id.
     *
     * @param id the unique identifier of the order.
     */
    public Order(int id) {
        this.id = id;
    }

    /**
     * Returns the id of the order.
     *
     * @return the id of the order.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the current state of the order.
     *
     * @return the current state of the order.
     */
    public State getState() {
        return state;
    }

    /**
     * Sets the state of the order to the given state.
     *
     * @param state the new state of the order.
     */
    public void setState(State state) {
        this.state = state;
        System.out.println(this);
    }

    /**
     * Returns a string representation of the order including its id and state.
     *
     * @return a string representation of the order.
     */
    @Override
    public String toString() {
        return "[Order with id: " + id + "], [" + state.getState() + "]";
    }
}