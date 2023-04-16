package ru.nsu.mbogdanov.snakegame.sprite.snake;

import lombok.Getter;
import ru.nsu.mbogdanov.snakegame.cell.Cell;
import ru.nsu.mbogdanov.snakegame.sprite.Sprite;
import ru.nsu.mbogdanov.snakegame.sprite.board.Board;
import ru.nsu.mbogdanov.snakegame.sprite.fruit.Fruit;
import ru.nsu.mbogdanov.snakegame.sprite.wall.Wall;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ru.nsu.mbogdanov.snakegame.sprite.snake.Direction.RIGHT;

/**
 * This abstract class represents a snake sprite in the Snake game.
 */
public abstract class Snake implements Sprite {
    private final int INITIAL_LENGTH = 3;
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
        this.body = IntStream.range(0, INITIAL_LENGTH)
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
        for (int i = 1; i < INITIAL_LENGTH; ++i) {
            body.get(i).setPosition(body.get(i - 1).getX() - head.getWidth(), headPositionY);
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
     * @param x - x coordinate of the sprite position
     * @param y - y coordinate of the sprite position
     */

    @Override
    public void update(double x, double y) {
        Cell head = new Cell(this.head.getWidth(), this.head.getHeight());
        switch (direction) {
            case RIGHT -> head.setPosition(this.head.getX() + x, this.head.getY());
            case LEFT -> head.setPosition(this.head.getX() - x, this.head.getY());
            case UP -> head.setPosition(this.head.getX(), this.head.getY() - y);
            case DOWN -> head.setPosition(this.head.getX(), this.head.getY() + y);
        }
        this.head = head;
        body.add(0, head);
        body.remove(getLength() - 1);

    }

    /**
     * Function just renders the current sprite.
     *
     * @param object the object to render the sprite in
     */
    @Override
    public abstract void render(Object object);
}
