package ru.nsu.mbogdanov.model.environment;

import lombok.Getter;

/**
 * This class represents a cell in the Snake game grid.
 * It contains the width and height of the cell, as well as its position (xCoordinate, yCoordinate coordinates).
 */
@Getter
public class Cell {

    /**
     * The width of the cell.
     */
    private final double width;

    /**
     * The height of the cell.
     */
    private final double height;

    /**
     * The xCoordinate coordinate of the current cell.
     */
    private double xCoordinate;

    /**
     * The yCoordinate coordinate of the current cell.
     */
    private double yCoordinate;

    /**
     * Constructs a new Cell with a specified width and height.
     *
     * @param width  - width the width of the cell
     * @param height - height the height of the cell
     */
    public Cell(double width, double height) {
        this.width = width;
        this.height = height;
        this.xCoordinate = 0;
        this.yCoordinate = 0;
    }

    /**
     * Sets the position of the cell.
     *
     * @param xCoordinate - the xCoordinate coordinate of the current of the cell
     * @param yCoordinate - the yCoordinate coordinate of the current of the cell
     */
    public void setPosition(double xCoordinate, double yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     * Returns true if this cell intersects another cell.
     *
     * @param cell - the cell to check for intersection
     * @return true if this cell intersects the specified cell.
     */
    public boolean intersects(Cell cell) {
        return (cell.xCoordinate + cell.width > xCoordinate)
                && (cell.yCoordinate + cell.height > yCoordinate)
                && (cell.xCoordinate < xCoordinate + width)
                && (cell.yCoordinate < yCoordinate + height);
    }

    /**
     * Returns true if this cell is equal to the specified object.
     *
     * @param object - the object to compare with this cell
     * @return true if this cell is equal to the specified object
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof Cell cell) {
            return cell.xCoordinate == xCoordinate && cell.yCoordinate == yCoordinate
                    && cell.xCoordinate + cell.width == xCoordinate + width
                    && cell.yCoordinate + cell.height == yCoordinate + height;
        }
        return false;
    }
}

