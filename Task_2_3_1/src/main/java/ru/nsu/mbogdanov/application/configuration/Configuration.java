package ru.nsu.mbogdanov.application.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Configuration {
    private double squareSize;
    private int rowsNumber;
    private int columnsNumber;
    private int maximumScore;
    private int wallsNumber;
    private int fruitsNumber;
    private int snakeSpeed;
}
