package ru.nsu.mbogdanov2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.mbogdanov2.model.constant.State;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing class for state entity.
 */
public class StateTest {

    @Test
    public void testInQueue() {
        State state = State.IN_QUEUE;
        assertEquals("in queue", state.getState());
    }

    @Test
    public void testCooking() {
        State state = State.COOKING;
        assertEquals("cooking", state.getState());
    }

    @Test
    public void testInStock() {
        State state = State.IN_STOCK;
        assertEquals("in stock", state.getState());
    }

    @Test
    public void testDelivering() {
        State state = State.DELIVERING;
        Assertions.assertEquals("delivering", state.getState());
    }

    @Test
    public void testDelivered() {
        State state = State.DELIVERED;
        Assertions.assertEquals("delivered", state.getState());
    }
}
