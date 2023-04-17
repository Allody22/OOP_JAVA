package ru.nsu.mbogdanov.model.ingame.objects;

import ru.nsu.mbogdanov.model.environment.Cell;

import java.util.Random;

import static ru.nsu.mbogdanov.model.ingame.objects.Board.instanceCheck;


/**
 * This abstract class represents a fruit sprite in the Snake game.
 */
public abstract class Fruit implements Sprite {

    private final Cell fruit;
    private final Random random;

    /**
     * Constructs a new fruit with the specified width and height.
     *
     * @param width  - width of the fruit cell
     * @param height - height of the fruit cell
     */
    public Fruit(double width, double height) {
        this.fruit = new Cell(width, height);
        this.random = new Random();
    }

    /**
     * Returns the cell object representing the boundaries of the fruit.
     *
     * @return the cell object representing the boundaries of the fruit
     */
    @Override
    public Cell getBoundary() {
        return fruit;
    }

    /**
     * Checks if this fruit intersects with the specified sprite.
     *
     * @param sprite - sprite to check for intersection
     * @return true if this fruit intersects with the specified sprite
     */
    @Override
    public boolean intersects(Sprite sprite) {
        return instanceCheck(sprite, fruit);
    }

    /**
     * Updates the position of the fruit to a random position within the specified number of rows and columns.
     *
     * @param rowsNumber    - number of rows on the game board
     * @param columnsNumber - number of columns on the game board
     */
    @Override
    public void update(double rowsNumber, double columnsNumber) {
        double fruitX = random.nextInt((int) rowsNumber - 1);
        double fruitY = random.nextInt((int) columnsNumber - 1);
        fruit.setPosition(fruitX * fruit.getWidth(), fruitY * fruit.getHeight());
    }

    /**
     * This method is responsible for rendering the fruit in the specified object.
     *
     * @param object - object to render the fruit in
     */
    @Override
    public abstract void render(Object object);
}