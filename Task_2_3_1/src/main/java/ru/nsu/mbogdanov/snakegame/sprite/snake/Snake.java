package ru.nsu.mbogdanov.snakegame.sprite.snake;

import ru.nsu.mbogdanov.snakegame.cell.Cell;
import ru.nsu.mbogdanov.snakegame.sprite.Sprite;
import ru.nsu.mbogdanov.snakegame.sprite.board.Board;
import ru.nsu.mbogdanov.snakegame.sprite.fruit.Fruit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ru.nsu.mbogdanov.snakegame.sprite.snake.Direction.RIGHT;

public abstract class Snake implements Sprite {
    private final int INITIAL_LENGTH = 2;
    private Direction direction;
    private Cell head;
    private final List<Cell> body;


    public Snake(double width, double height) {
        //this.body = Stream.generate(() -> new Cell(width, height))
        //        .limit(INITIAL_LENGTH).collect(Collectors.toCollection(ArrayList::new));

        this.body = IntStream.range(0, INITIAL_LENGTH)
                .mapToObj(i -> new Cell(width, height))
                .collect(Collectors.toCollection(ArrayList::new));

        this.head = this.body.get(0);
    }

    public Direction getDirection() {
        return direction;
    }

    public int getLength() {
        return body.size();
    }

    public void setDirection(Direction direction) {
        if (this.direction.opposite(direction)) {
            return;
        }
        this.direction = direction;
    }

    public void start(double headPositionX, double headPositionY) {
        direction = RIGHT;
        head.setPosition(headPositionX, headPositionY);
        for (int i = 1; i < INITIAL_LENGTH; ++i) {
            body.get(i).setPosition(body.get(i - 1).getX() - head.getWidth(), headPositionY);
        }
    }

    public void grow() {
        Cell flake = new Cell(head.getWidth(), head.getHeight());
        body.add(flake);
    }


    @Override
    public List<Cell> getBoundary() {
        return Collections.unmodifiableList(body);
    }


    @Override
    public boolean intersects(Sprite sprite) {
        if (sprite == this) {
            return body.stream().anyMatch((cell -> cell != head && cell.intersects(head)));
        }
        if (sprite instanceof Board) {
            return head.intersects((Cell) sprite.getBoundary());
        }
        if (sprite instanceof Fruit) {
            return body.stream().anyMatch((cell -> cell.intersects((Cell) sprite.getBoundary())));
        }
        return false;
    }

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

    @Override
    public abstract void render(Object object);
}
