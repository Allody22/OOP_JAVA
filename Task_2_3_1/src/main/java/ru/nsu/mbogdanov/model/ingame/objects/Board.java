package ru.nsu.mbogdanov.model.ingame.objects;

import ru.nsu.mbogdanov.model.environment.Cell;

import java.util.List;


/**
 * This abstract class represents the game board in the Snake game.
 * It contains a board cell object, which represents the boundaries of the game board.
 * It also implements the Sprite interface
 * and contains methods for checking intersection with other sprites,
 * updating board position,
 * and rendering the board.
 */
public abstract class Board implements Sprite {

    private final Cell board;

    /**
     * Constructs a new Board object with a specified width and height.
     *
     * @param width  - the width of the game board
     * @param height - the height of the game board
     */
    public Board(double width, double height) {
        board = new Cell(width, height);
    }

    /**
     * Gets the cell of the board.
     *
     * @return the cell object representing the boundaries of the board
     */
    @Override
    public Cell getBoundary() {
        return board;
    }

    /**
     * Checks if this board intersects with the specified sprite.
     *
     * @param sprite - the sprite to check for intersection
     * @return true if this board intersects with the specified sprite
     */
    @Override
    public boolean intersects(Sprite sprite) {
        return instanceCheck(sprite, board);
    }

    /**
     * Checks if this sprite intersects with the specified board cell.
     *
     * @param sprite - the sprite instance to check for intersection
     * @param board  - the board cell to check for intersection
     * @return true if the specified sprite instance intersects with the specified board cell
     */
    public static boolean instanceCheck(Sprite sprite, Cell board) {
        if (sprite.getBoundary() instanceof Cell cell) {
            return board != cell && board.intersects(cell);
        }
        if (sprite instanceof Snake snake) {
            List<Cell> boundary = snake.getBoundary();
            return boundary.stream().anyMatch(cell -> board != cell
                    && board.intersects(cell));
        }
        return false;
    }

    /**
     * Updates the position of the board cell to the specified x, y position.
     *
     * @param rowCoordinate - x coordinate of the board cell position
     * @param columnCoordinate - y coordinate of the board cell position
     */
    @Override
    public void update(double rowCoordinate, double columnCoordinate) {
        board.setPosition(rowCoordinate, columnCoordinate);
    }

    /**
     * It is responsible for rendering the board in the specified object.
     *
     * @param object - the object to render the board in
     */
    @Override
    public abstract void render(Object object);
}
