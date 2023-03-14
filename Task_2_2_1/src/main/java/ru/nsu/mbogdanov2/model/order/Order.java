package ru.nsu.mbogdanov2.model.order;

import ru.nsu.mbogdanov2.model.constant.State;

/**
 * The order class with order id and state of the order.
 */
public class Order {
    private final int id;
    private State state;

    public Order(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "[Order with id: " + id + "], [" + state.getState() + "]";
    }
}