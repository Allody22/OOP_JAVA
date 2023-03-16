package ru.nsu.mbogdanov2;

import org.junit.jupiter.api.Test;
import ru.nsu.mbogdanov2.model.constant.State;
import ru.nsu.mbogdanov2.model.order.Order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Testing class for Order class.
 */
public class OrderTest {

    @Test
    public void testConstructor() {
        Order order = new Order(123);
        assertEquals(123, order.getId());
        assertNull(order.getState());
    }

    @Test
    public void testSetState() {
        Order order = new Order(123);
        order.setState(State.IN_QUEUE);
        assertEquals(State.IN_QUEUE, order.getState());

        order.setState(State.COOKING);
        assertEquals(State.COOKING, order.getState());

        order.setState(State.DELIVERED);
        assertEquals(State.DELIVERED, order.getState());

        order.setState(State.DELIVERING);
        assertEquals(State.DELIVERING, order.getState());

        order.setState(State.IN_STOCK);
        assertEquals(State.IN_STOCK, order.getState());
    }


    @Test
    public void testMyMethodThrowsMyException() {
        Order order = new Order(123);
        assertThrows(NullPointerException.class, () -> order.setState(null));
    }

    @Test
    public void testToString() {
        Order order = new Order(123);

        order.setState(State.IN_QUEUE);
        assertEquals("[Order with id: 123], [in queue]", order.toString());

        order.setState(State.COOKING);
        assertEquals("[Order with id: 123], [cooking]", order.toString());

        order.setState(State.DELIVERED);
        assertEquals("[Order with id: 123], [delivered]", order.toString());

        order.setState(State.DELIVERING);
        assertEquals("[Order with id: 123], [delivering]", order.toString());

        order.setState(State.IN_STOCK);
        assertEquals("[Order with id: 123], [in stock]", order.toString());
    }
}
