package ru.nsu.mbogdanov2.json;

import lombok.Getter;
import lombok.Setter;

/**
 * Class for storing a json pizzeria object with all needed fields.
 */
@Getter
@Setter
public class PizzeriaJson {
    private int queueSize;
    private int storageSize;
    private BakerJson[] bakers;
    private CourierJson[] couriers;
}