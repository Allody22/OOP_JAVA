package ru.nsu.mbogdanov2.json;

/**
 * Class for storing a json pizzeria object with all needed fields.
 */
public class PizzeriaJson {
    public int queueSize;
    public int storageSize;
    public BakerJson[] bakers;
    public CourierJson[] couriers;

    public int queueSize() {
        return queueSize;
    }

    public int storageSize() {
        return storageSize;
    }

    public BakerJson[] bakers() {
        return bakers;
    }

    public CourierJson[] couriers() {
        return couriers;
    }
}