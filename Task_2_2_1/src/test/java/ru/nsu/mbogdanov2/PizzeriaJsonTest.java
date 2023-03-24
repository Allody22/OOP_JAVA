package ru.nsu.mbogdanov2;

import org.junit.jupiter.api.Test;
import ru.nsu.mbogdanov2.json.BakerJson;
import ru.nsu.mbogdanov2.json.CourierJson;
import ru.nsu.mbogdanov2.json.PizzeriaJson;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for PizzeriaJson class.
 */
public class PizzeriaJsonTest {
    @Test
    public void testQueueSize() {
        PizzeriaJson pizzeriaJson = new PizzeriaJson();
        pizzeriaJson.setQueueSize(10);
        assertEquals(10, pizzeriaJson.getQueueSize());
    }

    @Test
    public void testStorageSize() {
        PizzeriaJson pizzeriaJson = new PizzeriaJson();
        pizzeriaJson.setStorageSize(10);
        assertEquals(10, pizzeriaJson.getStorageSize());
    }

    @Test
    public void testBakers() {
        PizzeriaJson pizzeriaJson = new PizzeriaJson();
        BakerJson baker = new BakerJson();
        pizzeriaJson.setBakers(new BakerJson[]{baker});
        assertArrayEquals(new BakerJson[]{baker}, pizzeriaJson.bakers());
    }

    @Test
    public void testCouriers() {
        PizzeriaJson pizzeriaJson = new PizzeriaJson();
        CourierJson courier = new CourierJson();
        pizzeriaJson.setCouriers(new CourierJson[]{courier});
        assertArrayEquals(new CourierJson[]{courier}, pizzeriaJson.couriers());
    }

}
