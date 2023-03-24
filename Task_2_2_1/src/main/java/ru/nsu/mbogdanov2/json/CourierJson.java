package ru.nsu.mbogdanov2.json;

import lombok.Getter;
import lombok.Setter;

/**
 * Class for storing a json courier object with id and bax capacity.
 */
@Getter @Setter
public class CourierJson {
    private int id;
    private int bagCapacity;

    public int id() {
        return id;
    }

    public int bagCapacity() {
        return bagCapacity;
    }
}