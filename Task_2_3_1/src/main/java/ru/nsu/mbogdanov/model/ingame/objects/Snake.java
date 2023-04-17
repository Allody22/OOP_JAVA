package ru.nsu.mbogdanov.model.ingame.objects;

import lombok.Getter;
import ru.nsu.mbogdanov.model.constants.Direction;
import ru.nsu.mbogdanov.model.environment.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ru.nsu.mbogdanov.model.constants.Direction.RIGHT;

/**
 * This abstract class represents a snake sprite in the Snake game.
 */
public abstract class Snake implements Sprite {
    private final int startLength = 3;
    @Getter
    private Direction direction;
    private Cell head;
    private final List<Cell> body;

    /**
     * Snake constructor with its parameters.
     *
     * @param width  - the width of this snakes cell
     * @param height - the height of this snakes cell
     */
    public Snake(double width, double height) {
        this.body = IntStream.range(0, startLength)
                .mapToObj(i -> new Cell(width, height))
                .collect(Collectors.toCollection(ArrayList::new));

        this.head = this.body.get(0);
    }

    /**
     * Gets the length of the snakes body.
     *
     * @return int snakes length
     */
    public int getLength() {
        return body.size();
    }

    /**
     * Function changes the snake direction.
     *
     * @param direction - new direction of this snake
     */
    public void setDirection(Direction direction) {
        if (this.direction.opposite(direction)) {
            return;
        }
        this.direction = direction;
    }

    /**
     * Creates the snake in the input position.
     *
     * @param headPositionX - x coordinate of the snakes head
     * @param headPositionY - y coordinate of the snakes head
     */

    public void start(double headPositionX, double headPositionY) {
        direction = RIGHT;
        head.setPosition(headPositionX, headPositionY);
        for (int i = 1; i < startLength; ++i) {
            body.get(i).setPosition(body.get(i - 1).getRowCoordinate()
                    - head.getWidth(), headPositionY);
        }
    }

    /**
     * This function adds new body cell to the current snake.
     */
    public void grow() {
        Cell flake = new Cell(head.getWidth(), head.getHeight());
        body.add(flake);
    }

    /**
     * Returns the boundary of the snakes body.
     *
     * @return boundary of the snakes body
     */
    @Override
    public List<Cell> getBoundary() {
        return Collections.unmodifiableList(body);
    }

    /**
     * Method to check if the spires intersects.
     *
     * @param sprite - sprite to check for intersection
     * @return true if the sprites intersects with each other.
     */
    @Override
    public boolean intersects(Sprite sprite) {
        if (sprite == this) {
            return body.stream().anyMatch((cell -> cell != head && cell.intersects(head)));
        }
        if (sprite instanceof Board) {
            return head.intersects((Cell) sprite.getBoundary());
        }
        if (sprite instanceof Fruit || sprite instanceof Wall) {
            return body.stream().anyMatch((cell -> cell.intersects((Cell) sprite.getBoundary())));
        }
        return false;
    }

    /**
     * Updates coordinates of the current sprite.
     *
     * @param rowCoordinate - x coordinate of the sprite position
     * @param columnCoordinate - y coordinate of the sprite position
     */

    @Override
    public void update(double rowCoordinate, double columnCoordinate) {
        Cell head = new Cell(this.head.getWidth(), this.head.getHeight());
        switch (direction) {
            case RIGHT -> head.setPosition(this.head.getRowCoordinate()
                    + rowCoordinate, this.head.getColumnCoordinate());
            case LEFT -> head.setPosition(this.head.getRowCoordinate()
                    - rowCoordinate, this.head.getColumnCoordinate());
            case UP -> head.setPosition(this.head.getRowCoordinate(),
                    this.head.getColumnCoordinate() - columnCoordinate);
            case DOWN -> head.setPosition(this.head.getRowCoordinate(),
                    this.head.getColumnCoordinate() + columnCoordinate);
            default -> throw new RuntimeException("Something wrong with snake direction");
        }
        this.head = head;
        body.add(0, head);
        body.remove(getLength() - 1);

    }

    /**
     * Function just renders the current sprite.
     *
     * @param object - the object to render the sprite in
     */
    @Override
    public abstract void render(Object object);
}
