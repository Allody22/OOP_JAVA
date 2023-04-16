package ru.nsu.mbogdanov.snakegame.sprite.snake;


/**
 * This enum represents the direction that a game sprite can move in this game.
 * It has the four possible directions: UP, DOWN, LEFT, and RIGHT.
 */
public enum Direction {

    UP,
    DOWN,
    LEFT,
    RIGHT;

    /**
     * Checks if this direction is opposite to the specified direction.
     *
     * @param direction - direction to check for opposites
     * @return true if this direction is opposite to the specified direction
     */
    public boolean opposite(Direction direction) {
        return ((this == UP && direction == DOWN)
                || (this == DOWN && direction == UP)
                || (this == LEFT && direction == RIGHT)
                || (this == RIGHT && direction == LEFT));
    }
}
