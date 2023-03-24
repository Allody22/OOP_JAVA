package ru.nsu.mbogdanov2.json;

import lombok.Getter;
import lombok.Setter;

/**
 * Class for storing a json baker object with id and working experience.
 */
@Getter
@Setter
public class BakerJson {
    private int id;
    private int workingExperience;
}