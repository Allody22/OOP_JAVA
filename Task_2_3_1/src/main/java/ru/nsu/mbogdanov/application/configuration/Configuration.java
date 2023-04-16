package ru.nsu.mbogdanov.application.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Configuration file that
 */
@Data
@AllArgsConstructor
public class Configuration {

    /**
     * Size of squares on the game field.
     */
    private double squareSize;

    /**
     * Number of rows on the game field.
     */
    private int rowsNumber;

    /**
     * Number of columns on the game field.
     */
    private int columnsNumber;

    /**
     * Maximum score value in the game.
     */
    private int maximumScore;

    /**
     * Number of walls on the game field.
     */
    private int wallsNumber;

    /**
     * Number of fruits on the game field.
     */
    private int fruitsNumber;

    /**
     * Game and snake speed.
     */
    private int snakeSpeed;
}
