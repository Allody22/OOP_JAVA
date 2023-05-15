package ru.nsu.mbogdanov.model.environment;

import lombok.Getter;

/**
 * This class represents a cell in the Snake game grid.
 * It contains the width and height of the cell,
 * as well as its position (rowCoordinate, columnCoordinate coordinates).
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
     * The rowCoordinate coordinate of the current cell.
     */
    private double rowCoordinate;

    /**
     * The columnCoordinate coordinate of the current cell.
     */
    private double columnCoordinate;

    /**
     * Constructs a new Cell with a specified width and height.
     *
     * @param width - width the width of the cell
     * @param height - height the height of the cell
     */
    public Cell(double width, double height) {
        this.width = width;
        this.height = height;
        this.rowCoordinate = 0;
        this.columnCoordinate = 0;
    }

    /**
     * Sets the position of the cell.
     *
     * @param rowCoordinate - the rowCoordinate coordinate of the current of the cell
     * @param columnCoordinate - the columnCoordinate coordinate of the current of the cell
     */
    public void setPosition(double rowCoordinate, double columnCoordinate) {
        this.rowCoordinate = rowCoordinate;
        this.columnCoordinate = columnCoordinate;
    }

    /**
     * Returns true if this cell intersects another cell.
     *
     * @param cell - the cell to check for intersection
     * @return true if this cell intersects the specified cell.
     */
    public boolean intersects(Cell cell) {
        return (cell.rowCoordinate + cell.width > rowCoordinate)
                && (cell.columnCoordinate + cell.height > columnCoordinate)
                && (cell.rowCoordinate < rowCoordinate + width)
                && (cell.columnCoordinate < columnCoordinate + height);
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
            return cell.rowCoordinate == rowCoordinate && cell.columnCoordinate == columnCoordinate
                    && cell.rowCoordinate + cell.width == rowCoordinate + width
                    && cell.columnCoordinate + cell.height == columnCoordinate + height;
        }
        return false;
    }
}

