package ru.nsu.mbogdanov.snakegame.cell;

import lombok.Getter;

/**
 * This class represents a cell in the Snake game grid.
 * It contains the width and height of the cell, as well as its position (x, y coordinates).
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
     * The x coordinate of the current cell.
     */
    private double x;

    /**
     * The y coordinate of the current cell.
     */
    private double y;

    /**
     * Constructs a new Cell with a specified width and height.
     *
     * @param width- width the width of the cell
     * @param height - height the height of the cell
     */
    public Cell(double width, double height) {
        this.width = width;
        this.height = height;
        this.x = 0;
        this.y = 0;
    }

    /**
     * Sets the position of the cell.
     *
     * @param x - the x coordinate of the current of the cell
     * @param y - the y coordinate of the current of the cell
     */
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns true if this cell intersects another cell.
     *
     * @param cell - the cell to check for intersection
     * @return true if this cell intersects the specified cell.
     */
    public boolean intersects(Cell cell) {
        return (cell.x + cell.width > x) && (cell.y + cell.height > y)
                && (cell.x < x + width) && (cell.y < y + height);
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
            return cell.x == x && cell.y == y && cell.x + cell.width == x + width && cell.y + cell.height == y + height;
        }
        return false;
    }
}

