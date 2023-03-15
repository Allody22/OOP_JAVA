package ru.nsu.mbogdanov2;

import org.junit.jupiter.api.Test;
import ru.nsu.mbogdanov2.json.BakerJson;
import ru.nsu.mbogdanov2.json.CourierJson;
import ru.nsu.mbogdanov2.json.PizzeriaJson;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PizzeriaJsonTest {
    @Test
    public void testQueueSize() {
        PizzeriaJson pizzeriaJson = new PizzeriaJson();
        pizzeriaJson.queueSize = 10;
        assertEquals(10, pizzeriaJson.queueSize());
    }

    @Test
    public void testStorageSize() {
        PizzeriaJson pizzeriaJson = new PizzeriaJson();
        pizzeriaJson.storageSize = 10;
        assertEquals(10, pizzeriaJson.storageSize());
    }

    @Test
    public void testBakers() {
        PizzeriaJson pizzeriaJson = new PizzeriaJson();
        BakerJson baker = new BakerJson();
        pizzeriaJson.bakers = new BakerJson[]{baker};
        assertArrayEquals(new BakerJson[]{baker}, pizzeriaJson.bakers());
    }

    @Test
    public void testCouriers() {
        PizzeriaJson pizzeriaJson = new PizzeriaJson();
        CourierJson courier = new CourierJson();
        pizzeriaJson.couriers = new CourierJson[]{courier};
        assertArrayEquals(new CourierJson[]{courier}, pizzeriaJson.couriers());
    }

}
