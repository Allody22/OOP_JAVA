package ru.nsu.mbogdanov2;

import org.junit.jupiter.api.Test;
import ru.nsu.mbogdanov2.json.CourierJson;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourierJsonTest {

    @Test
    public void testGetId() {
        CourierJson courier = new CourierJson();
        courier.id = 123;
        assertEquals(123, courier.id());
    }

    @Test
    public void testGetBagCapacity() {
        CourierJson courier = new CourierJson();
        courier.bagCapacity = 5;
        assertEquals(5, courier.bagCapacity());
    }
}