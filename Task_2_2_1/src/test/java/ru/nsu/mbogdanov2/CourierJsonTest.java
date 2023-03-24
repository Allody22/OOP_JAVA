package ru.nsu.mbogdanov2;

import org.junit.jupiter.api.Test;
import ru.nsu.mbogdanov2.json.CourierJson;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for Courier json class.
 */
public class CourierJsonTest {

    @Test
    public void testGetId() {
        CourierJson courier = new CourierJson();
        courier.setId(123);
        assertEquals(123, courier.getId());
    }

    @Test
    public void testGetBagCapacity() {
        CourierJson courier = new CourierJson();
        courier.setBagCapacity(5);
        assertEquals(5, courier.getBagCapacity());
    }
}