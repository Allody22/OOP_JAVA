package ru.nsu.mbogdanov.snakegame.sprite.board;

import ru.nsu.mbogdanov.snakegame.cell.Cell;
import ru.nsu.mbogdanov.snakegame.sprite.Sprite;
import ru.nsu.mbogdanov.snakegame.sprite.snake.Snake;

import java.util.List;


public abstract class Board implements Sprite {
    private final Cell board;


    public Board(double width, double height) {
        board = new Cell(width, height);
    }

    @Override
    public Cell getBoundary() {
        return board;
    }

    @Override
    public boolean intersects(Sprite sprite) {
        return instanceCheck(sprite, board);
    }

    public static boolean instanceCheck(Sprite sprite, Cell board) {
        if (sprite.getBoundary() instanceof Cell cell) {
            return board != cell && board.intersects(cell);
        }
        if (sprite instanceof Snake snake) {
            List<Cell> boundary = snake.getBoundary();
            return boundary.stream().anyMatch(cell -> board != cell && board.intersects(cell));
        }
        return false;
    }

    @Override
    public void update(double x, double y) {
        board.setPosition(x, y);
    }

    @Override
    public abstract void render(Object object);
}
