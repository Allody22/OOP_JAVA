package ru.nsu.mbogdanov.snakegame.sprite;


/**
 * This interface represents a game sprite in the Snake game.
 */
public interface Sprite {

    /**
     * Returns the boundaries of the sprite.
     *
     * @return the boundaries of the sprite
     */
    Object getBoundary();

    /**
     * Updates the position of the sprite to the specified x, y position.
     *
     * @param x - x coordinate of the sprite position
     * @param y - y coordinate of the sprite position
     */
    void update(double x, double y);

    /**
     * Checks if this sprite intersects with the specified sprite.
     *
     * @param sprite - sprite to check for intersection
     * @return true if this sprite intersects with the specified sprite
     */
    boolean intersects(Sprite sprite);

    /**
     * Renders this sprite in the specified object.
     *
     * @param object the object to render the sprite in
     */
    void render(Object object);
}
