package ru.nsu.mbogdanov.snakegame.sprite.fruit;

import ru.nsu.mbogdanov.snakegame.cell.Cell;
import ru.nsu.mbogdanov.snakegame.sprite.Sprite;

import java.util.Random;

import static ru.nsu.mbogdanov.snakegame.sprite.board.Board.instanceCheck;


public abstract class Fruit implements Sprite {
    private final Cell fruit;
    private final Random random;

    public Fruit(double width, double height) {
        this.fruit = new Cell(width, height);
        this.random = new Random();
    }

    @Override
    public Cell getBoundary() {
        return fruit;
    }

    @Override
    public boolean intersects(Sprite sprite) {
        return instanceCheck(sprite, fruit);
    }


    @Override
    public void update(double rowsNumber, double columnsNumber) {
        double fruitX = random.nextInt((int) rowsNumber - 1);
        double fruitY = random.nextInt((int) columnsNumber - 1);
        fruit.setPosition(fruitX * fruit.getWidth(), fruitY * fruit.getHeight());
    }

    @Override
    public abstract void render(Object object);
}
