package ru.nsu.mbogdanov2.json;

/**
 * Class for storing a json pizzeria object with all needed fields.
 */
public class PizzeriaJSON {
    private int queueSize;
    private int storageSize;
    private BakerJSON[] bakers;
    private CourierJSON[] couriers;

    public int queueSize() {
        return queueSize;
    }

    public int storageSize() {
        return storageSize;
    }

    public BakerJSON[] bakers() {
        return bakers;
    }

    public CourierJSON[] couriers() {
        return couriers;
    }
}